package org.example.BasicStructures;

import org.apache.commons.lang.NullArgumentException;
import org.example.AbstracClasses.AbstractList;
import org.example.Exceptions.EmptyDoublyLinkedListException;
import org.example.Exceptions.EmptyLinkedListException;
import org.example.Interfaces.List;

import java.util.NoSuchElementException;

public class DoublyLinkedList<T> extends AbstractList implements List<T> {
    protected class DoublyLinkedNode extends Node<T>{
        DoublyLinkedNode next;
        DoublyLinkedNode prev;

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
    DoublyLinkedNode head;
    DoublyLinkedNode tail;
    int length = 0;
    /*------------------*/

    @Override
    public void insertAt(T value) {
        if(value == null) throw new NullArgumentException("value");
        if(this.isEmpty()) {
            head = new DoublyLinkedNode(value);
            tail = head;
        }
        else{
            DoublyLinkedNode current = head;
            while(current.next != null){
                current = current.next;
            }
            current.next = new DoublyLinkedNode(value, current, null);
            tail = current.next;
        }
        length++;
    }

    public void insertAt(T value, int place){
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

    @Override
    public void removeAt(int place) {
        if (place < 0 || place+1 > length) throw new IndexOutOfBoundsException("place is out of bounds");
        if (this.isEmpty()) throw new EmptyDoublyLinkedListException();

        if(place == 0){
            DoublyLinkedNode current = head.next;
            head.next = null;
            current.prev = null;
            head = current;
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

    @Override
    public String show() {
        if (this.isEmpty())throw new EmptyDoublyLinkedListException();
        else {
            DoublyLinkedNode current = head;
            StringBuilder output = new StringBuilder("List: [ ");

            while (current != null) {
                output.append(current.getValue());
                if (current.next != null) output.append(" , ");
                current = current.next;
            }
            output.append(" ]");
            return output.toString();
        }
    }

    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    @Override
    public void update(T value, int place) {
        if (place < 0 || place + 1 > length) throw new IndexOutOfBoundsException("place is out of bounds");
        if (value == null) throw new NullArgumentException("value");
        if (this.isEmpty()) throw new EmptyDoublyLinkedListException();

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
}
