package threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureTaskLearn {

    private static final List<String> rlist = new ArrayList<String>(){{
        add("A1");
        add("A2");
        add("B1");
        add("B2");
    }};

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        testCustomThreadPool();
//        doSomeThing("huke", 4);
//        getSomeResult();
    }

    private static void testCustomThreadPool() {
        ExecutorService exes = CustomThreadPool.getCustomPool();
        ExecutorService anotherExes = CustomThreadPool.getCustomPool();

        try {
            for(int i=0; i<50; ++i) {
                TimeUnit.MILLISECONDS.sleep(100);
                exes.submit(new MyThread("exes"));

                TimeUnit.MILLISECONDS.sleep(100);
                anotherExes.submit(new MyThread("anotherExes"));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            exes.shutdown();
        } finally {
            exes.shutdown();
        }
    }

    /**
     * 如果做一件事情比较耗时，而且我们不需要它实时做出响应， 那么可以从线程池中拿一个线程来执行它
     * 首先要把这件事情封装到一个Runnable或者Callable
     */

    private static void doSomeThing(String text, Integer num) throws InterruptedException {

        ExecutorService exes = CustomThreadPool.getCustomPool();
        exes.submit(new SomeThing(text, num));
        // 线程开启之后，我们就可以去做别的事情了
        for(String str : rlist) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("=========" + str + "-------------");
        }

        exes.shutdown();
        System.out.println("This is the end...");
    }

    private static void getSomeResult() throws InterruptedException, ExecutionException {

        ExecutorService exes = CustomThreadPool.getCustomPool();
        FutureTask<Integer> task =new FutureTask<>(new SomeResult());
        exes.submit(task);

        // 线程开启之后，我们就可以去做别的事情了
        for(String str : rlist) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("=========" + str + "-------------");
        }

        // 做完别的事情后，拿一下线程的返回结果
        System.out.println("the final someresult is " + task.get());

        for(String str : rlist) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("=========" + str + "-------------");
        }

        // shutdown只会优雅的结束exes线程池中的线程，对其他线程没有任何影响
        exes.shutdown();
        System.out.println("This is the end...");
    }

    private static class SomeThing implements Runnable {

        private String text = "fuck";

        private Integer count = 10;

        public SomeThing() {

        }

        public SomeThing(String text, Integer count) {
            this.text = text;
            this.count = count;
        }

        @Override
        public void run() {
            for(int i = 0; i < count; i++) {
                System.out.println(text + " " + i + " " + text.toUpperCase());
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class SomeResult implements Callable<Integer> {
        private String text = "fuck";

        private Integer count = 10;

        public SomeResult() {

        }

        public SomeResult(String text, Integer count) {
            this.text = text;
            this.count = count;
        }

        @Override
        public Integer call() throws Exception {
            Integer length = 0;

            for(int i = 0; i < count; i++) {
                TimeUnit.SECONDS.sleep(2);
                length = length + text.length();
            }

            return length;
        }
    }
}