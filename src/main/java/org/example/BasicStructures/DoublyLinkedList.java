package org.example.BasicStructures;

import org.apache.commons.lang.NullArgumentException;
import org.example.AbstracClasses.AbstractList;
import org.example.Exceptions.EmptyDoublyLinkedListException;
import org.example.Interfaces.List;

/**
 * A doubly linked list implementation that supports adding, removing, and accessing elements.
 * Inherits from AbstractList and implements the List interface.
 * @param <T> the type of elements in the list
 * @author GuliGuli228
 * @see AbstractList
 * @see List
 * @see EmptyDoublyLinkedListException
 */

public class DoublyLinkedList<T> extends AbstractList implements List<T> {
    protected class DoublyLinkedNode extends Node<T>{
        //Experimental decision- making next and prev fields public
        public DoublyLinkedNode next;
        public DoublyLinkedNode prev;

        protected DoublyLinkedNode(){};
        protected DoublyLinkedNode(T value){
            super(value);
        }
        protected DoublyLinkedNode(T value, DoublyLinkedNode next){
            super(value);
            this.next = next;
        }
        protected DoublyLinkedNode(T value, DoublyLinkedNode prev, DoublyLinkedNode next){
            super(value);
            this.next = next;
            this.prev = prev;
        }
        @Override
        protected T getValue(){
            return super.getValue();
        }
        @Override
        protected void setValue(T value){
            super.setValue(value);
        }

    }

    /*-----Variables-----*/
    protected DoublyLinkedNode head;
    protected DoublyLinkedNode tail;
    protected int length = 0;
    /*------------------*/


    /**
     * Adds an element to the end of the doubly linked list.
     * @param value the element to add
     * @throws NullArgumentException if the element is null
     */

    @Override
    public void add(T value) {
        if(value == null) throw new NullArgumentException("value");
        if(this.isEmpty()) {
            head = new DoublyLinkedNode(value);
            tail = head;
        }
        else{
            DoublyLinkedNode current = head;
            for (int i =0; i< length-1; i++){
                current = current.next;
            }
            current.next = new DoublyLinkedNode(value, current, null);
            tail = current.next;
        }
        length++;
    }

    /**
     * Inserts an element at the specified index in the list.
     * @param place the place to insert the element
     * @param value the element to insert
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     * @throws NullArgumentException if the element is null
     * @throws EmptyDoublyLinkedListException if list is empty
     */

    public void add(T value, int place){
        if(value == null) throw new NullArgumentException("value");
        if(place < 0 || place > length) throw new IndexOutOfBoundsException("place is out of bounds");
        if(this.isEmpty()) throw new EmptyDoublyLinkedListException();

        DoublyLinkedNode node = new DoublyLinkedNode(value);
        if(place == 0){
            head.prev = node;
            node.next = head;
            head = node;
        }
        if(place == length){
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        else {
            DoublyLinkedNode current = head;
            for (int i =0; i < place - 1; i++){
                current = current.next;
            }
            DoublyLinkedNode tempNode =  current.next;
            current.next = node;
            node.prev = current;
            node.next =  tempNode;
            tempNode.prev = node;
        }
        length++;
    }

    /**
     * Removes the first occurrence of the specified element from the list.
     * @param place the place from where element should be removed
     * @throws IndexOutOfBoundsException if the element is out of bounds
     * @throws EmptyDoublyLinkedListException if list is empty
     */
    @Override
    public void removeAt(int place) {
        if (this.isEmpty()) throw new EmptyDoublyLinkedListException();
        if (place < 0 || place+1 > length) throw new IndexOutOfBoundsException("place is out of bounds");

        if(place == 0){
            DoublyLinkedNode current = head.next;
            current.prev = null;
            head = current;
            length--;
            return;
        }
        if(place == length-1){
            DoublyLinkedNode current = tail.prev;
            tail.prev = null;
            current.next = null;
            tail = current;
        }
        else{
            DoublyLinkedNode current = head;
            for (int i =0; i < place - 1; i++){
                current = current.next;
            }
            DoublyLinkedNode tempNode =  current.next;
            tempNode.prev = null;
            current.next = tempNode.next;
            tempNode.next = null;
            current.next.prev = current;
        }
        length--;
    }

    /**
     *Shows a string representation of list
     *
     * @throws EmptyDoublyLinkedListException if list is empty
     */
    @Override
    public void show() {
        System.out.println(this);
    }

    /**
     * Returns all list elements as String
     *
     * @throws EmptyDoublyLinkedListException if list is empty
     */
    @Override
    public String toString(){
        if (this.isEmpty())throw new EmptyDoublyLinkedListException();
        if (this.length == 1){
            return "List: [ " + head.getValue() + " ]";
        }
        else {
            DoublyLinkedNode current = head;
            StringBuilder output = new StringBuilder("List: [ ");

            for (int i =0 ; i < length; i++){
                assert current != null;
                output.append(current.getValue()).append(" ");
                if (i != length-1) output.append(", ");
                current = current.next;
            }

            output.append("]");
            return output.toString();
        }
    }

    /**
     * Cheks if list is empty
     * @return true id list is empty, false otherwise
     * */

    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Update value of element at index
     * @param value  new value
     * @param place  index of element, which should be updated*/

    @Override
    public void update(T value, int place) {
        if (this.isEmpty()) throw new EmptyDoublyLinkedListException();
        if (place < 0 || place + 1 > length) throw new IndexOutOfBoundsException("place is out of bounds");
        if (value == null) throw new NullArgumentException("value");

        if (place == 0) head.setValue(value);
        if (place == length-1) tail.setValue(value);
        else{
            DoublyLinkedNode current = head;
            for (int i =0; i < place; i++){
                current = current.next;
            }
            current.setValue(value);
        }
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[length];
        DoublyLinkedNode current = head;
        for (int i = 0; i < length; i++){
            array[i] = current.getValue();
            current = current.next;
        }
        return array;
    }
}

