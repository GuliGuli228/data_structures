package org.example.ComplexStructures;

import org.apache.commons.lang.NullArgumentException;
import org.example.BasicStructures.DoublyLinkedList;
import org.example.BasicStructures.GenericLinkedList;
import org.example.Exceptions.EmptyQueueException;

import java.sql.PreparedStatement;

public class Queue<E> {
    GenericLinkedList<E> list = new GenericLinkedList<>();
    //
    public void enqueue(E element){
        if(element==null) throw new NullArgumentException("element");
        list.insertAt(element, 0);
    }
    public E dequeue(){
        if(list.isEmpty()) throw new EmptyQueueException();
        E result = list.getValueAt(list.getSize()-1);
        list.removeAt(list.getSize()-1);
        return result;
    }

    public E peek(){
        if(this.isEmpty()) throw new EmptyQueueException();
        return list.getValueAt(list.getSize()-1);
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public int size(){
        return list.getSize();
    }

}
