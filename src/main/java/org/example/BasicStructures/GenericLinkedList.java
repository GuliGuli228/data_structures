package org.example.BasicStructures;

import org.apache.commons.lang.NullArgumentException;
import org.example.AbstracClasses.AbstractList;
import org.example.Exceptions.EmptyLinkedListException;
import org.example.Interfaces.List;

/**
 * A singly linked list implementation that supports adding, removing, and accessing elements.
 * Inherits from AbstractList and implements the List interface.
 * @param <T> the type of elements in the list
 * @see AbstractList
 * @see List
 * @see EmptyLinkedListException
 */


public class GenericLinkedList<T> extends AbstractList implements List<T> {

    /**
     * A node in the singly linked list, containing a value and a reference to the next node.
     * Extends the abstract Node class from AbstractList.
     * @see AbstractList.Node
     */

    protected class LinkedNode extends Node<T>{
        LinkedNode next;

        protected LinkedNode(T value, LinkedNode node){
            super(value);
            this.next = node;
        }
        protected LinkedNode(T value) {
            super(value);
        }

        /*---Getters & Setters Overrides---*/
        @Override
        protected T getValue(){
            return super.getValue();
        }
        @Override
        protected void setValue(T value){
            super.setValue(value);
        }
        /*---------------------------------*/

    }
        /*-----Variables-----*/
    protected LinkedNode head;
    protected LinkedNode tail;

    int length = 0;
        /*------------------*/

    /**
     * Adds an element to the end of the linked list.
     * @param value the element to add
     * @throws NullArgumentException if the element is null
     */
    @Override
    public void add(T value) {
        if(this.isEmpty()) {
            head = new LinkedNode(value);
            tail =head;
        }
        else{
            LinkedNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new LinkedNode(value);
            tail = current.next;
        }
        length++;
    }

    /**
     * Removes the element at the specified position in the list.
     * @param place the index of the element to remove
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     * @throws EmptyLinkedListException if list is empty
     */
    @Override
    public void removeAt(int place) {
        if(this.isEmpty()) throw new EmptyLinkedListException();
        if (place > length || place < 0) throw new IndexOutOfBoundsException();
        else {
            if (length == 1){
                head =null;
                tail = null;
                length--;
                return;
            }

            LinkedNode current = head;
            if(place == 0){
                head = head.next;
                length--;
                return;
            }

            for (int i = 0; i < place - 1; i++) {
                current = current.next;
            }

            LinkedNode nodeToDelete = current.next;
            current.next = nodeToDelete != null ? nodeToDelete.next : null;
            tail = current;
            length--;
        }
    }

    /**
     * Returns the element at the specified position in the list.
     * @param place the index of the element to return
     * @return the element at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     * @throws EmptyLinkedListException if list is empty
     */
    public T getValueAt(int place){
        if (this.isEmpty()) throw new EmptyLinkedListException();
        if(place < 0 || place +1 > length) throw new IndexOutOfBoundsException();
        LinkedNode current = head;
        for (int i =0; i < place; i++){
            current = current.next;
        }
        return current.getValue();
    }

    /**
     * Shows a string representation of lsit
     * @throws EmptyLinkedListException if list is empty
     */
    @Override
    public void show() {
        System.out.println(this);
    }

    /**
     * Returns a string representation of the list.
     *
     * @throws EmptyLinkedListException if list is empty
     */

    @Override
    public String toString(){
        if (this.isEmpty())throw new EmptyLinkedListException();
        else {
            LinkedNode current = head;
            StringBuilder output = new StringBuilder("[ ");
            while (current != null) {
                output.append(current.getValue());
                if (current.next != null) output.append(" , ");
                current = current.next;
            }
            output.append(" ]");
            return output.toString();
        }
    }

    /**
     * Checks if the list is empty.
     * @return true if the list contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Updates the value of the element at the specified position.
     * @param value the new value
     * @param place the index of the element to update
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     * @throws NullArgumentException if the new value is null
     * @throws EmptyLinkedListException if list is empty
     */
    @Override
    public void update(T value, int place) {
        if (this.isEmpty()) throw new EmptyLinkedListException();
        if(place<0 || place + 1 > length) throw new IndexOutOfBoundsException();
        if (value == null) throw new NullArgumentException("value");

        LinkedNode current = head;
        for(int i = 0; i< place; i++){
            current = current.next;
        }
        current.setValue(value);
    }

    /**
     * Returns the number of elements in the list.
     * @return the number of elements in the list
     */

    @Override
    public int size() {
        return length;
    }

    /**
     * Returns array implementation of list
     * @return Object[] array
     */
    @Override
    public Object[] toArray() {
        if (this.isEmpty()) throw new EmptyLinkedListException();
        Object[] array = new Object[length];
        LinkedNode current = head;
        for (int i = 0; i < length; i++){
            array[i] = current.getValue();
            current = current.next;
        }
        return array;
    }

    @Override
    public int indexOf(T value) {
        int index = 0;
        LinkedNode current = head;
        for (int i = 0; i < length; i++){
            if (value.equals(current.getValue())) return index;
            current = current.next;
            index++;
        }
        return -1;
    }


    /**
     * Inserts an element at the specified index in the list.
     * @param value the element to insert
     * @param place the index at which to insert the element
     * @throws IndexOutOfBoundsException if the index is out of range
     * @throws NullArgumentException if the element is null
     * @throws EmptyLinkedListException if list is empty
     */
    public void add(T value, int place){
        if(place < 0 || place > length) throw new IndexOutOfBoundsException();
        if(this.isEmpty()){
            head = new LinkedNode(value);
            tail = head;
            length++;
        }
        else{
            LinkedNode current = head;
            LinkedNode node = new LinkedNode(value);
            if (place == 0) {
                node.next = head;
                head = node;
                length ++;
                return;
            }
            if (place == length){
                tail.next = node;
                tail = node;
                length++;
            }
            else{
                for (int i = 0; i< place -1; i++){
                    current = current.next;
                }
                node.next = current.next;
                current.next = node;
                length++;
            }
        }
    }
}

