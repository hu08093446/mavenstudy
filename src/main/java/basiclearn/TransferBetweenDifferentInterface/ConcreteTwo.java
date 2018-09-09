package basiclearn.TransferBetweenDifferentInterface;

public class ConcreteTwo implements InterfaceA, InterfaceB {
    @Override
    public String getName() {
        return "meichangsu";
    }

    @Override
    public Integer getAge() {
        return 30;
    }
}
