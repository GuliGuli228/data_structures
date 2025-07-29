package org.example.Interfaces;

public interface Tree<T, N> {
    void add (T value);
    void deleteByValue(T value);
    String show();
    void update(T from, T to);
    N BFS(T value);
    N DPS(T value);
    boolean IsEmpty();
}
