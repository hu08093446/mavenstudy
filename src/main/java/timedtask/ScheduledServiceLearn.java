package timedtask;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class ScheduledServiceLearn {

    private static Logger LOG = LoggerFactory.getLogger(ScheduledServiceLearn.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        useScheduledTask();
    }

    private static void useScheduledTask() throws ExecutionException, InterruptedException {
//        ScheduledExecutorService execA = Executors.newScheduledThreadPool(2,
//                new BasicThreadFactory.Builder().daemon(false).namingPattern("huke-timer-task-%d")
//                        .uncaughtExceptionHandler(new HukeExceptionHandler()).build());
//        // 异常处理器在这里并不起作用
//        execA.scheduleAtFixedRate(new MyTask(), 2, 2, TimeUnit.SECONDS);

        ExecutorService exec =
                new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(10), new BasicThreadFactory.Builder().daemon(false).namingPattern("huke-timer-task-%d")
                        .uncaughtExceptionHandler(new HukeExceptionHandler()).build());
        // 当使用submit时，异常处理器也不起作用，只有用execute时才能起作用
        FutureTask<String> result = new FutureTask<>(new MyTask());
        exec.execute(result);
        try
        {
            System.out.println(result.get());
        } catch (ExecutionException e) {
            LOG.warn("exception transfer to outside");
        } finally {
            exec.shutdown();
        }

    }

    private static class HukeExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            LOG.warn("process exception..., cause = [{}]", e.getCause());
        }
    }

    private static void printSomething() {
        String day = "Saturday";
        System.out.println(String.format("Today is %s, ThreadName is %s", day, Thread.currentThread().getName()));
        throw new RuntimeException("error.");
    }

    private static class MyTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            printSomething();
            return "success";
        }
    }
}
