package removecollection;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class RmHashMap {
    public static void main(String[] args) {
        Map<String, Integer> rmMap =
                new HashMap<String, Integer>() {
                    {
                        put("santi", 1);
                        put("deathforever", 2);
                        put("darkforest", 3);
                    }
                };

        //        traverseMap(rmMap);
        //        rmMapTest(rmMap);

        multiOpeMap();
    }

    public static void multiOpeMap() {
//        HashMap<HashMap<String, Integer>, Integer> myHashMap = new HashMap<>();
//        HashMap<String, Integer> temp = new HashMap<>();
//        temp.put("1", 1);
//        temp.put("2", 2);
//        myHashMap.put(temp, 3);

        //        for (Iterator<Map.Entry<HashMap<String, Integer>, Integer>>
        //             it = myHashMap.entrySet().iterator(); it.hasNext();){
        //            Map.Entry<HashMap<String, Integer>, Integer> item = it.next();
        //            item.getKey().remove("1");
        //            System.out.println(myHashMap.size());
        //            it.remove();
        //            System.out.println(myHashMap.size());
        //        }

        //        for (Iterator<Map.Entry<HashMap<String, Integer>, Integer>> it =
        //                        myHashMap.entrySet().iterator();
        //                it.hasNext(); ) {
        //            Map.Entry<HashMap<String, Integer>, Integer> item = it.next();
        //            // item.getKey().remove("1");
        //            HashMap<String, Integer> to_put = new HashMap<>(item.getKey());
        //            to_put.remove("1");
        //            myHashMap.put(to_put, item.getValue());
        //            System.out.println(myHashMap.size());
        //            it.remove();
        //            System.out.println(myHashMap.size());
        //        }

        ConcurrentHashMap<HashMap<String, Integer>, Integer> myHashMap = new ConcurrentHashMap<>();
        HashMap<String, Integer> temp = new HashMap<>();
        temp.put("1", 1);
        temp.put("2", 2);
        myHashMap.put(temp, 3);

        for (Iterator<Map.Entry<HashMap<String, Integer>, Integer>> it =
                        myHashMap.entrySet().iterator();
                it.hasNext(); ) {
            Map.Entry<HashMap<String, Integer>, Integer> item = it.next();
            // item.getKey().remove("1");
            HashMap<String, Integer> to_put = new HashMap<>(item.getKey());
            to_put.remove("1");
            myHashMap.put(to_put, item.getValue());
            System.out.println(myHashMap.size());
            it.remove();
            System.out.println(myHashMap.size());
        }
    }

    public static <K, V> void rmMapTest(Map<K, V> trMap) {
        final String target = "santi";

        System.out.println(JSONObject.toJSONString(trMap));

        Iterator<Map.Entry<K, V>> iterator = trMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<K, V> item = iterator.next();
            if (Objects.equals(item.getKey(), target)) {
                iterator.remove();
            }
        }

        System.out.println(JSONObject.toJSONString(trMap));

        trMap.remove("darkforest");

        System.out.println(JSONObject.toJSONString(trMap));
    }

    public static <K, V> void traverseMap(Map<K, V> trMap) {
        Iterator<Map.Entry<K, V>> iterator = trMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<K, V> item = iterator.next();
            System.out.println("key = " + item.getKey() + ", " + "value = " + item.getValue());
            System.out.println(JSONObject.toJSONString(item));
        }
    }
}
