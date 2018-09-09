package learncache.learnspringcache;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component("myKeyGenerator")
public class MykeyGenerator implements KeyGenerator{
    @Override
    public Object generate(Object target, Method method, Object... params) {
        return SimpleKey.EMPTY;
    }
}
