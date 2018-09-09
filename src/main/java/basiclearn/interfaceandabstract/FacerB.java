package basiclearn.interfaceandabstract;

/**
 * 面壁者B妄图和三体一战，最终人类灭亡
 */
public class FacerB extends AbstractWallFacer{

    @Override
    public void resulting() {
        System.out.println("mankind disappear!");
    }

    @Override
    public void doInSecondYear() {
        System.out.println("facerB wants to battle with three body.");
    }
}
