package basiclearn.interfaceandabstract;

/**
 * 面壁者A无所作为，人类被俘，全体迁往澳洲
 */
public class FacerA extends AbstractWallFacer {

    @Override
    public void resulting() {
        System.out.println("all people leave their native place.");
    }

    @Override
    public void doInSecondYear() {
        System.out.println("facerA do nothing in the second year.");
    }

    public void evaluationing() {
        super.evaluationing();
        System.out.println("FacerA go to die.");
    }
}
