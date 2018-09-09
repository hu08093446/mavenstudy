package futurelearn;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureCallableLearn {

    public static void main(String[] args) {
        Callable<Integer> callable = () -> {
            Thread.sleep(1000);
            return new Random().nextInt(100);
        };

        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
        new Thread(futureTask).start();

        try {
            Thread.sleep(3000);

            System.out.println(futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
