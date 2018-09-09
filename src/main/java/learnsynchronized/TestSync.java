package learnsynchronized;

public class TestSync {

    public static void main(String[] args) throws InterruptedException {
        testA();
    }

    /**
     * 本方式重在说明：
     * 1、内置锁是可重入的
     * 2、synchronized是对方法调用所在的对象加锁
     * 3、静态的synchronized方法以类的Class对象作为锁
     * 4、线程在start或者提交之后只是出于runnable（可运行态），并不是立即执行的
     * @throws InterruptedException 异常喽
     */
    private static void testA() throws InterruptedException {
        MethodClass method = new MethodClass();
        new Thread(new Task(method)).start();
//        method.m2();
        MethodClass.m3();
    }



    private static class Task implements Runnable {

        private MethodClass method;

        public Task(MethodClass method) {
            this.method = method;
        }

        @Override
        public void run() {
            try {
                method.m1();
            } catch (InterruptedException e) {
                System.out.println("Task occurs exception.");
            }
        }
    }
}
