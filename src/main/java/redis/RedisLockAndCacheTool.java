package redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class RedisLockAndCacheTool {

    private static final Logger LOG = LoggerFactory.getLogger(RedisLockAndCacheTool.class);

    private static final String LOCK_SUCCESS = "OK";

    private static final String SET_IF_NOT_EXIST = "NX";

    private static final int SLEEP_TIME = 2;

    /**
     * 对应的时间单位：毫秒
     */
    private static final String SET_WITH_EXPIRE_TIME_MILLSECONDS = "PX";

    /**
     * 对应的时间单位：秒
     */
    private static final String SET_WITH_EXPIRE_TIME_SECONDS = "EX";

    private static final Long RELEASE_SUCCESS = 1L;

    private static boolean getDistributedLock(
            Jedis jedis, String lockKey, String requestId, int expireTime) {
        String result =
                jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME_SECONDS, expireTime);

        if (Objects.equals(result, LOCK_SUCCESS)) {
            return true;
        }

        return false;
    }

    private static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {
        String script =
                "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

        Object result =
                jedis.eval(
                        script,
                        Collections.singletonList(lockKey),
                        Collections.singletonList(requestId));

        if (Objects.equals(result, RELEASE_SUCCESS)) {
            System.out.println("release Ok");
            return true;
        } else {
            System.out.println("release failed");
            return false;
        }
    }

    /**
     * 尝试获取redis锁，带有超时重试机制
     * @param lockKey 加锁对象
     * @param requestId 加锁凭证（谁来加锁，就由谁来解锁）
     * @param expireTime 获取锁之后的超时时间 单位：毫秒
     * @param retryTime 超时重试总时间
     * @return 是否加锁成功 true 成功  false 失败
     * @throws InterruptedException
     */
    public static boolean tryGetRedisLockWithRetry(String lockKey, String requestId, int expireTime, int retryTime) throws InterruptedException {
        Long time = System.currentTimeMillis();
        // 标识是否拿到了锁
        boolean flag = tryGetRedisLock(lockKey, requestId, expireTime);
        while (!flag) {
            // 如果没拿到锁，沉睡SLEEP_TIME后，再次尝试
            TimeUnit.SECONDS.sleep(SLEEP_TIME);
            flag = tryGetRedisLock(lockKey, requestId, expireTime);

            if (System.currentTimeMillis() - time > retryTime * 1000) {
                // 超时
                break;
            }
        }

        return flag;
    }

    /**
     * 尝试获取redis锁
     * @param lockKey 加锁对象
     * @param requestId 加锁凭证（谁来加锁，就由谁来解锁）
     * @param expireTime 获取锁之后的超时时间 单位：毫秒
     * @return 是否加锁成功 true 成功  false 失败
     */
    public static boolean tryGetRedisLock(String lockKey, String requestId, int expireTime) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return false;
        }

        try {
            return getDistributedLock(jedis, lockKey, requestId, expireTime);
        } finally {
            closeJedis(jedis);
        }
    }

    /**
     * 释放redis锁
     * @param lockKey 加锁对象
     * @param requestId 加锁凭证（谁来加锁，就由谁来解锁）
     * @return 是否解锁成功 true 成功  false 失败
     */
    public static boolean releaseRedisLock(String lockKey, String requestId) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return false;
        }

        try {
            return releaseDistributedLock(jedis, lockKey, requestId);
        } finally {
            closeJedis(jedis);
        }
    }

    /**
     * 获取Jedis
     * @return 是否获取到 null 没有获取到  非null 获取到了呦
     */
    public static Jedis getJedis() {
        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
        if (null == jedisPool) {
            return null;
        }
        return jedisPool.getResource();
    }

    /**
     * 释放Jedis，用完要及时释放
     * @param jedis 释放对象
     */
    public static void closeJedis(Jedis jedis) {
        if (null == jedis) {
            return;
        }
        jedis.close();
    }
}
