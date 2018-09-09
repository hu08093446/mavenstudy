package basiclearn.multiinheritance;

public class ConcreteClass extends BaseClass implements InterfaceA, InterfaceB {
    @Override
    public void funcA() {
        System.out.println(this.getClass().getCanonicalName() + " funcA");
        funcBase();
        funcB();
    }

    @Override
    public void funcB() {
        System.out.println(this.getClass().getCanonicalName() + " funcB");
    }
}
