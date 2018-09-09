package basiclearn;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.IntFunction;

public class GenericClass {
    public static void main(String[] args) {

        String[] arrA = {"a", "b", "c", "d", "e"};
        System.out.println(getMiddle(arrA));

        String[] arrB = new String[4];
        for(int i = 0; i < arrB.length; i++) {
            arrB[i] = String.valueOf(i);
        }
        System.out.println(JSON.toJSONString(arrB));

        // 这里的str只是arrB中元素的副本，对str的改变并不会影响arrB
        for(String str : arrB) {
            if(Objects.equals(str, "2")) {
                System.out.println(str);
                str = "c";
                System.out.println(str);
            }
        }
        System.out.println(JSON.toJSONString(arrB));

        System.out.println(getLength(arrB));

        System.out.println(JSON.toJSONString(createArray("hello", "world")));
        System.out.println(JSON.toJSONString(createArray("hello", "java")));

        printEnum();

    }

    // java可变类型参数是基于数组实现的，所以最终可变参数会被编译成数组
    // 因此传入一个数组也是可以的，但是传入其他的就不行
    private static <T> T getMiddle(T... a) {
        return a[a.length / 2];
    }

    // 类型参数放在修饰符的后面，返回类型的前面
    private static <T> int getLength(T... a) {
        return a.length;
    }

    // 由于不能直接创建泛型数组，所以此处采用反射的方式创建
    private static <T> T[] createArray(T a, T b) {
        T[] arr = (T[])Array.newInstance(b.getClass(), 2);
        arr[0] = a;
        arr[1] = b;

        return arr;
    }

    // 这是Java8中创建泛型数组的方式
    private static <T> T[] createArray(IntFunction<T[]> constr, T a, T b) {
        T[] arr = constr.apply(2);
        arr[0] = a;
        arr[1] = b;

        return arr;
    }

    // 通过这种反射的方式也可以获取到所有的枚举对象
    private static void printEnum() {
        GenericEnum[] enums = GenericEnum.class.getEnumConstants();
        Set<GenericEnum> enumSet = new HashSet<>(Arrays.asList(enums));
        System.out.println(enumSet);

        System.out.println(GenericEnum.isContain(4));
    }
}
