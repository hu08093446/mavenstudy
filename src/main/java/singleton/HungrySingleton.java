package singleton;

import java.io.Serializable;

/**
 * 最简单的饿汉单例模式
 */
public class HungrySingleton implements Serializable{

    private static final long serialVersionUID = -2837713276283086156L;

    private static String MY_NAME = "huke";

    private static Integer MY_AGE = 1;

    private static HungrySingleton instance = new HungrySingleton(MY_NAME, MY_AGE);

    private String name;

    private Integer age;

    private HungrySingleton() {

    }

    public HungrySingleton(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public static HungrySingleton getInstance() {
        return instance;
    }

    // 保证序列化再反序列化不会生成新的实例
    private Object readResolve() {
        return instance;
    }

}
