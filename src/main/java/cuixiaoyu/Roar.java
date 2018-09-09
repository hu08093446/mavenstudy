package cuixiaoyu;

public class Roar {

    public static void main(String[] args) {
        animal(new Cat());
    }

    private static void animal(Animal animal) {
        animal.roar();
    }

}
