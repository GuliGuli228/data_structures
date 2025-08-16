package org.example.ComplexStructures;

import org.example.Interfaces.Set;

public class HashSet <V extends Comparable<V>> implements Set<V> {
    private HashMap<V,Object> map = new HashMap<>();
    private static final Object DUMMY = new Object();
    private int amountOfElements = 0;

    @Override
    public void add(V value) {
        map.add(value,DUMMY);
    }

    @Override
    public void remove(V value) {
        map.remove(value);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public void show() {
        System.out.println(map.getKeys());
    }

    @Override
    public boolean contains(V value) {
        return map.containsKey(value);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public int size() {
        return map.size();
    }
}
