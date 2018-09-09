package learncache.guavacacheuseandproxy;

import learnproxy.proxynew.ProxyFactoryNew;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaCacheMain {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ApplicationContext context = new ClassPathXmlApplicationContext("learnspringcache/guavacache/app.xml");
        ConcreteService concreteService = context.getBean(ConcreteService.class);

//        testGuavaCache(concreteService);
        testProxy(concreteService);
        TimeUnit.SECONDS.sleep(1);
    }

    private static void testGuavaCache(ConcreteService concreteService) throws InterruptedException {
        System.out.println(concreteService.getPerson("huke"));
        System.out.println(concreteService.getPerson("cuixiaoyu"));
        TimeUnit.SECONDS.sleep(5);
        System.out.println(concreteService.getPerson("cuixiaoyu"));

    }

    /**
     * 双重代理清理，第一层由spring完成，第二层JDK动态代理手工完成
     * @param concreteService
     */
    private static void testProxy(ConcreteService concreteService){
        if (concreteService instanceof  ConcreteService) {
            ProxyFactoryNew.INSTANCE.getProxy(concreteService, "testA").getPerson("someone");
            ProxyFactoryNew.INSTANCE.getProxy(concreteService, "testB").getPerson("someone");
            try {
                System.out.println(ProxyFactoryNew.resultLocal.get().get("testA").get());
                System.out.println(ProxyFactoryNew.resultLocal.get().get("testB").get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                ProxyFactoryNew.resultLocal.remove();
            }

        }

    }
}
