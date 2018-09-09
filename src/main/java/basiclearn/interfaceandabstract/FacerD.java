package basiclearn.interfaceandabstract;

/**
 * 面壁人D叛变，地球被三体入侵，前途未卜
 */
public class FacerD extends AbstractWallFacer {

    @Override
    public void thinking() {
        System.out.println("FacerD thinks nothing.");
    }

    @Override
    public void actioning() {
        System.out.println("FacerD defects mankind.");
    }

    @Override
    public void resulting() {
        System.out.println("Earth is occupied by three body.");
    }

    @Override
    public void doInSecondYear() {
        throw new UnsupportedOperationException("FacerD do noting in the second year.");
    }
}
