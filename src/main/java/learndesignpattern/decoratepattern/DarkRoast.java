package learndesignpattern.decoratepattern;

public class DarkRoast extends Beverage {
    public DarkRoast() {
        this.description = "DarkRoast";
    }

    @Override
    protected String handleDescription() {
        return description;
    }

    @Override
    public double cost() {
        return Cost.Dark_Roast;
    }
}
