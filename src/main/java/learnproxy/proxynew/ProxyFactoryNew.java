package learnproxy.proxynew;

import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 这样做是可以，但是每调用一个方法就要创建一个proxytarget，太浪费了
 * 能不能做到每个类只创建一个proxytarget呢？
 */
public enum ProxyFactoryNew {
    INSTANCE;
    /**
     *  以下两种初始化threadLocal的方式都是可以的，第一种更加的简洁
     */
//    public static ThreadLocal<Map<String, FutureTask<Object>>> resultLocal = ThreadLocal.withInitial(HashMap::new);
    public static ThreadLocal<Map<String, FutureTask<Object>>> resultLocal = new ThreadLocal<>();
    static {
        resultLocal.set(new HashMap<>());
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy(T target, String key) {
        List<Class<?>> classList = ClassUtils.getAllInterfaces(target.getClass());
        return (T)Proxy.newProxyInstance(target.getClass().getClassLoader(), classList.toArray(new Class[0]),
                new ProxyTarget<>(target, key));
    }

    /**
     * 静态内部代理类
     * @param <T>
     */
    private static class ProxyTarget<T> implements InvocationHandler {
        private static final String SING = "sing";

        private static final String DANCE = "dance";

        private String key;

        private T target;

        public ProxyTarget(T target, String key) {
            this.key = key;
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object object = null;

            if (Objects.equals(method.getName(), SING)) {
                // 这里的proxy对应ProxyFactory，所以这里的打印没有问题
                System.out.println("------------sync singing." + proxy);
                object = method.invoke(target, args);
            } else if (Objects.equals(method.getName(), DANCE)) {
                System.out.println("------------sync dancing.");
                object = method.invoke(target, args);
            } else {
                delayMethod(method, args);
            }

            return object;
        }

        private void delayMethod(Method method, Object[] args)
                throws InvocationTargetException, IllegalAccessException {

            ExecutorService execs = Executors.newSingleThreadExecutor();
            FutureTask<Object> result =new FutureTask<>(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    return method.invoke(target, args);
                }
            });
            execs.submit(result);
            resultLocal.get().put(key, result);
            execs.shutdown();
        }
    }

}


