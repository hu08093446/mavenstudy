package learncache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyCacheManager<T> {
    private Map<String, T> cache = new ConcurrentHashMap<>();

    public T getValue(String key) {
        return cache.get(key);
    }

    public void addOrUpdateCache(String key, T value) {
        cache.put(key, value);
    }

    public void evictCache(String key) {
        if(cache.containsKey(key)) {
            cache.remove(key);
        }
    }

    public void evictCache() {
        cache.clear();
    }
}
