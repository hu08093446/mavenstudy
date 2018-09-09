package localcache;

import java.util.HashMap;
import java.util.Map;

public class LocalCache {
    private static ThreadLocal<Map<String, Object>> local = new ThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    @SuppressWarnings("unchecked")
    public static <T> T get(String key) {
        return (T)local.get().get(key);
    }

    public static void set(String key, Object value) {
        local.get().put(key, value);
    }

    public static void clear(String key) {
        local.get().remove(key);
    }
}
