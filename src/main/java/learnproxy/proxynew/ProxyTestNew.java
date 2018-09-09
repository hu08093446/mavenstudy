package learnproxy.proxynew;

import learnproxy.ChengSS;
import learnproxy.Person;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ProxyTestNew {
    public static void main(String[] args) {
        Person css = new ChengSS();
        ProxyFactoryNew.INSTANCE.getProxy(css, "shootyuyu").shoot(5);
        ProxyFactoryNew.INSTANCE.getProxy(css, "shootyu").shoot(6);

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
            System.out.println(ProxyFactoryNew.resultLocal.get().get("shootyuyu").get());
            System.out.println(ProxyFactoryNew.resultLocal.get().get("shootyu").get());
//            System.out.println(ProxyFactory.resultMap.get("fly").get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            ProxyFactoryNew.resultLocal.remove();
        }
    }
}
