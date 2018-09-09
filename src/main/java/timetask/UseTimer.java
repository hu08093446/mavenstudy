package timetask;


import java.util.concurrent.*;

public class UseTimer {
    private static final ScheduledExecutorService scheExec = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        scheduleAtFixedRateForRunable();

        scheduleForDelayTaskAndGetResult();

//        scheduleWithFixedDelay();
    }

    /**
     * 使用callable无法做到周期性定时运行，只执行了一次，就GG了
     */
    private static void scheduleAtFixedRate() {
        FutureTask<String> task = new FutureTask<String>(new TaskCallable());

        scheExec.scheduleAtFixedRate(task, 0, 1000, TimeUnit.MILLISECONDS);
    }

    /**
     * 这个也是周期性的执行，但是是固定的时间点，到点就执行，而不管任务的执行时间，但两个任务并不会同时执行
     */
    private static void scheduleAtFixedRateForRunable() {

        scheExec.scheduleAtFixedRate(new TaskRunable(), 10, 1, TimeUnit.SECONDS);

        // 不能使用shutdown，不然任务直接GG
//        scheExec.shutdown();
    }

    private static void scheduleForDelayTaskAndGetResult() throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<String>(new TaskCallable());

        scheExec.schedule(task, 5, TimeUnit.SECONDS);
        scheExec.schedule(new TaskRunable(), 10, TimeUnit.SECONDS);
        scheExec.submit(new TaskRunable());
        System.out.println("----------------------------" + task.get());

        scheExec.shutdown();

    }

    /**
     * 这个是周期性执行，但是固定的是delay，即上一次执行成功后（可能花了很长时间），经过delay时长，执行下一次
     */
    private static void scheduleWithFixedDelay() {
        scheExec.scheduleWithFixedDelay(new TaskRunable(), 5, 1, TimeUnit.SECONDS);
    }
}
