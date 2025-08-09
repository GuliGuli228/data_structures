package org.example.BasicStructures;

import org.apache.commons.lang.NullArgumentException;
import org.example.AbstracClasses.AbstractList;
import org.example.Exceptions.EmptyLinkedListException;
import org.example.Interfaces.List;


public class GenericLinkedList<T> extends AbstractList implements List<T> {
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

    @Override
    public void insertAt(T value) {
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


    @Override
    public void removeAt(int place) {
        if(this.isEmpty()) throw new EmptyLinkedListException();
        if (place > length || place < 0) throw new IndexOutOfBoundsException();
        else {
            try {
                LinkedNode current = head;
                if(place == 0){
                    head = head.next;
                    tail = head;
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
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
                throw new NullPointerException();
            }
        }
    }

    // TODO rewrite method to not return null
    public T getValueAt(int place){
        if (this.isEmpty()) throw new EmptyLinkedListException();
        if(place < 0 || place +1 > length) throw new IndexOutOfBoundsException();
        try {
            LinkedNode current = head;
            for (int i =0; i < place; i++){
                current = current.next;
            }
            return current.getValue();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());;
        }
        return null;
    }

    @Override
    public String show() {
        if (this.isEmpty())throw new EmptyLinkedListException();
        else {
            LinkedNode current = head;
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
        if (this.isEmpty()) throw new EmptyLinkedListException();
        if(place<0 || place + 1 > length) throw new IndexOutOfBoundsException();
        if (value == null) throw new NullArgumentException("value");

        LinkedNode current = head;
        for(int i = 0; i< place; i++){
            current = current.next;
        }
        current.setValue(value);
    }

    public int getSize(){
        return length;
    }

    public void insertAt(T value, int place){
        if(place < 0 || place > length) throw new IndexOutOfBoundsException();
        if(this.isEmpty()){
            head = new LinkedNode(value);
            length++;
        }
        else{
            LinkedNode current = head;
            LinkedNode node = new LinkedNode(value);
            if (place == 0) {
                node.next = head;
                head = node;
                tail = head;
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

