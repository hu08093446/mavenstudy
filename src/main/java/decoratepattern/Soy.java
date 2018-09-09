package decoratepattern;

public class Soy extends CondimentDecorator {
    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Soy";
    }

    @Override
    public CUPSIZE getSize() {
        return beverage.getSize();
    }

    @Override
    public double cost() {
        CUPSIZE cupsize = getSize();
        Double costResult = beverage.cost() + Cost.Soy;
        switch (cupsize) {
            case TAIL:
                costResult = costResult + 1.01;
                break;
            case GRANDE:
                costResult = costResult + 2.01;
                break;
            case VENTI:
                costResult = costResult + 3.01;
                break;
        }
        return costResult;
    }
}
