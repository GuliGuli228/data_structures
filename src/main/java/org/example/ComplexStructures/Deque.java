package org.example.ComplexStructures;

import org.example.BasicStructures.GenericLinkedList;
import org.example.Exceptions.EmptyQueueException;

/**
 * A double-ended queue (deque) implementation using a linked list as the underlying data structure.
 * Supports insertion and removal of elements from both ends in constant time.
 *
 * @param <T> the type of elements stored in the deque
 *
 * @see GenericLinkedList
 * @see EmptyQueueException
 */


public class Deque<T>{
    GenericLinkedList<T> queue;
    int size = 0;

    public Deque(){
        queue = new GenericLinkedList<>();
    }

    /**
     * Inserts the specified element at the front of the deque.
     * @param value the element to add
     *
     * @implNote Time complexity: O(1)
     */

    public void addFirst(T value){
        queue.add(value,0);
        size++;
    }

    /**
     * Inserts the specified element at the end of the deque.
     * @param value the element to add
     *
     * @implNote Time complexity: O(1)
     */

    public void addLast(T value){
        queue.add(value,size);
        size++;
    }

    /**
     * Removes and returns the first element from the deque.
     * @return the first element of the deque
     * @throws EmptyQueueException if the deque is empty
     *
     * @implNote Time complexity: O(1)
     */

    public T removeFirst(){
        if (queue.isEmpty()) throw new EmptyQueueException();
        T value = queue.getValueAt(0);
        queue.removeAt(0);
        size--;
        return value;
    }

    /**
     * Removes and returns the last element from the deque.
     * @return the last element of the deque
     * @throws EmptyQueueException if the deque is empty
     *
     * @implNote Time complexity: O(1)
     */

    public T removeLast(){
        if (queue.isEmpty()) throw new EmptyQueueException();
        T value = queue.getValueAt(queue.size()-1);
        queue.removeAt(queue.size()-1);
        size--;
        return value;
    }

    /**
     * Retrieves, but does not remove, the first element of the deque.
     * @return the first element of the deque
     * @throws EmptyQueueException if the deque is empty
     *
     * @implNote Time complexity: O(1)
     */

    public T peekFirst(){
        if (queue.isEmpty()) throw new EmptyQueueException();
        return queue.getValueAt(0);
    }

    /**
     * Retrieves, but does not remove, the last element of the deque.
     * @return the last element of the deque
     * @throws EmptyQueueException if the deque is empty
     *
     * @implNote Time complexity: O(1)
     */

    public T peekLast(){
        if (queue.isEmpty()) throw new EmptyQueueException();
        return queue.getValueAt(size-1);
    }

    /**
     * Returns the number of elements in the deque.
     * @return the number of elements in the deque
     */

    public int size(){
        return size;
    }

    /**
     * Cheks if queue is empty
     * @return true if empty, false otherwise
     */

    public boolean isEmpty(){
        return queue.isEmpty();
    }

    /**
     * Shows elements of queue*/
    public void show(){
        System.out.print("Deque: [");
        for (int i = 0; i < queue.size(); i++){
            System.out.print(" "+queue.getValueAt(i) +" ");
            if (i != queue.size()-1) System.out.print(",");
        }
        System.out.print("]");
    }
    /**
     * Returns queue as Object array. It is safe for data structure, Because it returns copy of structure, not structure itself
     * @return array of Objects*/
    public Object[] toArray(){
        return queue.toArray();
    }

}
