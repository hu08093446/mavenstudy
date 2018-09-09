package childexecption;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChildThread implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(ChildThread.class);

    private String name;

    public ChildThread() {}

    public ChildThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        if(StringUtils.isNotEmpty(name)) {
            Thread.currentThread().setName(name);
        }
        doSomethingA();

        try {
            exceptionMethod();
        } catch (Exception e) {
            LOG.error("catching your exception, restrain yourself", e);
        }

        doSomethingB();

    }

    private void doSomethingA() {
        System.out.println("-------------------------------do something A");
    }

    private void doSomethingB() {
        System.out.println("+++++++++++++++++++++++++++++++do something B");
        System.out.println(Thread.currentThread().getName());
    }

    private void exceptionMethod() {
        String[] arr = new String[2];
        arr[2] = "str";
        throw new RuntimeException("missing shuangshuang");
    }
}
