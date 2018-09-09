package threadlocal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huke
 * threadlocal如果引用了一个static变量，那么不好意思，仍旧不是线程安全的
 */
public class ThreadLocalForStaticVariable {
    private static ThreadLocal<List<String>> listLocal = new ThreadLocal<>();

    private static List<String> strList = new ArrayList<>();

    /**
     * 如果threadlocal引用的是类中的静态变量，那么两个线程的threadlocalmap存储
     * 的value仍旧是同一个List，这样两个线程仍旧共享同一个List，仍然会相互影响，无法保证线程安全
     * @param args
     */
    public static void main(String[] args){
        new Thread(() -> {
            strList.add("张三");
            strList.add("李四");
            strList.add("王五");
            listLocal.set(strList);
            System.out.println(Thread.currentThread().getName());
            listLocal.get().forEach(param -> System.out.println(param));
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                strList.add("Chinese");
                strList.add("English");
                listLocal.set(strList);
                System.out.println(Thread.currentThread().getName());
                listLocal.get().forEach(param -> System.out.println(param));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


}
