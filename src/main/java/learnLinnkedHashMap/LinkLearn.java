package learnLinnkedHashMap;

import java.util.Map;

public class LinkLearn {
    public static void main(String[] args) {
//        Map<Integer, String> map = new LinkedHashMap<>();
//        map.put(3, "fuckA");
//        map.put(2, "fuckB");
//        map.put(4, "fuckC");
//        map.forEach((k, v) -> System.out.println("first " + k + " second " + v));
//
//        System.out.println();
//        System.out.println();
//        System.out.println();
//
//        Map<Integer, String> mapA = new HashMap<>();
//        mapA.put(3, "fuckA");
//        mapA.put(2, "fuckB");
//        mapA.put(4, "fuckC");
//        mapA.forEach((k, v) -> System.out.println("first " + k + " second " + v));

        float DEFAULT_LOAD_FACTOR = 0.75f;

        Map<Integer, String> myMap = new MyLinkedHashMap<>(5, DEFAULT_LOAD_FACTOR, true);
        myMap.put(1, "fuckA");
        myMap.put(2, "fuckB");
        myMap.put(3, "fuckC");
        myMap.put(4, "fuckD");
        myMap.put(5, "fuckE");

        myMap.get(1);
        myMap.forEach((k, v) -> System.out.println("key " + k + " value " + v));
        System.out.println(myMap);

        myMap.put(6, "fuckF");
        myMap.forEach((k, v) -> System.out.println("key " + k + " value " + v));
        System.out.println(myMap);

//        myMap.put(7, "fuckG");
//        myMap.forEach((k, v) -> System.out.println("key " + k + " value " + v));
//        System.out.println(myMap);
    }
}
