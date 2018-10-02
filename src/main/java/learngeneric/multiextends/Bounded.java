package learngeneric.multiextends;

public class Bounded extends Dimension implements HasColor, Weight {
    @Override
    public String getColor() {
        return null;
    }

    @Override
    public int weight() {
        return 0;
    }
}
