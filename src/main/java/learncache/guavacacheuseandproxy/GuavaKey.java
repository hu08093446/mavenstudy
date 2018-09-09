package learncache.guavacacheuseandproxy;

import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

public class GuavaKey implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        // 把方法名作为key
        return method.getName();
    }
}
