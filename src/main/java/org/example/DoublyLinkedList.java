package org.example;

public class DoublyLinkedList {

    private class Node{
        Node prev;
        Node next;
        int value;

        public Node(int value){
            this.value = value;
        }
        public Node (Node prev, Node next){
            this.prev = prev;
            this.next = next;
        }

        public Node (int value, Node prev, Node next){
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

    }

    private  Node head;

    public void insert (int value){
        if(head == null){
            head = new Node(value, null,null);
        }
        else{
            Node current = head;
            while(current.next != null){
                current = current.next;
            }
            current.next = new Node(value, current, null);
        }
    }

    public void insert (int value, int place){
        if(head == null) {
            System.out.println("List is empty");
        }
        else{
            try {
                int counter = 1;
                Node current = head;
                while (counter != place-1){
                    current = current.next;
                    counter++;
                }
                Node temp = new Node(value, current, current.next);
                current.next.prev = temp;
                current.next = temp;
            } catch (NullPointerException e) {
                System.out.println("No such element to insert");
            }
        }
    }

    public void display(){
        if(head == null) System.out.println("List is empty");
        else{
            DoublyLinkedList.Node current = head;
            while (current!= null){
                System.out.print(current.value + "->");
                current = current.next;
            }
            System.out.println();
        }
    }

    public int length(){
        int counter = 0;
        if(this.head == null) System.out.println("List is empty");
        else{
            DoublyLinkedList.Node current = this.head;
            while(current!= null){
                current = current.next;
                counter++;
            }
        }
        return counter;
    }

}
