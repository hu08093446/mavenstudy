package learndynamicprop;

import idgenerator.util.SpringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import threadpool.CustomThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class PropTest {

    public static void main(String[] args) {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("classpath:learndynamicprop/context.xml");

        Person person = SpringUtils.getBean("person");

        doTestProp(person);
    }

    private static void doTestProp(Person person) {
        ExecutorService execs = CustomThreadPool.getCustomPool();
        // 先试下一个线程
        execs.submit(new TestAction(person));

        // 第二个线程
        execs.submit(new TestAction(person));

        execs.shutdown();
    }

    private static class TestAction implements Runnable {

        private static final Integer MAX = 10;

        private static final AtomicInteger COUNT = new AtomicInteger(0);

        Person person;

        public TestAction(Person person) {
            this.person = person;
        }

        @Override
        public void run() {

            while (true) {

                if (COUNT.getAndIncrement() >= MAX) {
                    break;
                }

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(
                        Thread.currentThread().getName() + " name = " + person.getName());
                System.out.println("age = " + person.getAge());
            }
        }
    }
}
