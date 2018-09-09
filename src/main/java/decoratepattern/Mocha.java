package decoratepattern;

public class Mocha extends CondimentDecorator {

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    @Override
    public CUPSIZE getSize() {
        return beverage.getSize();
    }

    @Override
    public double cost() {
        return beverage.cost() + Cost.Mocha;
    }
}
