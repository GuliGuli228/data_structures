package org.example.Interfaces;

public interface Set<V> {
    void add(V value);
    void remove(V value);
    void clear();
    void show();
    boolean contains(V value);
    boolean isEmpty();
    int size();
}
