package learnsynchronized;

import java.util.concurrent.TimeUnit;

public class MethodClass {
    synchronized public void m1() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("m1 running start.");
        m2();
        TimeUnit.SECONDS.sleep(4);
        System.out.println("m1 running end.");
    }

    synchronized public void m2() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("m2 running.");
    }

    synchronized public static void m3() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("m3 running.");
    }
}
