package decoratepattern;

public abstract class Beverage {
    protected String description = "Unknown Beverage";

    protected CUPSIZE size = CUPSIZE.GRANDE;

    public void setDescription(String description) {
        this.description = description;
    }

    public CUPSIZE getSize() {
        return size;
    }

    public void setSize(CUPSIZE size) {
        this.size = size;
    }

    public String getDescription() {
        return handleDescription();
    }

    protected String handleDescription() {
        this.description = "Unknown Beverage";
        return description;
    }

    public abstract double cost();
}
