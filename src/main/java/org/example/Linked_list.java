package org.example;

public class Linked_list {
    private class Node{
        Node next;
        int value;

        public Node(int value){
            this.value = value;
        }
        public Node(){};
    }

    Node head;

    public void insert(int value){
        if(head == null){
            head = new Node(value);
        }
        else{
            Node current = head;
            while(current.next != null){
                current = current.next;
            }
            Node node = new Node(value);
            current.next = node;
        }
    }

    public void insert (int value, int place){
        int counter = 1;
        if(head == null) System.out.println("List is empty");
        else {
            try {
                Node current = head;
                while (counter != place-1){
                    current = current.next;
                    counter++;
                }
                Node temp = new Node(value);
                temp.next = current.next;
                current.next = temp;
            } catch (NullPointerException e) {
                System.out.println("No such element to insert");;
            }


        }
    }

    public void display(){
        if(head == null) System.out.println("List is empty");
        else{
            Node current = head;
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
            Node current = this.head;
            while(current!= null){
                current = current.next;
                counter++;
            }
        }
        return counter;
    }
}
