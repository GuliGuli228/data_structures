package org.example.ComplexStructures;

import org.example.BasicStructures.DoublyLinkedList;

/**
 * A circular doubly linked list implementation.
 * Extends {@link DoublyLinkedList} to provide circular linking behavior where
 * the tail node points to the head and vice versa.
 *
 * @param <T> the type of elements stored in the list
 *
 * @see DoublyLinkedList
 */

public class CircularList<T> extends DoublyLinkedList<T> {

    /**
     * Establishes circular linking by connecting the tail to the head and vice versa.
     * This method is called after any structural modification to maintain circularity.
     */

    private void setProp(){
        tail.next = head;
        head.prev = tail;
    }

    /**
     * Adds an element to the end of the circular list.
     * Maintains circular linking after addition.
     * @param value the element to add
     * @throws IllegalArgumentException if value is null
     */

    @Override
    public void add(T value){
        super.add(value);
        setProp();
    }

    /**
     * Inserts an element at the specified position in the circular list.
     * Maintains circular linking after insertion.
     * @param value the element to insert
     * @param index the position at which to insert (0-based)
     * @throws IndexOutOfBoundsException if index is out of range
     * @throws IllegalArgumentException if value is null
     */

    @Override
    public void add(T value, int index){
        super.add(value, index);
        setProp();
    }

    /**
     * Removes the element at the specified position in the circular list.
     * Maintains circular linking after removal.
     * @param index the position of the element to remove (0-based)
     * @throws IndexOutOfBoundsException if index is out of range
     * @throws IllegalStateException if list is empty
     */

    @Override
    public void removeAt(int index){
        super.removeAt(index);
        setProp();
    }

}
