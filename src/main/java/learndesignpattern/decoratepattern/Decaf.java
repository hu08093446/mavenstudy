package learndesignpattern.decoratepattern;

public class Decaf extends Beverage {

    public Decaf() {
        this.description = "Decaf";
    }

    @Override
    protected String handleDescription() {
        return description;
    }

    @Override
    public double cost() {
        return Cost.Decaf;
    }
}
