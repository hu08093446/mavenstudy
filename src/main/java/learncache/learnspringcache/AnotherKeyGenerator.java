package learncache.learnspringcache;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component("anotherKey")
public class AnotherKeyGenerator implements KeyGenerator{
    /**
     * 以方法名作为key
     * @param target
     * @param method
     * @param params
     * @return
     */
    @Override
    public Object generate(Object target, Method method, Object... params) {
        return method.getName();
    }
}
