package learnproxy.proxygood;

import learnproxy.ChengSS;
import learnproxy.Person;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProxyTestNew {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(5);
        exec.submit(new MyRun());
        exec.submit(new MyRun());

        exec.shutdown();
    }

    private static class MyRun implements Runnable {
        @Override
        public void run() {
            ProxyFactoryNew factoryNew = new ProxyFactoryNew();
            Person css = new ChengSS();
            Person proxy = factoryNew.getProxy(css);

            try {
                ProxyFactoryNew.ProxyTarget.setKey("shootyuyu");
                proxy.shoot(5);

                ProxyFactoryNew.ProxyTarget.setKey("shootyu");
                proxy.shoot(6);

                // 接下来就可以做我自己的事情了，和前面的代理方法是异步同时进行的
                for (int i = 0; i < 5; i++) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("other thing" + i);
                }

                // todo 我能不能自己指定标识，而不使用方法名呢？因为同一个方法万一被调用多次呢？
                System.out.println(ProxyFactoryNew.getLocal().get().get("shootyuyu").get());
                System.out.println(ProxyFactoryNew.getLocal().get().get("shootyu").get());
                //            System.out.println(ProxyFactory.resultMap.get("fly").get());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ProxyFactoryNew.clearLocal();
            }
        }
    }
}
