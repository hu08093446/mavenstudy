package basiclearn.polymorphism;

public class HappyActor extends Actor {
    @Override
    public void act() {
        System.out.println("hayypactor");
    }

    @Override
    public void sing() {
        System.out.println("happyactor sing");
    }
}
