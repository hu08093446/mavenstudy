package learncache.generalguavacacheuse;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * guava cache使用方式A
 */
public enum CacheUseA {
    INSTANCE;

    private static Logger LOG = LoggerFactory.getLogger(CacheUseA.class);

    private Cache<String, Employee> myCache = CacheBuilder.newBuilder().
            maximumSize(10).expireAfterAccess(5, TimeUnit.SECONDS).build();

    public Employee getCache(String key) {
        try {
            return myCache.get(key, new MyTask(key));
        } catch (ExecutionException e) {
            LOG.error("Get emploee info from guava cache occurs error!");
        }

        return null;
    }

    private static class MyTask implements Callable<Employee> {
        private String key;

        MyTask(String key) {
            this.key = key;
        }

        @Override
        public Employee call() throws Exception {
            LOG.info("get employee from mytask");
            return new Employee(key, key.length()*2);
        }
    }
}
