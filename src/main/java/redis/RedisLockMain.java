package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RedisLockMain {
    private static final String LOCK_KEY = "life";

    public static void main(String[] args) {
        //        testJedisPool();
        testJedisLock();
    }

    public static void testJedisLock() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        // 加锁
        service.submit(new Together("huke"));


        OtherPeopleNeverOnYourLife("peopleA");
        OtherPeopleNeverOnYourLife("peopleB");
        OtherPeopleNeverOnYourLife("peopleC");

        service.shutdown();
    }

    /**
     * 其他人要加锁，不可能，除非业务挂了
     * @param name
     */
    private static void OtherPeopleNeverOnYourLife(String name) {

        try {
            TimeUnit.SECONDS.sleep(2);
            boolean result = RedisLockAndCacheTool.tryGetRedisLockWithRetry(LOCK_KEY, name, 60, 40);

            if(true == result) {
                System.out.println(name + " get lock");
            } else {
                System.out.println(name + " can not get lock");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            RedisLockAndCacheTool.releaseRedisLock(LOCK_KEY, name);
        }
    }

    private static class Together implements Runnable {

        String customerId;


        public Together(String customerId) {
            this.customerId = customerId;
        }

        @Override
        public void run() {
            String requestId = UUID.randomUUID().toString() + customerId;

            try {
                // 可以过期释放了锁
                RedisLockAndCacheTool.tryGetRedisLock(LOCK_KEY, requestId, 60);
                // 业务时长如果已经超过了锁的过期时间，那肯定是有问题的
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                RedisLockAndCacheTool.releaseRedisLock(LOCK_KEY, requestId);
            }
        }
    }

    public static void testJedisPool() {
        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();

        // 虽然是不同的连接，但是操作的是同一个数据库，所以两个连接看到的内容是一模一样的
        Jedis myJedis = jedisPool.getResource();
        Jedis aJedis = jedisPool.getResource();
        System.out.println(myJedis);
        System.out.println(aJedis);

        try {

            myJedis.set("nameA", "huke");
            myJedis.set("nameB", "chengss");

            aJedis.set("nameA", "hello world");
            aJedis.set("nameB", "hand in hand");

            Set<String> keySet = myJedis.keys("*");
            for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext(); ) {
                String key = iterator.next();
                System.out.println("myJedis " + "key: " + key + " value: " + myJedis.get(key));
            }

            Set<String> aSet = aJedis.keys("*");
            for (Iterator<String> aiterator = aSet.iterator(); aiterator.hasNext(); ) {
                String key = aiterator.next();
                System.out.println("aJedis " + "key: " + key + " value: " + myJedis.get(key));
            }

        } finally {
            JedisPoolUtil.clear(myJedis);
            JedisPoolUtil.clear(aJedis);

            JedisPoolUtil.release(myJedis);
            JedisPoolUtil.release(aJedis);
        }
    }
}
