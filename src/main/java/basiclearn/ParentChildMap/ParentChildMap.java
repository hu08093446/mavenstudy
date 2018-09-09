package basiclearn.ParentChildMap;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class ParentChildMap {

    private static final int INDEX_FIRST = 0;

    private static final int INDEX_SECOND = 1;

    public static void main(String[] args) {
        methodA();
    }

    private static void methodA()
    {
        Map<String, Map<String, Integer>> pcMap = produceParentChildMap();
        System.out.println(JSON.toJSONString(pcMap));

    }

    private static Map<String, Map<String, Integer>> produceParentChildMap()
    {
        List<String> originList = produceStringList();
        Map<String, Map<String, Integer>> parentMap = new HashMap<>();

        for (String str : originList)
        {
            Map<String, Integer> childMap =
                    parentMap.computeIfAbsent(str.substring(INDEX_FIRST, INDEX_FIRST + 1), k -> new HashMap<>());

            childMap.merge(str.substring(INDEX_SECOND, INDEX_SECOND + 1), 1, (a, b) -> a + b);
        }

        return parentMap;
    }

    private static List<String> produceStringList()
    {
        String [] strArr = {"1a", "1b", "1c", "1a", "1b", "3c",
                            "2a", "2b", "2c",
                            "3a", "3b", "3c"};
        List<String> list = new ArrayList<>(Arrays.asList(strArr));

        return list;
    }


}
