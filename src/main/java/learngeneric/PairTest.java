package learngeneric;

import java.util.ArrayList;
import java.util.List;

public class PairTest {

    public static void main(String[] args) {
        ArrayList wordList = new ArrayList<String>() {{
            add("words");
            add("had");
            add("huke");
            add("yunguai");
            add("apple");
        }};

        ArrayList ageList = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(-1);
            add(10);
        }};

        Pair<String, Integer> pairResult = ArrayAlg.minMax(wordList, ageList);

        System.out.println("result is " + pairResult);

    }
}

class ArrayAlg {
    /**
     * 获取数组中的最大值和最小值
     */
    private static final int MIN_INDEX = 0;

    private static final int MIN_0 = 0;

    public static Pair<String, Integer> minMax(List<String> strList, List<Integer> intList) {

        Single<String> strRes = min(strList);

        Single<Integer> intRes = max(intList);

        return new Pair<>(strRes.getData(), intRes.getData());

    }

    private static Single<String> min(List<String> strList) {
        String min;
        if (null == strList || strList.isEmpty()) {
            return new Single<>(null);
        }
        min = strList.get(MIN_INDEX);

        for (String str : strList) {
            min = (str.compareTo(min) < MIN_0) ? str : min;
        }

        return new Single<>(min);
    }

    private static Single<Integer> max(List<Integer> intList) {
        Integer max;

        if(null == intList || intList.isEmpty()) {
            return new Single<>(null);
        }

        max = intList.get(MIN_INDEX);

        for(Integer intPer : intList) {
            max = (max < intPer) ? intPer : max;
        }

        return new Single<>(max);
    }
}
