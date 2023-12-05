package HeldKarp;

import java.util.LinkedHashMap;
import java.util.Map;

public class MemoizationCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public MemoizationCache(int capacity) { // capacity is number of entries
        super(capacity, 0.75f, true); // access order = true means that the order of elements is based on a 
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) { // removes the eldest entity from the map after it reaches the specified capacity
        return size() > capacity;
    }
}