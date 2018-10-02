package learndesignpattern.bridgepattern.brand;

import learndesignpattern.bridgepattern.soft.HandSetSoft;

public class HandSetBrandN extends AbstractHandSetBrand {
    @Override
    public void run() {
        softList.forEach(HandSetSoft::run);
    }
}
