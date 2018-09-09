package learnjava8;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class CompletableFutureLearn {

    private static Logger LOG = LoggerFactory.getLogger(CompletableFutureLearn.class);

    public static void main(String[] args) {
        try {
            testF();
        } catch (ExecutionException | InterruptedException e) {
            LOG.error("exception occurs.", e);
        }
    }

    private static void testA() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        new Thread(new Runnable() {
            @Override
            public void run () {
                //模拟执行耗时任务
                System.out.println("task doing...");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //告诉completableFuture任务已经完成
                completableFuture.complete("result");
            }
        }).start();

        //获取任务结果，如果没有完成会一直阻塞等待
        String result = completableFuture.get();
        System.out.println("计算结果:"+result);
    }

    private static void testB() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //模拟执行耗时任务
                    System.out.println("task doing...");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    throw new RuntimeException("抛异常了");
                }catch (Exception e) {
                    //告诉completableFuture任务发生异常了
                    completableFuture.completeExceptionally(e);
                }
            }
        }).start();
        //获取任务结果，如果没有完成会一直阻塞等待
        String result=completableFuture.get();
        System.out.println("计算结果:"+result);
    }

    private static void testC() throws ExecutionException, InterruptedException {
        //supplyAsync内部使用ForkJoinPool线程池执行任务
        CompletableFuture<String> completableFuture=CompletableFuture.supplyAsync(()->{
            //模拟执行耗时任务
            System.out.println("task doing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            throw new RuntimeException("抛异常了");
            //返回结果
            return "result";
        });
        LOG.info("计算结果: [{}]", completableFuture.get());
    }

    private static void testD() throws ExecutionException, InterruptedException {
        //supplyAsync内部使用ForkJoinPool线程池执行任务
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 5, 10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new BasicThreadFactory.Builder().daemon(false).namingPattern("huke-pool-%d").build());
        executor.allowCoreThreadTimeOut(true);

        CompletableFuture<String> completableFuture=CompletableFuture.supplyAsync(()->{
            //模拟执行耗时任务
            LOG.info("task doing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            throw new RuntimeException("抛异常了");
            //返回结果
            return "result";
        }, executor);
        LOG.info("计算结果: [{}]", completableFuture.get());
    }

    private static void testE() throws ExecutionException, InterruptedException {

        CompletableFuture<String> completableFuture1=CompletableFuture.supplyAsync(()->{
            //模拟执行耗时任务
            LOG.info("task1 doing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return "result1";
        });

        CompletableFuture<String> completableFuture2=CompletableFuture.supplyAsync(()->{
            //模拟执行耗时任务
            LOG.info("task2 doing...");
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("抛异常了，啦啦啦...");
            //返回结果
//            return "result2";
        });

        CompletableFuture<Object> anyResult = CompletableFuture.anyOf(completableFuture1,completableFuture2);

        LOG.info("第一个完成的任务结果: [{}]", anyResult.get());

        CompletableFuture<Void> allResult = CompletableFuture.allOf(completableFuture1,completableFuture2);

        //阻塞等待所有任务执行完成
        allResult.join();
        LOG.info("所有任务执行完成: [{}], [{}]", completableFuture1.get(), completableFuture2.get());

    }

    public static void testF() throws ExecutionException, InterruptedException {

        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(CompletableFutureLearn::functionA);

        //等第一个任务完成后，将任务结果传给参数result，执行后面的任务并返回一个代表任务的completableFuture
        CompletableFuture<String> completableFuture2 = completableFuture1.thenCompose(result -> CompletableFuture.supplyAsync(() -> functionB(result)));

        System.out.println(completableFuture2.get());

    }

    private static String functionA() {
        //模拟执行耗时任务
        System.out.println("task1 doing...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        throw new RuntimeException("抛异常了，啦啦啦...");
        //返回结果
        return "result1";
    }

    private static String functionB(String param) {
        //模拟执行耗时任务
        System.out.println("task2 doing...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //返回结果
        return param + " result2";
    }

}
