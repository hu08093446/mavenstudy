package basiclearn.TransferBetweenDifferentInterface;

public class ConcreteOne implements InterfaceA, InterfaceB {
    @Override
    public String getName() {
        return "huke";
    }

    @Override
    public Integer getAge() {
        return 27;
    }
}
