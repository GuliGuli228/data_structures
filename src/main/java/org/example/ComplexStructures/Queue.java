package org.example.ComplexStructures;

import org.apache.commons.lang.NullArgumentException;
import org.example.BasicStructures.GenericLinkedList;
import org.example.Exceptions.EmptyQueueException;

/**
 * A queue implementation using a linked list as the underlying data structure.
 * Follows FIFO (First-In-First-Out) principle.
 * @param <E> the type of elements in the queue
 * @see GenericLinkedList
 * @see EmptyQueueException
 */

public class Queue<E> {
    GenericLinkedList<E> list;
    public Queue() {
        this.list = new GenericLinkedList<>();
    }

    /**
     * Adds an element to the end of the queue.
     * @param element the element to add
     * @throws NullArgumentException if the element is null
     * @implNote Time complexity: O(1)
     */

    public void enqueue(E element){
        if(element==null) throw new NullArgumentException("element");
        list.add(element, 0);
    }
    /**
     * Removes and returns the element from the front of the queue.
     * @return the element at the front of the queue
     * @throws EmptyQueueException if the queue is empty
     * @implNote Time complexity: O(1)
     */

    public E dequeue(){
        if(list.isEmpty()) throw new EmptyQueueException();
        E result = list.getValueAt(list.size()-1);
        list.removeAt(list.size()-1);
        return result;
    }
    /**
     * Returns the element at the front of the queue without removing it.
     * @return the element at the front of the queue
     * @throws EmptyQueueException if the queue is empty
     * @implNote Time complexity: O(1)
     */

    public E peek(){
        if(this.isEmpty()) throw new EmptyQueueException();
        return list.getValueAt(list.size()-1);
    }

    /**
     * Checks if the queue is empty.
     * @return true if the queue is empty, false otherwise
     */

    public boolean isEmpty(){
        return list.isEmpty();
    }

    /**
     * Returns the number of elements in the queue.
     * @return the number of elements in the queue
     */

    public int size(){
        return list.size();
    }


}
