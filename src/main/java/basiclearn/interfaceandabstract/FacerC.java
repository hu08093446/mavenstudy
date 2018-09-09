package basiclearn.interfaceandabstract;

/**
 * 面壁者C成为执剑人，随时准备毁灭地球和三体星系，以此要挟三体
 */
public class FacerC extends AbstractWallFacer {

    @Override
    public void resulting() {
        System.out.println("Three people survived.");
    }

    @Override
    public void doInSecondYear() {
        System.out.println("facerC holds the sword, threating three body.");
    }

    public void evaluationing() {
        System.out.println("FacerC say nothing, but he stands firmly.");
    }
}
