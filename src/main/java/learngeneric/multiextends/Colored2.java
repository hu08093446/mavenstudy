package learngeneric.multiextends;

public class Colored2<T extends HasColor> extends HoldItem<T> {
    Colored2(T item) {
        super(item);
    }

    String getColor() {
        return item.getColor();
    }
}
