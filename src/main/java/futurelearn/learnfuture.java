package futurelearn;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class learnfuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        funcA();
        System.out.println(Thread.currentThread().getName());
//        funcB();
    }

    public static void funcA() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> cale(40));
        System.out.println(future.get());
    }

//    public static void funcB() throws ExecutionException, InterruptedException {
//        System.out.println(Thread.currentThread().getName());
//        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println(Thread.currentThread().getName());
//            return cale(50);
//        }).thenApply(i -> {
//            System.out.println(Thread.currentThread().getName());
//            return Integer.toString(i);
//        }).thenApply(str -> {
//            System.out.println(Thread.currentThread().getName());
//            return "\"" + str + "\"";
//        }).thenApply(str -> {
//            System.out.println(Thread.currentThread().getName());
//            return str;
//        }).exceptionally(ex -> {
//            System.out.println(ex.toString());
//            return 1;
//        });
//
//        CompletableFuture<Void> futureA= CompletableFuture
//                .supplyAsync(() -> cale(50))
//                .exceptionally(ex -> {
//                    System.out.println("ex.toString() = " + ex.toString());
//                    return 0;
//                })
//                .thenApply(i -> Integer.toString(i))
//                .thenApply(str -> "\"" + str + "\"")
//                .thenAccept(System.out::println);
//
//        System.out.println(futureA.get());
//    }

    public static Integer cale(Integer para) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return para + para;
    }
}
