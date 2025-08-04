package org.example.ComplexStructures;

import org.apache.commons.lang.NullArgumentException;
import org.example.BasicStructures.GenericLinkedList;
import org.example.Exceptions.EmptyStackException;

public class Stack<E>{

    private GenericLinkedList<E> list;

    public Stack(){
        this.list = new GenericLinkedList<>();
    }

    public void push (E value){
        if (value == null) throw new NullArgumentException("value");
        list.insertAt(value, 0);
    }

    public E pop(){
        if (this.isEmpty()) throw new EmptyStackException();
        E result = list.getValueAt(0);
        list.removeAt(0);
        return result;
    }
    public E peek(){
       if (this.isEmpty()) throw new EmptyStackException();
       return list.getValueAt(0);
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }
}
