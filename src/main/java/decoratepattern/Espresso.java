package decoratepattern;

public class Espresso extends Beverage {

    public Espresso() {
        this.description = "Espresso";
    }

    @Override
    protected String handleDescription() {
        return description;
    }

    @Override
    public double cost() {
        return Cost.Espresso;
    }
}
