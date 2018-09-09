package learnproxy.proxygood;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
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
public class ProxyFactoryNew {
    /**
     *  下面有四种初始化方式，前三种都可以，第四种是错误的
     *  虽然是static，但因为是threadlocal，所以每个线程都会有一份，互不影响
     *  这里的hashmap必须是new出来的，如果返回一个固有的static类型的hashmap，那么不同的线程将会公用同一个hashmap，会有问题，除非每个
     *  线程再重新set一个hashMap进去
     */
    public static ThreadLocal<Map<String, FutureTask<Object>>> resultLocal = ThreadLocal.withInitial(HashMap::new);

//    private static ThreadLocal<Map<String, FutureTask<Object>>> resultLocal =
//            ThreadLocal.withInitial(() -> new HashMap<>());

//    public static ThreadLocal<Map<String, FutureTask<Object>>> resultLocal =
//            new ThreadLocal<Map<String, FutureTask<Object>>>(){
//                @Override
//                protected Map<String, FutureTask<Object>> initialValue() {
//                    return new HashMap<>();
//                }
//            };

    //下面这种方式是不对的，会有问题的，不要这样写
//    private static ThreadLocal<Map<String, FutureTask<Object>>> resultLocal = new ThreadLocal<>();
//    static {
//        resultLocal.set(new HashMap<>());
//    }

    public static ThreadLocal<Map<String, FutureTask<Object>>> getLocal() {
        return resultLocal;
    }

    public static void clearLocal() {
        resultLocal.remove();
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy(T target) {
        return (T)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new ProxyTarget<>(target));
    }

    /**
     * 静态内部代理类
     * @param <T>
     */
    public static class ProxyTarget<T> implements InvocationHandler {
        private static final String SING = "sing";

        private static final String DANCE = "dance";

        private static String key;

        private T target;

        public static void setKey(String value) {
            key = value;
        }

        public ProxyTarget(T target) {
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


