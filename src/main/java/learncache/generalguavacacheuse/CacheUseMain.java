package learncache.generalguavacacheuse;

import java.util.concurrent.TimeUnit;

public class CacheUseMain {
    public static void main(String[] args) throws InterruptedException {
        useCacheA();
//        useCacheB();
    }

    private static void useCacheA() throws InterruptedException {
        System.out.println(CacheUseA.INSTANCE.getCache("huke"));
        System.out.println(CacheUseA.INSTANCE.getCache("huke"));
        TimeUnit.SECONDS.sleep(6);
        System.out.println(CacheUseA.INSTANCE.getCache("huke"));
    }

    private static void useCacheB() {
        System.out.println(CacheUseB.INSTANCE.getCache("huke"));
        System.out.println(CacheUseB.INSTANCE.getCache("huke"));
    }
}
