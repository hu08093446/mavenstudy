package idgenerator.mybatis;

import com.alibaba.fastjson.JSON;
import idgenerator.SequenceEntry;
import idgenerator.mybatis.impl.MybatisSequenceStoreImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMybatis {

    private static final String FAST = "fast";

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:mybatis.xml");
        MybatisSequenceStoreImpl impl = (MybatisSequenceStoreImpl) ctx.getBean("mybatisSequenceStoreImpl");

        System.out.println(JSON.toJSONString(impl.getSequence(FAST)));

        SequenceEntry entry = new SequenceEntry();
        entry.setEntityType("slow");

//        impl.save(entry);
        entry.setDataCenterCode("01");
        entry.setVersion(1);
        System.out.println(impl.update(entry));


    }
}
