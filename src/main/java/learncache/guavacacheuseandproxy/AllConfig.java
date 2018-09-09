package learncache.guavacacheuseandproxy;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class AllConfig {

    // 自定义的key生成器
    @Bean
    public GuavaKey guavaKey() {
        return new GuavaKey();
    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        Cache guavacacheA = new GuavaCache("guavacacheA",
                CacheBuilder.newBuilder().expireAfterWrite(3, TimeUnit.SECONDS).build(), false);

        List<Cache> cacheList = new ArrayList<>();
        cacheList.add(guavacacheA);
        cacheManager.setCaches(cacheList);

        return cacheManager;
    }
}
