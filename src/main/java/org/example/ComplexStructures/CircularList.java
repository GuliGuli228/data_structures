package org.example.ComplexStructures;

import org.example.BasicStructures.DoublyLinkedList;


public class CircularList<T> extends DoublyLinkedList<T> {
    protected class CircularNode extends DoublyLinkedNode{
        CircularNode next;
        CircularNode prev;
    }

    /*---Variables----*/
//    DoublyLinkedNode head = new DoublyLinkedNode();
//    DoublyLinkedNode tail;
    /*----------------*/


}
