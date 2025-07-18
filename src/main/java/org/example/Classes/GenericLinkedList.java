package org.example.Classes;

import org.example.AbstracClasses.AbstractList;
import org.example.Interfaces.List;

public class GenericLinkedList<T> extends AbstractList<T> implements List<T> {
    protected class LinkedNode extends Node{
        LinkedNode next;

        protected LinkedNode(T value, LinkedNode node){
            super(value);
            this.next = node;
        }
        protected LinkedNode(T value) {
            super(value);
        }
        protected LinkedNode(){};
    }
        /*-----Variables-----*/
    LinkedNode head;
    int length = 0;
        /*------------------*/

    @Override
    public void insert(T value) {
        if(this.IsEmpty()){
            head = new LinkedNode(value);
        }
        else{
            LinkedNode current = head;
            while (current.next != null) current = current.next;
            current.next = new LinkedNode(value);
        }
        length++;
    }

    @Override
    public void delete(int place) {
        if (length < place) System.out.println("No such element to delete, List is shorter");
        else{
            try {
                int counter = 0;
                LinkedNode current = head;
                while (counter < place){
                    current = current.next;
                    counter++;
                }
                if(current.next.next==null) current.next = null;
                else current.next = current.next.next;

            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    @Override
    public void show() {
        if(this.IsEmpty()) System.out.println("List is empty");
        else{
            LinkedNode current = head;
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
