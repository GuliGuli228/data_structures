package org.example.Interfaces;

public interface List<T> {
     void insertAt(T value);
     void removeAt(int place);
     String show();
     boolean isEmpty();
     void update(T value, int place);
}
