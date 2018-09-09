package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
    private static volatile JedisPool jedisPool = null;

    private static Object object = new Object();

    private JedisPoolUtil() {}

    public static JedisPool getJedisPoolInstance() {
        // 此处采用双重检查机制，因为可能会被多线程访问
        if (null == jedisPool) {
            synchronized (object) {
                if (null == jedisPool) {
                    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                    // 最大连接数
                    jedisPoolConfig.setMaxTotal(1000);
                    // 最大空闲连接数
                    jedisPoolConfig.setMaxIdle(32);
                    // 获取连接时最大等待毫秒数
                    jedisPoolConfig.setMaxWaitMillis(10 * 1000);
                    // 获取连接时检查有效性
                    jedisPoolConfig.setTestOnBorrow(true);

                    jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
                }
            }
        }

        return jedisPool;
    }

    public static void release(Jedis jedis) {
        if(null != jedis) {
            jedis.close();
        }
    }

    public static void clear(Jedis jedis) {
        if(null != jedis) {
            jedis.flushAll();
        }
    }
}
