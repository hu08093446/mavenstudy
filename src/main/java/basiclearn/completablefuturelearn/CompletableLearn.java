package basiclearn.completablefuturelearn;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class CompletableLearn {

    private static ReentrantLock lock = new ReentrantLock();
    private static ExecutorService exec = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        //        testCompletable();
//        testFutureTask();
        testCatchExecptionForCompletable();
    }

    private static void testCatchExecptionForCompletable() {
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
            try {
                throwSomeException();
            } catch (IOException e) {
                throw new RuntimeException("tranfer to unchecked exception");
            }
            return null;
        });

        try {
            result.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
    // CompletableFuture目前看无法将受查异常向外抛出，必须在内部处理，非受查异常是可以的
    private static String throwSomeException() throws IOException {
//        throw new RuntimeException("unchecked exception");
        throw new IOException("checked exception");
    }

    // CompletableFuture目前看无法将受查异常向外抛出，必须在内部处理，非受查异常是可以的
    // 但是FutureTask可以将内部的受查异常封装为ExecutionException后向外抛出
    private static void testCompletable() throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            try {
                anyWay();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
        TimeUnit.MILLISECONDS.sleep(10);
        anyWay();
        TimeUnit.SECONDS.sleep(1);
    }

    private static void anyWay() throws InterruptedException {
        if (!lock.tryLock(3, TimeUnit.SECONDS)) {
            return;
        }
        lock.lockInterruptibly();
        try {
            TimeUnit.SECONDS.sleep(4);
            System.out.println("hello, lock, " + Thread.currentThread().getName());

        } finally {
            lock.unlock();
        }
    }

    private static void testFutureTask() {
        FutureTask<String> task = new FutureTask<>(new Task());
        exec.submit(task);

        try {
            task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            exec.shutdown();
        }
    }

    private static class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            throw new IOException("checked exception");
//            return "success";
        }
    }
}
