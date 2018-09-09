package childexecption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threadpool.CustomThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * LOG需要配置下打印的方法名，具体行数等详细信息
 */
public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
//        runThread();
//        tryToCatchException();
        tryToUseAwaitTermationC();
    }

    /**
     * 原来futuretask的get()方法本身就可以检测任务执行是否超时
     * 那awaitTermination方法到底啥子用处？？？
     * @throws InterruptedException
     */
    private static void tryToUseAwaitTermationC() throws InterruptedException {
        final int cnt = 2;
        ExecutorService exec = CustomThreadPool.getCustomPool();
        List<FutureTask<String>> taskList = new ArrayList<>(2);
        for(int i = 0; i < cnt; i++) {
            FutureTask<String> task = new FutureTask<>(new FutureThreadRunLongTime(String.valueOf(i)));
            taskList.add(task);
        }

        for(FutureTask<String> task : taskList) {
            exec.submit(task);
        }

        // 注意，只要线程执行了，无论是否线程执行是否正常，其结果都会存放在futuretask中
        // 不论中间耽搁了多长时间，都不会有影响
        TimeUnit.SECONDS.sleep(1);

        try {
            for(FutureTask<String> task : taskList) {
                LOG.info("child thread result is [{}]", task.get(1, TimeUnit.SECONDS));
            }
            // 抛出的runtimeexception是会被封装的
        } catch (ExecutionException e) {
            LOG.error("task running occurs excetpion", e);
        } catch (TimeoutException e) {
            // log4j本身就支持占位符，但是异常不能用占位符，直接加载后面就可以了
            LOG.error("task running too long, e = [{}]","exception", e);
        } finally{
            exec.shutdown();
        }
    }

    /**
     * 该方法用于测试任务超时的情况
     * 这种方式awaitTermination的返回结果是true，因为get()方法会阻塞线程执行
     * 此方式无法检测任务执行是否超时
     * @throws InterruptedException
     */
    private static void tryToUseAwaitTermationA() throws InterruptedException {
        final int cnt = 2;
        ExecutorService exec = CustomThreadPool.getCustomPool();
        List<FutureTask<String>> taskList = new ArrayList<>(2);
        for(int i = 0; i < cnt; i++) {
            FutureTask<String> task = new FutureTask<>(new FutureThreadRunLongTime(String.valueOf(i)));
            taskList.add(task);
        }

        for(FutureTask<String> task : taskList) {
            exec.submit(task);
        }

        try {
            for(FutureTask<String> task : taskList) {
                LOG.info("child thread result is [{}]", task.get());
            }
            // 注意！！！这里不能使用RuntimeException 因为抛出的异常是被包装过的ExecutionException
            // 使用RuntimeException将无法捕获异常
        } catch (Exception e) {
            LOG.error("--------------------------catch child thread exception", e);
        } finally{
            exec.shutdown();

            System.out.println(exec.awaitTermination(1, TimeUnit.SECONDS));

        }
    }

    /**
     * 可以使用这种写法来检测线程是否超时
     * @throws InterruptedException
     */
    private static void tryToUseAwaitTermationB() throws InterruptedException {
        final int cnt = 2;
        ExecutorService exec = CustomThreadPool.getCustomPool();
        List<FutureTask<String>> taskList = new ArrayList<>(2);
        for(int i = 0; i < cnt; i++) {
            FutureTask<String> task = new FutureTask<>(new FutureThreadRunLongTime(String.valueOf(i)));
            taskList.add(task);
        }

        for(FutureTask<String> task : taskList) {
            exec.submit(task);
        }

        exec.shutdown();
        // 线程执行超时，返回了false，我们可以尝试在超时的时候 向外抛出异常
        System.out.println(exec.awaitTermination(1, TimeUnit.SECONDS));

        // 注意，只要线程执行了，无论是否线程执行是否正常，其结果都会存放在futuretask中
        // 不论中间耽搁了多长时间，都不会有影响
        TimeUnit.SECONDS.sleep(10);

        try {
            for(FutureTask<String> task : taskList) {
                LOG.info("child thread result is [{}]", task.get());
            }
        } catch (Exception e) {
            LOG.error("--------------------------catch child thread exception", e);
        } finally{
//            exec.shutdown();
//
//            System.out.println(exec.awaitTermination(1, TimeUnit.SECONDS));

        }
    }

    private static void tryToCatchException() {
        final int cnt = 20;
        ExecutorService exec = CustomThreadPool.getCustomPool();
        List<FutureTask<String>> taskList = new ArrayList<>(20);
        for(int i = 0; i < cnt; i++) {
            FutureTask<String> task = new FutureTask<>(new FutureThread(String.valueOf(i)));
            taskList.add(task);
        }

        for(FutureTask<String> task : taskList) {
            exec.submit(task);
        }

        try {
            for(FutureTask<String> task : taskList) {
                LOG.info("child thread result is [{}]", task.get());
            }
        } catch (Exception e) {
            LOG.error("--------------------------catch child thread exception", e);
//            return;
        } finally{
            exec.shutdown();
        }
    }

    private static void runThread() {
        ExecutorService exec = CustomThreadPool.getCustomPool();
        exec.submit(new ChildThread(SetThreadName.setName("test")));
        for(int i = 0; i < 20; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            exec.submit(new ChildThread());
        }

        exec.shutdown();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
