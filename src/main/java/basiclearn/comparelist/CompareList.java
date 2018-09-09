package basiclearn.comparelist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CompareList {

    public static <T extends Comparable<T>> boolean compare(List<T> listA, List<T>listB) {
        if (null == listA && null == listB) {
            return true;
        } else if (!(null != listA && null != listB)) {
            return false;
        }

        if (listA.size() != listB.size()) {
            return false;
        }

        List<T> listC = new ArrayList<>(listA);
        List<T> listD = new ArrayList<>(listB);

        Collections.sort(listC);
        Collections.sort(listD);

        for (int i = 0; i < listC.size(); i++) {
            if(!Objects.equals(listC.get(i), listD.get(i))) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        List<Person> listA = new ArrayList<>(3);
        listA.add(new Person("shuangshuang", 26));
        listA.add(new Person("huke", 27));
        listA.add(new Person("god", 10000));

        List<Person> listB = new ArrayList<>(4);
        listB.addAll(listA);
        listB.set(2, new Person("g", 10000));
        System.out.println(compare(listA, listB));
        System.out.println(listA);
        System.out.println(listB);
    }
}
