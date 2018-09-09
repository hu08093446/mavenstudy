package learnLinnkedHashMap;

import java.util.LinkedHashMap;
import java.util.Map;

public class MyLinkedHashMap<K, V> extends LinkedHashMap<K, V> {

    private static final int MAX_NUM = 5;



    public MyLinkedHashMap(int initialCapacity,
                           float loadFactor,
                           boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return size() > MAX_NUM;
    }
}
