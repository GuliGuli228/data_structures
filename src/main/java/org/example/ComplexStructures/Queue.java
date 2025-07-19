package org.example.ComplexStructures;

import org.example.BasicStructures.DoublyLinkedList;
import org.example.BasicStructures.GenericLinkedList;

import java.sql.PreparedStatement;

public class Queue<E> extends DoublyLinkedList<E> {
    GenericLinkedList<E> list = new GenericLinkedList<>();

    public void enqueue(E element){
        list.insertAt(element, list.getSize()-1);
    }
    public E dequeue(){
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

    public int size(){
        return list.getSize();
    }





}
