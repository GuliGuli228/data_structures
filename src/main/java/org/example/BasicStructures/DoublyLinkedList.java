package org.example.BasicStructures;

import org.example.AbstracClasses.AbstractList;
import org.example.Interfaces.List;

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
        protected DoublyLinkedNode(T value, DoublyLinkedNode next, DoublyLinkedNode prev){
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
    int lenght = 0;
    /*------------------*/

    @Override
    public void insertAt(T value) {
        if(this.IsEmpty())  head = new DoublyLinkedNode(value, null,null);
        else{
            DoublyLinkedNode current = head;
            while(current.next != null){
                current = current.next;
            }
            current.next = new DoublyLinkedNode(value, current, null);
        }
        lenght++;
    }
    public void insertAt(T value, int place){
        if (this.IsEmpty() || lenght-1 > place) {
            this.insertAt(value);
            return;
        }
        else{
            DoublyLinkedNode current = head;
            for(int i = 0; i < place; i++){
                current = current.next;
            }

        }
    }

    @Override
    public void removeAt(int place) {
        if(this.IsEmpty()) System.out.println("List is empty");
        else{
            int counter = 1;
            Node current = head;
        }
    }

    @Override
    public void show() {
        if(this.IsEmpty()) System.out.println("List is empty");
        else{
            DoublyLinkedNode current = head;
            while (current!= null){
                System.out.print(current.getValue()+ "->");
                current = current.next;
            }
            System.out.println();
        }
    }

    @Override
    public boolean IsEmpty() {
        return (head == null);
    }

    @Override
    public void update(T value, int place) {
        try {
            DoublyLinkedNode current = head;
            for (int i = 0; i < place; i++){
                current = current.next;
            }
            current.setValue(value);
        } catch (NullPointerException e) {
            System.out.println("No such element to update +" + e.getMessage());;
        }
    }
}
