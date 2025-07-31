package org.example.BasicStructures;

import org.example.AbstracClasses.AbstractList;
import org.example.Interfaces.List;

import java.util.NoSuchElementException;

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
        if(this.IsEmpty()) {
            head = new LinkedNode(value);
            tail =head;
        }
        else{
            LinkedNode current = head;
            while (current.next != null) current = current.next;
            current.next = new LinkedNode(value);
            tail = current.next;
        }
        length++;
    }


    @Override
    public void removeAt(int place) {
        if(this.IsEmpty()){
            System.out.println("List is empty, noting to delete");
            throw new NoSuchElementException();
        }
        if (place > length || place < 0){
            System.out.println("No such element to delete");
            throw new NoSuchElementException();
        }
        else {
            try {
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
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
                throw new NullPointerException();
            }
        }
    }

    // TODO rewrite method to not return null
    public T getValueAt(int place){
        if (this.IsEmpty()){
            System.out.println("List is empty");
            throw new NullPointerException();
        }
        if(place < 0 ) throw new IllegalArgumentException();
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
        if (this.IsEmpty()) {
            System.out.println("List is empty");
            throw new IllegalArgumentException();
        } else {
            LinkedNode current = head;
            StringBuilder output = new StringBuilder("List: ");
            while (current != null) {
                output.append(current.getValue());
                if (current.next != null) {
                    output.append(" → ");
                }
                current = current.next;
            }
            return output.toString();
        }
    }


    @Override
    public boolean IsEmpty() {
        return (head == null);
    }

    @Override
    public void update(T value, int place) {
        if (this.IsEmpty()) throw new NoSuchElementException();
        if(place<0 || place <length-1) throw new IllegalArgumentException();
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
        if(this.IsEmpty()){
            head = new LinkedNode(value);
            length++;
            return;
        }
        if(place < 0 || place+1 > length) throw new IllegalArgumentException();
        else{
            LinkedNode current = head;
            LinkedNode node = new LinkedNode(value);
            if (place == 0) {
                node.next = head;
                head = node;
                length ++;
                return;
            }
            if (place == length-1){
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

