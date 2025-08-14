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
     void insertAt(T value);
     void removeAt(int place);
     String show();
     boolean isEmpty();
     void update(T value, int place);
}
