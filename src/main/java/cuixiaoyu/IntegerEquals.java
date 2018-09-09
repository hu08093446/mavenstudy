package cuixiaoyu;

import org.springframework.util.Assert;

import java.util.Objects;

public class IntegerEquals {
    public static void main(String[] args) {
        testA();
    }

    private static void testA() {
        Integer a = 130;
        Integer b = 130;
        System.out.println(Objects.equals(a, b));
        System.out.println(a == b);
        Assert.isTrue(1 < 0, "you are wrong");
    }
}
