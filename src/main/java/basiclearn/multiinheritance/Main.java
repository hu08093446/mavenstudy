package basiclearn.multiinheritance;

/**
 * 通过concrete只能直接调用funcA() （因为声明的类型是InterfaceA，其他接口和类的方法在编译期间无法编译通过）
 * 但是在funcA()的内部是可以调用ConcreteClass拥有的所有方法的
 * 因为concrete的实际类型是ConcreteClass，而且实际的方法调用时通过this指针进行的
 */
public class Main {
    public static void main(String[] args) {
        InterfaceA concrete = new ConcreteClass();
        concrete.funcA();
    }
}
