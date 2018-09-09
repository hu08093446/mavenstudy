package childexecption;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class FutureThread implements Callable<String> {
    // randNumber 将被赋值为一个 MIN 和 MAX 范围内的随机数
    // int randNumber = rand.nextInt(MAX - MIN + 1) + MIN;
    private static final Random random = new Random();

    private String name;

//    public FutureThread() {}

    public FutureThread(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
//        doSomethingA();
        exceptionMethod();
//        doSomethingB();

        return name;
    }

    private void doSomethingA() {
        System.out.println("-------------------------------do something A");
    }

    private void doSomethingB() {
        System.out.println("+++++++++++++++++++++++++++++++do something B");
        System.out.println(Thread.currentThread().getName());
    }

    private void exceptionMethod() {
//        String[] arr = new String[2];
//        arr[2] = "str";
        if(random.nextInt(10) < 1) {
            throw new RuntimeException("FutureThread, exceptionMethod, Lack of quota");
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
