package org.example.BasicStructures;

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
    protected LinkedNode head;
    protected LinkedNode tail;
    int length = 0;
        /*------------------*/

    @Override
    public void insertAt(T value) {
        if(this.IsEmpty()) head = new LinkedNode(value);
        else{
            LinkedNode current = head;
            while (current.next != null) current = current.next;
            current.next = new LinkedNode(value);
            tail = current.next;
        }
        length++;
    }
    public void insertAt(T value, int place){
        if(this.IsEmpty()) head = new LinkedNode(value);
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
                return;
            }
            else{
                for (int i = 0; i< place -1; i++){
                    current = current.next;
                }
                node.next = current.next.next;
                current.next = node;
            }
        }
    }

    @Override
    public void removeAt(int place) {
        if(this.IsEmpty()){
            System.out.println("List is empty, noting to delete");
            return;
        }
        if (place > length || place < 0){
            System.out.println("No such element to delete");
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
                length--;
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());;
            }
        }
    }

    public T getValueAt(int place){
        try {
            LinkedNode current = head;
            for (int i =0; i < place; i++){
                current = current.next;
            }
            return current.value;
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());;
        }
        return null;
    }

    @Override
    public void show() {
        if (this.IsEmpty()) {
            System.out.println("List is empty");
        } else {
            LinkedNode current = head;
            StringBuilder output = new StringBuilder();
            while (current != null) {
                output.append(current.value);
                if (current.next != null) {
                    output.append(" → ");
                }
                current = current.next;
            }

            System.out.println("List: " + output.toString());
        }
    }


    @Override
    public boolean IsEmpty() {
        return (head == null);
    }

}

