package redis.generalrediscache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.RedisLockAndCacheTool;
import redis.clients.jedis.Jedis;

/**
 * 单实例枚举，目前最完美的单例模式
 */
public enum RedisCacheOperate {
    INSTANCE;

    private static final Logger LOG = LoggerFactory.getLogger(RedisCacheOperate.class);

    /**
     * 将key-value对加入redis缓存
     * @param key 键
     * @param value 值，可用json工具将各种类型转换为Json字符串
     * @param expireTime redis缓存过期时间
     * @return 加入缓存是否成功 true 成功  false 失败
     */
    public boolean setCache(String key, String value, int expireTime) {
        Jedis jedis = RedisLockAndCacheTool.getJedis();
        if (null == jedis) {
            LOG.error("Can not get Jedis!");
            return false;
        }

        try {
            jedis.setex(key, expireTime, value);
        } finally {
            RedisLockAndCacheTool.closeJedis(jedis);
        }

        return true;
    }

    /**
     * 删除缓存
     * @param key 键
     * @return 删除是否成功 true 成功  false 失败
     */
    public boolean delCache(String key) {
        Jedis jedis = RedisLockAndCacheTool.getJedis();
        if (null == jedis) {
            LOG.error("Can not get Jedis!");
            return false;
        }

        try {
            if (jedis.exists(key)) {
                jedis.del(key);
            }
        } finally {
            RedisLockAndCacheTool.closeJedis(jedis);
        }

        return true;
    }
}
