package threadpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 这也是一种单例模式
 */
public class CustomThreadPool {
    private static ExecutorService customPool;

    public static ExecutorService getCustomPool() {
        return customPool;
    }

    static {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(20);
        customPool =
                new ThreadPoolExecutor(
                        5, 5, 60, TimeUnit.MICROSECONDS, queue, new CustomThreadFactory());
    }

    private static class CustomThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        CustomThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "huke thread pool - " + poolNumber.getAndIncrement() + " -thread- ";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }

            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }

            return t;
        }
    }
}
