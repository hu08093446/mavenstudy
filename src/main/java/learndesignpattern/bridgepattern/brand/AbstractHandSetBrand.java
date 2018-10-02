package learndesignpattern.bridgepattern.brand;

import learndesignpattern.bridgepattern.soft.HandSetSoft;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractHandSetBrand {
    protected List<HandSetSoft> softList = new ArrayList<>();

    public void addSoft(List<HandSetSoft> softs) {
        if (CollectionUtils.isNotEmpty(softs)) {
            softList.addAll(softs);
        }
    }

    abstract public void run();
}
