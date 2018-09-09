package learncache.rediscache;

import com.alibaba.fastjson.JSON;
import learncache.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.Callable;

public class SystemRedisCache implements Cache {
    /**
     * Redis
     */
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 缓存名称
     */
    private String name;

    /**
     * 超时时间
     */
    private long timeout;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        return redisTemplate;
    }

    /**
     * 该方法比较有局限性，只能用于存储User类型，这显然是不合适的
     * 应该可以使用下面的get方法，但是具体怎么用，还不太清楚
     * @param key
     * @return
     */
    @Override
    public ValueWrapper get(Object key) {
        if (null == key) {
            return null;
        } else {
            final String finalKey;
            if (key instanceof String) {
                finalKey = (String) key;
            } else {
                finalKey = key.toString();
            }
            Object object = null;
            object = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    byte[] key = finalKey.getBytes();
                    byte[] value = connection.get(key);
                    if (value == null) {
                        return null;
                    }
                    return JSON.parseObject(new String(value), User.class);
                }
            });
            return (object != null ? new SimpleValueWrapper(object) : null);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(Object key, Class<T> type) {
        return null;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        if (null == key || null == value) {
            return;
        } else {
            final String finalKey;
            if (key instanceof String) {
                finalKey = (String) key;
            } else {
                finalKey = key.toString();
            }
            if (!StringUtils.isEmpty(finalKey)) {
                final Object finalValue = value;
                redisTemplate.execute(new RedisCallback<Boolean>() {
                    @Override
                    public Boolean doInRedis(RedisConnection connection) {
                        connection.set(finalKey.getBytes(), JSON.toJSONString(finalValue).getBytes());
                        // 设置超时间
                        connection.expire(finalKey.getBytes(), timeout);
                        return true;
                    }
                });
            }
        }
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        return null;
    }

    @Override
    public void evict(Object key) {
        if (null == key) {
            return;
        } else if (null != key) {
            final String finalKey;
            if (key instanceof String) {
                finalKey = (String) key;
            } else {
                finalKey = key.toString();
            }
            if (!StringUtils.isEmpty(finalKey)) {
                redisTemplate.execute(new RedisCallback<Long>() {
                    @Override
                    public Long doInRedis(RedisConnection connection) throws DataAccessException {
                        return connection.del(finalKey.getBytes());
                    }
                });
            }
        }
    }

    @Override
    public void clear() {

    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}
