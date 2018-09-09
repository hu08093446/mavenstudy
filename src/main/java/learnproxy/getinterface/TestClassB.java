package learnproxy.getinterface;

import org.apache.commons.lang3.ClassUtils;

import java.util.Arrays;

public class TestClassB extends TestClassA implements InterfaceB{
    public static void main(String[] args) {
        // jdk的getInterfaces获取的是当前类实现的接口，InterfaceA并不是当前类实现的，所以拿不到
        System.out.println(Arrays.toString(TestClassB.class.getInterfaces()));
        // 要想拿到整个流程中所有的接口，要用下面的方式
        System.out.println(ClassUtils.getAllInterfaces(TestClassB.class));
    }
}
