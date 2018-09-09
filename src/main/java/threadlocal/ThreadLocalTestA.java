package threadlocal;

import java.util.concurrent.Executors;

public class ThreadLocalTestA {

    private static Integer intTest = 0;

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

                System.out.println("AAAAAAAAAAAAAAA value : " + intTest);
                intTest = 1;
                System.out.println("AAAAAAAAAAAAAAA value : " + intTest);
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

                System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB value : " + intTest);
                intTest = 2;
                System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB value : " + intTest);
            }
        }
    }
}
