package singleton;

import java.io.Serializable;

public class MySingleton implements Serializable {

    private static final long serialVersionUID = -7887257296645379014L;

    // 使用volatile关键字保其可见性
    private static volatile MySingleton instance;

    private static String MY_NAME = "huke";

    private static Integer MY_WORK_AGE = 1;

    private static Object lock = new Object();

    private String name;

    private Integer working_age;

    /**
     * 线程安全的单例模式
     *
     * @return
     */
    public static MySingleton getInstance() {
        if (null == instance) {
            synchronized (lock) {
                if (null == instance) {
                    instance = new MySingleton(MY_NAME, MY_WORK_AGE);
                }
            }
        }

        return instance;
    }

    private MySingleton(String name, Integer working_age) {
        this.name = name;
        this.working_age = working_age;
    }

    public void print() {
        System.out.println(this);
    }

    // 保证序列化再反序列化不会生成新的实例
    private Object readResolve() {
        return instance;
    }
}
