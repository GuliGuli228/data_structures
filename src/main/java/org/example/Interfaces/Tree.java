package org.example.Interfaces;

public interface Tree<T> {
    void add (T value);
    void deleteAt(int place);
    void show();
    void update();
}
