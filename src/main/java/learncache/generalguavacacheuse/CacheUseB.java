package learncache.generalguavacacheuse;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * guava使用方式B
 */
public enum CacheUseB {
    INSTANCE;

    private static Logger LOG = LoggerFactory.getLogger(CacheUseB.class);

    private LoadingCache<String, Employee> myCache = CacheBuilder.newBuilder()
            .maximumSize(10).expireAfterAccess(5, TimeUnit.SECONDS).build(createCacheLoader());

    private CacheLoader<String, Employee> createCacheLoader() {
        return new CacheLoader<String, Employee>() {
            @Override
            public Employee load(String key) {
                LOG.info("加载创建key:" + key);
                return new Employee(key, key.length() * 3);
            }
        };
    }

    public Employee getCache(String key) {
        try {
            return myCache.get(key);
        } catch (ExecutionException e) {
            LOG.info("get cache error!");
        }
        return null;
    }

    public void deleteCache(String key) {
        myCache.invalidate(key);
    }
}
