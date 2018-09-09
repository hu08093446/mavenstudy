package basiclearn;

/**
 * 多态性只能体现在方法上，不能体现在字段上
 * 字段遵循就近原则，先在本类中找，找不到往父类中找
 */
class A {
    private int x = 6;
    private int y = 2;

    public A(int a) {
        x = a;
    }

    public int getz() {
        int z;
        z = x / y;
        return z;
    }

    void show() {
        System.out.println("x=" + x);
        System.out.println("y=" + y);
        System.out.println("z=" + getz());
    }
}

class B extends A {
    private int x = 3;

    private int y = 5, z;

    public B(int a) {
        super(a);
    }

    public int getz() {
        z = x + y;
        return z;
    }
}

public class ChildClassUseParentProperty {
    public static void main(String[] args) {
        A num1 = new A(10);
        B num2 = new B(9);
        num1.show();
        num2.show();
    }
}