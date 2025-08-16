package org.example.ComplexStructures;

import org.apache.commons.lang.NullArgumentException;
import org.example.BasicStructures.GenericLinkedList;
import org.example.Exceptions.EmptyStackException;

/**
 * A stack implementation using a linked list as the underlying data structure.
 * Follows LIFO (Last-In-First-Out) principle.
 * @param <E> the type of elements in the stack
 * @see GenericLinkedList
 * @see EmptyStackException
 */

public class Stack<E>{

    private GenericLinkedList<E> list;

    public Stack(){
        this.list = new GenericLinkedList<>();
    }

    /**
     * Pushes an element onto the top of the stack.
     * @param value the element to push
     * @throws NullArgumentException if the value is null
     */

    public void push (E value){
        if (value == null) throw new NullArgumentException("value");
        list.add(value, 0);
    }

    /**
     * Removes and returns the element at the top of the stack.
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     * @implNote Time complexity: O(1)
     */

    public E pop(){
        if (this.isEmpty()) throw new EmptyStackException();
        E result = list.getValueAt(0);
        list.removeAt(0);
        return result;
    }
    /**
     * Returns the element at the top of the stack without removing it.
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     * @implNote Time complexity: O(1)
     */

    public E peek(){
       if (this.isEmpty()) throw new EmptyStackException();
       return list.getValueAt(0);
    }
    /**
     * Checks if the stack is empty.
     * @return true if the stack is empty, false otherwise
     */

    public boolean isEmpty(){
        return list.isEmpty();
    }

    /**
     * Returns size of stack.
     * @return number of elements in stack
     */

    public int size(){
        return list.size();
    }

}
