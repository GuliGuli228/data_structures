package org.example.Interfaces;

/**
 * Interface representing basic list operations.
 * Defines the contract for sequential data structures that maintain element order.
 *
 * @param <T> the type of elements stored in the list
 *
 * @see org.example.AbstracClasses.AbstractList
 * @see org.example.BasicStructures.GenericLinkedList
 * @see org.example.BasicStructures.DoublyLinkedList
 */

public interface List<T> {
     void add(T value);
     void removeAt(int place);
     void show();
     boolean isEmpty();
     void update(T value, int place);
     int size();
     Object[] toArray();
     int indexOf(T value);
}
