package singleton;

/**
 * 使用枚举方式的得到的单例，必定是线程安全的
 * 使用枚举已经无偿的提供了序列化机制，无须再考虑序列化的问题
 * @author Administrator
 */
public enum EnumSingleton {
    INSTANCE, SECOND("huke", 1);

    // 不能使用下面两个常量来初始化枚举实例，因为枚举实例创建时，它们还没有初始化
    private static final String MY_NAME = "huke";

    private static final Integer MY_WORK_AGE = 1;

//    private static Object lock = new Object();

    private String name;

    private Integer working_age;

    private EnumSingleton() {}

    private EnumSingleton(String name, Integer workage) {
        this.name = name;
        this.working_age = workage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWorking_age() {
        return working_age;
    }

    public void setWorking_age(Integer working_age) {
        this.working_age = working_age;
    }

    public void print() {
        System.out.println(this);
        System.out.println(this.name + " " + this.working_age);
    }

}
