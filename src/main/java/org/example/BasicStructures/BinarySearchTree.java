package org.example.BasicStructures;

import org.example.AbstracClasses.AbstractTree;
import org.example.ComplexStructures.Queue;
import org.example.ComplexStructures.Stack;
import org.example.Interfaces.Tree;

import java.util.Comparator;

public class BinarySearchTree<T extends Comparable<T>> extends AbstractTree<T, BinarySearchTree.BinaryNode> implements Tree<T, BinarySearchTree.BinaryNode> {
    protected class BinaryNode extends Node{

        protected BinaryNode(T value){
            super(value);
        }
        @Override
        protected T getValue(){
            return super.getValue();
        }
        @Override
        protected void setValue(T value){
            super.setValue(value);
        }
        protected void setLeft(BinaryNode left){
            this.left = left;
        }
        protected void setRight(BinaryNode right){
            this.right = right;
        }
        protected BinaryNode getLeft(){
            return left;
        }
        protected BinaryNode getRight(){
            return right;
        }

    }
    private class BinaryTreeComparator implements Comparator<BinaryNode>{
        @Override
        public int compare(BinaryNode node1, BinaryNode node2) {
            return node1.getValue().compareTo(node2.getValue());
        }
    }

    BinaryNode root;
    BinaryTreeComparator Compare = new BinaryTreeComparator();
    @Override
    public void add(T value) {
        if(this.IsEmpty()){
            root = new BinaryNode(value);
        }
        else{
            BinaryNode current = root;
            BinaryNode parent = null;
            int direction = 0;
            while (current!=null){
                parent = current;
                direction = value.compareTo(current.getValue());
                if(direction>0) current = current.getRight();
                if(direction <0) current = current.getLeft();
                if(direction == 0) return;
            }
            if(direction > 0) parent.setRight(new BinaryNode(value));
            if(direction < 0) parent.setLeft(new BinaryNode(value));
        }
    }

    @Override
    public void deleteByValue(T value) {
        BinaryNode current = root;
        BinaryNode parent = null;
        int direction = 0;
        while(value.compareTo(current.getValue()) != 0){
            parent = current;
            direction = value.compareTo(current.getValue());
            if (direction > 0) current = current.getRight();
            if(direction < 0) current = current.getLeft();
        }
        BinaryNode left = current.getLeft();
        BinaryNode right = current.getRight();
        if(left == null && right == null){
            if(direction > 0) parent.setRight(null);
            if(direction < 0) parent.setLeft(null);
        }
        if(left == null && right != null){
            if(direction > 0) parent.setRight(current.getRight());
            if(direction < 0) parent.setLeft(current.getRight());
        }
        if(left != null && right == null){
            if(direction > 0) parent.setRight(current.getLeft());
            if(direction < 0) parent.setLeft(current.getLeft());
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void update(T from, T to) {
        BinaryNode current = BFS(from);
        current.setValue(to);
    }

    @Override
    public BinaryNode BFS(T value) {
        if(this.IsEmpty()){
            System.out.println("Tree is Empty");
            return null;
        }
        BinaryNode current = root;
        Queue<BinaryNode> queue = new Queue<>();
        queue.enqueue(current);

        try {
            while(!queue.IsEmpty()){
                current = queue.dequeue();
                if(value.compareTo(current.getValue())==0) return current;
                else{
                    if(current.getLeft()!= null)queue.enqueue(current.getLeft());
                    if(current.getRight()!=null) queue.enqueue(current.getRight());
                }
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());;
        }
        return current;
    }

    public GenericLinkedList<BinaryNode> BFS(){
        GenericLinkedList<BinaryNode> list = new GenericLinkedList<>();
    }

    @Override
    public BinaryNode DPS(T value) {
        if(this.IsEmpty()){
            System.out.println("Tree is Empty");
            return null;
        }
            BinaryNode current = root;
            Stack<BinaryNode> stack = new Stack<>();
            stack.push(current);

        try {
            while (!stack.isEmpty()){
                current = stack.pop();
                if(value.compareTo(current.getValue()) == 0){
                    return current;
                }
                else{
                    if(current.getRight()!= null) stack.push(current.getRight());
                    if(current.getLeft()!=null)   stack.push(current.getLeft());
                }
            }
            return current;
        } catch (NullPointerException e) {
            System.out.println("Tree is empty");;
        }
        return null;
    }

    @Override
    public boolean IsEmpty() {
        return (root == null);
    }
}
