package learndesignpattern.bridgepattern;

import learndesignpattern.bridgepattern.brand.AbstractHandSetBrand;
import learndesignpattern.bridgepattern.brand.HandSetBrandM;
import learndesignpattern.bridgepattern.brand.HandSetBrandN;
import learndesignpattern.bridgepattern.soft.HandSetAddressList;
import learndesignpattern.bridgepattern.soft.HandSetGame;
import learndesignpattern.bridgepattern.soft.HandSetSoft;

import java.util.ArrayList;
import java.util.List;

public class BridgeMain {
    public static void main(String[] args) {

        List<HandSetSoft> softList = new ArrayList<HandSetSoft>() {{
            add(new HandSetGame());
            add(new HandSetAddressList());
        }};

        AbstractHandSetBrand brandA = new HandSetBrandM();
        brandA.addSoft(softList);
        brandA.run();

        AbstractHandSetBrand brandB = new HandSetBrandN();
        brandB.addSoft(softList);
        brandB.run();
    }

}
