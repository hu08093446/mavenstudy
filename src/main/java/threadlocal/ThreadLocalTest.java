package threadlocal;

import java.util.concurrent.Executors;

public class ThreadLocalTest {

    private static ThreadLocal<Integer> intTest = new ThreadLocal<Integer>();

    public static void main(String[] args) {
        System.out.println("test started!");
        Executors.newSingleThreadExecutor().execute(new ThreadA());
        Executors.newSingleThreadExecutor().execute(new ThreadB());
        System.out.println("main end.");
    }

    private static class ThreadA implements Runnable {

        public void run() {
            for(int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("AAAAAAAAAAAAAAA value : " + intTest.get());
                intTest.set(1);
                System.out.println("AAAAAAAAAAAAAAA value : " + intTest.get());
            }
        }
    }

    private static class ThreadB implements Runnable {

        public void run() {
            for(int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB value : " + intTest.get());
                intTest.set(2);
                System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB value : " + intTest.get());
            }
        }
    }
}
