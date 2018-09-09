package learnproxy;

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
 * 目前来看不论target的类型是Object还是使用泛型T，都是没有问题的
 * @param <T>
 */
public class ProxyFactory<T> {

    public static Map<String, FutureTask<Object>> resultMap = new HashMap<>();

    private final String SING = "sing";

    private final String DANCE = "dance";

    private String key;

    private T target;

    public ProxyFactory(T target) {
        this.target = target;
    }

    @SuppressWarnings("unchecked")
    public T getProxy() {
        return (T)Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
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
                            // 如果返回的是基本类型，这要求第一个参数的类型和返回类型一样，而且没啥用
                            // 如果是类类型，直接返回null
                        }

                        return object;
                    }
                });
    }

    private void delayMethod(Method method, Object[] args)
            throws InvocationTargetException, IllegalAccessException {
        // todo 异步调用处理
//        System.out.println("----------async process start.");

        ExecutorService execs = Executors.newSingleThreadExecutor();
        FutureTask<Object> result =new FutureTask<>(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return method.invoke(target, args);
            }
        });
        execs.submit(result);
        resultMap.put(method.getName(), result);
//        resultMap.put(key, result);
        execs.shutdown();
    }
}
