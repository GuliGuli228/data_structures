package org.example.ComplexStructures;

import org.example.BasicStructures.GenericLinkedList;

public class Stack<E>{

    private GenericLinkedList<E> list;

    public Stack(){
        this.list = new GenericLinkedList<>();
    }

    public void push (E value){
        list.insertAt(value, 0);
    }

    public E pop(){
        return list.getValueAt(0);
    }
    public void peek(){
        System.out.println(list.getValueAt(0));
    }

    public boolean isEmpty(){
        return list.IsEmpty();
    }
}
