package learnproxy;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ProxyTest {

    public static void main(String[] args) {
        // 这段是正统代码
//        Person css = new ChengSS();
//        ProxyFactory proxyFactory = new ProxyFactory(css);

        ChengSS css = new ChengSS();
        ProxyFactory proxyFactory = new ProxyFactory(css);

        /**
         * 下面这个语句会包类型转换异常，因为生成的代理类是Person类型
         * 但却不是Chengss类型，这样转型有问题
          */

        // 关于泛型，有个疑问，为什么类型转换异常是发生在这里，而不是在getPrxy()方法返回的时候？？？
//        ChengSS proxy = (ChengSS)proxyFactory.getProxy();
        Person proxy = (Person)proxyFactory.getProxy();

        // 下面这个方法是没办法被代理的，因为它不是接口中的方法，如果要强行调用
        // 就需要proxy是ChengSS类型，那就回报类型转换异常
//        proxy.donothing();

        try {
            System.out.println(proxy.sing("go west"));
            // 异常已经被包装了，这里是抓不住的
        } catch (IndexOutOfBoundsException e)
        {
            e.printStackTrace();
        }


        System.out.println(proxy.dance("black duck"));

        proxy.shoot(5);
        proxy.fly("huke", 4);

        // 接下来就可以做我自己的事情了，和前面的代理方法是异步同时进行的
        for(int i = 0; i < 5; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("other thing" + i);
        }

        // todo 我能不能自己指定标识，而不使用方法名呢？因为同一个方法万一被调用多次呢？
        try {
            System.out.println(ProxyFactory.resultMap.get("shoot").get());
            System.out.println(ProxyFactory.resultMap.get("fly").get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
