package idgenerator;

import idgenerator.util.SpringUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IdGenerator {
    /**
     * 全局并发符号
     */
    private static final Object globalSyncSignal = new Object();

//    @Autowired
    // todo spring貌似不支持这样注入静态属性，可以试一下
    private  static SequenceStore sequenceStore;

    private static PlatformTransactionManager transactionManager = null;

    /**
     * 每种类型对应一个并发符号
     */
    private static final Map<String, Object> entitySyncSignalMap = new HashMap<>();

    /**
     * 每种类型对应一个当前步长信息的结构体
     */
    private static final Map<String, EntityIDRecord> entityCurrentRecordMap = new HashMap<>();

    /**
     * 程序启动标识
     */
    private static volatile boolean isStart = false;

    private static final String ENTITY_TYPE = "Dota2";

    private static final String ID_SEPORATOR = "-";

    private static final Integer INDEX_START = 0;

    private static final Integer MIN_0 = 0;

    // 获取号码段允许的失败次数
    private static final Integer FAILED_TIMES = 5;

    public static String generateId(String entityType) {
        return generateId(entityType, 10);
    }

    private static String generateId (String entityType, int formatNumber) {
        Assert.notNull(entityType, "entityType can not be null");
        Assert.isTrue(formatNumber > 0, "formatNumber must be greater than zero");

        String idResult;

        /**
         * 如果程序刚启动，初始化数据
         */
        init();

        // 根据entityType类型从map中获取对应的并发Object和IdReocrd
        Object syncSignal = entitySyncSignalMap.get(entityType);
        EntityIDRecord entityIdRecord = entityCurrentRecordMap.get(entityType);

        // 并发控制，不同的entityType使用的不同的同步Object，这样可以使多个entityType的获取逻辑并发进行
        if(null == syncSignal || null == entityIdRecord) {
            throw new IdGeneratorException(entityType, "synSignal or entityIdRecord can not be null");
        }

        synchronized (syncSignal) {
            int counter = entityIdRecord.getCurrentIndex();
            if(counter >= entityIdRecord.getStepSize()) {
                // 号段已经用完，需要重新申请
                if(!applyNewSegment(entityIdRecord)) {
                    // 若干次重试之后，申请号段仍然不成功，向外抛出异常
                    throw new IdGeneratorException(entityType, "apply new id section failed.");
                } else {
                    counter = entityIdRecord.getCurrentIndex();
                }
            }

            // 构建ID
            StringBuilder idBuilder = new StringBuilder();
            idBuilder.append(entityIdRecord.getEntityCode()).append(ID_SEPORATOR);
            // todo 加入计数起点，后续要将该段保持为11（or更多）位
            idBuilder.append(entityIdRecord.getIdSegmentStartCode()).append(ID_SEPORATOR);
            // todo 加入当前index，后续要处理为4位（0-999）
            idBuilder.append(counter++).append(ID_SEPORATOR);
            entityIdRecord.setCurrentIndex(counter);
            // 加入datacenterCode
            idBuilder.append(entityIdRecord.getDataCenterCode());

            idResult = idBuilder.toString();
        }

        return idResult;
    }

    /**
     * 首先不同的entityType是不会相互影响的，无需考虑
     * 其次，由于已经加了并发控制，所以同一节点相同的entityType会顺序进行，也没问题
     * 但是，不同节点间相同的entityType会争用数据库，这时要靠version字段进行乐观锁保证，允许重试一定的次数
     * 重点是向数据库进行更新写入的操作
     *
     * 申请号段允许重试若干次，但如果到达一定次数还不成功，就返回false
     * @param entityIdRecord
     */
    private static boolean applyNewSegment(EntityIDRecord entityIdRecord) {
        boolean pass = false;
        int count = 0;
        int result = 0;
        do{
            try{
                // 首先，读取数据库对应的记录
                String entityType = entityIdRecord.getEntityType();
                SequenceEntry entry = sequenceStore.getSequence(entityType);
                // 从当前idSegmentStartCode开始共stepSize个数正在被使用，所以要从下一个号段开始
                Long startCode = Long.valueOf(entry.getIdSegmentStartCode());
                startCode = startCode + entry.getStepSize();

                entityIdRecord.setIdSegmentStartCode(startCode.toString());
                entityIdRecord.setCurrentIndex(INDEX_START);

                // 向数据库写入
                entry.setIdSegmentStartCode(startCode.toString());
                // 如果result = 0或者出现了异常，意味着申请号段不成功，需要重新申请
                result = sequenceStore.update(entry);

            } catch (Exception e) {
                System.out.println("++++++++++++++++++++get id section failed.");
                pass = false;
                count++;
                e.printStackTrace();
                continue;
            }

            // 更新成功
            if(result > MIN_0) {
                pass = true;
            } else {
                pass = false;
                count++;
            }

        } while (!pass && count <= FAILED_TIMES);

        return pass;
    }

    public static void main(String[] args) {
        String test = "1233445566777";
        Long result = Long.valueOf(test);
        System.out.println(result);
    }

    private static void init() {
        // 确保相关bean都已经被初始化了
        // todo 两种方式，1.使用autowired，那么本类也要加载到spring中，2.使用applicationContext加载
        ensureBean();

        /**
         * 启动时初始化数据，只被初始一次
         */
        synchronized (globalSyncSignal) {
            if(!isStart) {
                List<SequenceEntry> seqEntryList = sequenceStore.getAllSequence();
                // 每个设置一个同步信号，设置一个record
                for(SequenceEntry entry : seqEntryList) {
                    entitySyncSignalMap.put(entry.getEntityType(), new Object());

                    entityCurrentRecordMap.put(entry.getEntityType(),
                            new EntityIDRecord(entry.getEntityType(), entry.getEntityCode(),
                                    entry.getDataCenterCode(), entry.getIdSegmentStartCode(),
                                    entry.getStepSize(), entry.getStepSize()));

                    isStart = true;
                }
            }
        }

    }

    /**
     * 确保相关的Bean处于可用状态
     */
    private static void ensureBean() {
        sequenceStore = SpringUtils.getOneBeanOfType(SequenceStore.class);
        if(null == sequenceStore) {
            throw new IdGeneratorException((String)null, "can not get instance of sequencestore.");
        }
    }

}
