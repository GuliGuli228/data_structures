package org.example.Classes;

import java.util.NoSuchElementException;

public class Stack<E> extends GenericLinkedList<E> {

    Linked_list list;

    public Stack(){
        this.list = new Linked_list();
    }

    @Override
    public void insert(E value){
        if (this.IsEmpty()){
            head = new Node(value);
        }
        else{
            Node node = new Node(value);
            node.next = head;
            head = node;
        }
    }

    public E pop(){
        if (this.IsEmpty()){
            throw new NoSuchElementException("List is empty");
        }
        else{
            E result = head.value;
            head.next = head;
            return result;
        }
    }

    public E peek (){
        return head.value;
    }

    public boolean IsEmpty(){
        return this.IsEmpty();
    }
}
