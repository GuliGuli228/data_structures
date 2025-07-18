package org.example.Classes;

public class GenericLinkedList<T> {
    private int length;

    protected class Node{
        protected Node next;
        protected T value;

        protected Node (T value, Node next){
            this.value = value;
            this.next = next;
        }
        protected Node (T value){
            this.value = value;
        }
        protected Node (){};
    }

    protected Node head;

    public boolean IsEmpty(){
        return (head == null);
    }

    public void insert (T value){
        if(this.IsEmpty()){
            head = new Node(value);
        }
        else{
            Node current = head;
            while (current.next != null) current = current.next;
            current.next = new Node(value);
        }
        length++;
    }

    public void Length(){
        System.out.println(this.length);
    }

    public int getLength(){
        return this.length;
    }
    public void delete (int place){
        if (length < place) System.out.println("No such element to delete, List is shorter");
        else{
            try {
                int counter = 0;
                Node current = head;
                while (counter < place){
                    current = current.next;
                    counter++;
                }
                if(current.next.next==null) current.next = null;
                else current.next = current.next.next;

            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void display(){
        if(this.IsEmpty()) System.out.println("List is empty");
        else{
            Node current = head;
            while (current!= null){
                System.out.print(current.value + "->");
                current = current.next;
            }
            System.out.println();
        }
    }

}
