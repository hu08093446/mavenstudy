package learncallable;


import java.util.concurrent.*;

public class Dota {


    public static Callable<String> play() {
        System.out.println("main thread start");

        Callable<String> result = () -> {
            System.out.println("vice thread start");
            Thread.sleep(1000);
            System.out.println("vice thread end");
            return "success";
        };
        FutureTask<String> futureTask = new FutureTask<String>(result);
        new Thread(futureTask).start();


        System.out.println("main thread end");
        return result;
    }

    public static void roa() {
        System.out.println(Thread.currentThread().getName() + " main thread start");

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> result = executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " vice thread start");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " vice thread end");
            return "success";
        });


        System.out.println(Thread.currentThread().getName() + " main thread end");
    }
}