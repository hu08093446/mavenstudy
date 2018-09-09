package timetask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskRunable implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(TaskRunable.class);
    @Override
    public void run() {
        taskMethod();
    }

    private void taskMethod() {
        LOG.info("runnable task is running...");
    }
}
