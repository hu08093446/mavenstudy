package basiclearn.TransferBetweenDifferentInterface;


public class Main {
    public static void main(String[] args) {
        funcA(new ConcreteOne());
        pringSplitLine();
        funcA(new ConcreteTwo());
    }

    private static void pringSplitLine() {
        System.out.println("----------------------------------华丽丽的分割线------------------------------");
    }

    private static void doSomething() {
        InterfaceA interfaceA = new ConcreteOne();

        Integer age = ((ConcreteOne) interfaceA).getAge();
        String name = interfaceA.getName();

        System.out.println(String.format("name is %s, age is %d", name, age));
    }

    private static void funcA(InterfaceA interfaceA) {
        String name = interfaceA.getName();
        System.out.println(String.format("name is %s", name));

        Integer age = 1;
        if (interfaceA instanceof InterfaceB) {
            age = ((InterfaceB) interfaceA).getAge();
            System.out.println(String.format("age is %d", age));
        } else {
            throw new UnsupportedOperationException("error!");
        }

    }
}
