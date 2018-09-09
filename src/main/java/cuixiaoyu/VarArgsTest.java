package cuixiaoyu;

import com.alibaba.fastjson.JSON;

public class VarArgsTest {
    public static void m1(String s, String... ss) {

        System.out.println(JSON.toJSONString(ss));
        for (int i = 0; i < ss.length; i++) {
            System.out.println(ss[i]);
        }
    }

    public static void m2(String... ss) {

        System.out.println(JSON.toJSONString(ss));
        System.out.println(ss.length);
        for (int i = 0; i < ss.length; i++) {
            System.out.println(ss[i]);
        }
    }

    private static void testA() {
        m1("");
        m1("aaa");
        m1("aaa", "bbb");
        m2();
    }

    public static void main(String[] args) {
        testA();
    }
}