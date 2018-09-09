package threadpool;

import java.util.concurrent.atomic.AtomicInteger;

class MyThread implements Runnable{
    private static AtomicInteger num = new AtomicInteger(0);

    private String name;

    public MyThread(String name) {
        num.incrementAndGet();
        this.name = name;
    }
    @Override
    public void run() {
//        while(true){
//            synchronized(MyThread.class){
//                ++num;
//                try{
//                    Thread.sleep(500);
//                } catch(Exception e){
//                    System.out.println(e.toString());
//                }
//                System.out.println(Thread.currentThread().getName() + " " + num);
//            }
//        }
        System.out.println(Thread.currentThread().getName() + " " + num + " " + name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}