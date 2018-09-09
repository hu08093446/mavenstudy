package singleton;

import threadpool.CustomThreadPool;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class TestSingleton {
    public static void main(String[] args) {
        EnumSingleton.INSTANCE.print();
        EnumSingleton.SECOND.print();

        test();

        File directory = new File("");
        System.out.println(directory.getAbsolutePath());
        System.out.println(System.getProperty("user.dir"));
        System.out.println(TestSingleton.class.getResource("").getPath());
    }

    private static void test() {
        ExecutorService exes = CustomThreadPool.getCustomPool();
        for (int i = 0; i < 50; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            exes.submit(new SingletonThread());
        }

        exes.shutdown();
    }
}

class SingletonThread implements Runnable {

    private MySingleton instance = null;

    @Override
    public void run() {
        instance = MySingleton.getInstance();
        System.out.println(instance.hashCode());
    }
}
