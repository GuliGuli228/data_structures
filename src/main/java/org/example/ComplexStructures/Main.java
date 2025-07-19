package org.example.ComplexStructures;

import org.example.BasicStructures.DoublyLinkedList;
import org.example.BasicStructures.GenericLinkedList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /*--------ОБЪЕКТЫ---------*/
        GenericLinkedList<Integer> List = new GenericLinkedList<>();
        Linked_list linked_list = new Linked_list();
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        GenericLinkedList<Integer> list = new GenericLinkedList<>();
        list.insertAt(1);
        list.insertAt(2);
        list.insertAt(3);
        list.insertAt(5);
        list.insertAt(22, 0);
        list.insertAt(23, 4);
        list.removeAt(3);
//        List: 1 → 2 → 3 → 5
//              0   1   2   3
//              1   2   3   4
        list.show();
        System.out.println(list.getValueAt(4));
        /*--------МЕТОДЫ---------*/
//        linked_list.insert(10);
//        linked_list.insert(20);
//        linked_list.insert(30);
//        linked_list.insert(40);
//        linked_list.insert(50);
//        linked_list.insert(60);
//        linked_list.insert(31,3);
//        linked_list.display();
//        System.out.println(linked_list.length());
        /*----------------------------------------------*/
//        doublyLinkedList.insert(10);
//        doublyLinkedList.insert(20);
//        doublyLinkedList.insert(30);
//        doublyLinkedList.insert(40);
//        doublyLinkedList.insert(29,3);
//        doublyLinkedList.display();
        /*----------------------------------------------*/

//        List.insert(1);
//        List.insert(2);
//        List.insert(3);
//        List.insert(4);
//        List.insert(5);
//        List.insert(6);
//        List.delete(1);
//        List.delete(6);
//        List.delete(3);
//        List.display();

        /*----------------------------------------------*/
    }
}