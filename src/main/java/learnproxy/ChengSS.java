package learnproxy;

import java.util.concurrent.TimeUnit;

public class ChengSS implements Person{

    private static final String SING = "ok, every one, singing end.";

    private static final String DANCE = "dancing end, thanks";

    @Override
    public String sing(String name) {
        System.out.println("cheng ss sings " + name);
        // 尝试向外抛出异常，看看会是什么情况
        // 这种未声明的异常，如果不处理，向外抛的过程中会被封装
        // IndexOutOfBoundsException -> InvocationTargetException -> UndeclaredThrowableException
        // 但是像IOException这种必须处理或者声明的异常就不会有这个问题
//        throw new IndexOutOfBoundsException("exception");
        return SING;
    }

    @Override
    public String dance(String name) {
        System.out.println("cheng ss dances " + name);
        return DANCE;
    }

    @Override
    public Integer shoot(int num) {
        int point = 0;
        for(int i = 0; i < num; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            point = 2 * point + 1;
        }
        return point;
    }

    @Override
    public String fly(String name, int num) {
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < num; i++) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            builder.append(name).append("&");
        }
        String result = builder.toString();
        return result.substring(0, result.length() - 1);
    }

    public void donothing() {
        System.out.println("+++++++++++++++++++++++++++++++++++++++");
    }
}
