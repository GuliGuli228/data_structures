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
        E result = list.getValueAt(0);
        list.removeAt(0);
        return result;
    }
    public E peek(){
       return list.getValueAt(0);
    }

    public boolean isEmpty(){
        return list.IsEmpty();
    }
}
