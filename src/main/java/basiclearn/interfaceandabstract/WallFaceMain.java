package basiclearn.interfaceandabstract;

public class WallFaceMain {

    public static void main(String[] args) {
        testA();
    }

    public static void testA() {
        WallFacer[] wallFacers = {new FacerA(), new FacerB(), new FacerC(), new FacerD()};
        for (WallFacer wf: wallFacers) {
            wf.thinking();
            wf.actioning();
            wf.resulting();
            if (wf instanceof FacerA || wf instanceof FacerC) {
                wf.evaluationing();
            }
            System.out.println("------------------------------------华丽丽的分割线-------------------------------------");
        }

        WallFacer.doSomething();
        System.out.println("------------------------------------华丽丽的分割线-------------------------------------");
    }
}
