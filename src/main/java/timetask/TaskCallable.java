package timetask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class TaskCallable implements Callable<String> {
    private static final Logger LOG = LoggerFactory.getLogger(TaskCallable.class);

    @Override
    public String call() throws Exception {
        LOG.info("task is starting...");
        String result = taskMethod();

        return result;
    }

    private String taskMethod() {
        String str = "I'm watching dota2...";
        return str;
    }
}
