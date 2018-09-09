package basiclearn.interfaceandabstract;

public abstract class AbstractWallFacer implements WallFacer {

    @Override
    public void thinking() {
        System.out.println("wallfacers are thinking about how to save the planet");
    }

    @Override
    public void actioning() {
        doInFirstYear();
        doInSecondYear();
    }

    private void doInFirstYear() {
        System.out.println("Facers receive assistance from all over the world");
    }

    public void evaluationing() {
        System.out.println("mankind has nothing to say.");
    }

    public abstract void doInSecondYear();
}
