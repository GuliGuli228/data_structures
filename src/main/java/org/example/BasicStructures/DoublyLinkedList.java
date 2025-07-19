package org.example.BasicStructures;

import org.example.AbstracClasses.AbstractList;
import org.example.Interfaces.List;

public class DoublyLinkedList<T> extends AbstractList<T> implements List<T> {
    protected class DoublyLinkedNode extends Node{
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
                System.out.print(current.value + "->");
                current = current.next;
            }
            System.out.println();
        }
    }

    @Override
    public boolean IsEmpty() {
        return (head == null);
    }
}
