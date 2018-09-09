package idgenerator;

import idgenerator.mybatis.impl.MybatisSequenceStoreImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class IdGeneratorMain {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:mybatis.xml");
        MybatisSequenceStoreImpl impl = (MybatisSequenceStoreImpl) ctx.getBean("mybatisSequenceStoreImpl");

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new Task());

        // 这一段不能放在开启子线程前面，不然就是先执行完整个for循环，再开启子线程
        // 这显然不是我想要的
        for(int i = 0; i < 1001; i++) {
            TimeUnit.NANOSECONDS.sleep(10);
            System.out.println(Thread.currentThread().getName() + IdGenerator.generateId("fast"));
        }

        // shutdown会使线程优雅的结束，至少是让线程执行完自己的任务
        executorService.shutdown();

        TimeUnit.SECONDS.sleep(2);
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            for(int i = 0; i < 1001; i++) {
                try {
                    TimeUnit.NANOSECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + IdGenerator.generateId("fast"));
            }
        }
    }
}
