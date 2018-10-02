package basiclearn.ExtendClassAndImplementInterface;

public class ConcreteClass extends AbstractClassA implements InterfaceA {
    // 发现没有，很神奇吧，虽然实现了InterfaceA，但是确不用明确实现print()方法
    // 因为ConcreteClass同时还继承了 AbstractClassA，就拥有了该抽象类的print()方法，就像 ConcreteClass 自己的方法一样
    // 如果把AbstractClassA中的方法声明为private，那么就必须明确实现print()方法了，因为该方法在ConcreteClass中看不到了
    // 有意思，代码还能这样写
}
