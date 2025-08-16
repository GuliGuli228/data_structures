package org.example.Interfaces;

public interface Map<K,V>{
    void add(K key, V value);
    void remove(K key);
    V get(K key);
    void clear();
    void show();
    boolean containsKey(K key);
    boolean containsValue(V value);
    boolean isEmpty();
    int size();
}
