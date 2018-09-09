package timedtask;

import java.util.Timer;
import java.util.TimerTask;

public class Timerlearn {

    public static void main(String[] args) {
        useTimer();
    }

    private static void useTimer() {
        new Timer("huke").schedule(new MyTimerTask(), 2000, 2000);
    }

    private static void printSomething() {
        String day = "Saturday";
        System.out.println(String.format("Today is %s, ThreadName is %s", day, Thread.currentThread().getName()));
    }

    private static class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            printSomething();
        }
    }
}
