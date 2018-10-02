package learndesignpattern.bridgepattern.brand;


import org.apache.commons.collections4.ListUtils;

import java.util.Collections;

public class HandSetBrandM extends AbstractHandSetBrand{
    @Override
    public void run() {
        Collections.reverse(softList);
        softList.forEach(soft -> soft.run());
    }
}
