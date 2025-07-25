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
//        TIP Override blocks make protected methods from InnerClass Node
//        visible in BinarySearchTree field

        /*---Getters Overrides---*/
        @Override
        protected T getValue(){
            return super.getValue();
        }
        @Override
        protected BinaryNode getLeft(){
            return super.getLeft();
        }
        @Override
        protected BinaryNode getRight(){
            return super.getRight();
        }
        @Override
        protected BinaryNode getParent(){
            return super.getParent();
        }
        /*-----------------------*/

        /*---Setters Overrides---*/
        @Override
        protected void setValue(T value){
            super.setValue(value);
        }
        @Override
        protected void setLeft(BinarySearchTree.BinaryNode node){
            super.setLeft(node);
        }
        @Override
        protected void setRight(BinarySearchTree.BinaryNode node){
            super.setRight(node);
        }
        @Override
        protected void setParent(BinarySearchTree.BinaryNode node){
            super.setParent(node);
        }
        /*-----------------------*/

        /*---Methods---*/
        protected int countChildren(){
            if (this.left != null && this.right != null) return 2;
            if(this.left == null && this.right == null) return 0;
            else return 1;
        }
    }

    //TODO: maybe delete
    private class BinaryTreeComparator implements Comparator<BinaryNode>{
        @Override
        public int compare(BinaryNode node1, BinaryNode node2) {
            return node1.getValue().compareTo(node2.getValue());
        }
    }

    //TODO find a way to use method .getParent(), not variable BinaryNode parent;
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
// TODO: add Nullpointerexeption try-catch or null "if statement", and ORDER cases (Краевые случаи)

    @Override
    public void deleteByValue(T value) {
        BinaryNode toDelete = BFS(value);

        switch (toDelete.countChildren()){
            case 0:
                if(toDelete.getParent().getRight() == toDelete) toDelete.getParent().setRight(null);
                if(toDelete.getParent().getLeft() == toDelete) toDelete.getParent().setLeft(null);
                return;
            case 1:
                BinaryNode child = toDelete.getLeft() == null ? toDelete.getRight(): toDelete.getLeft(); // if left child is null, we'll use right child, otherwise we'll use right child

                if(toDelete.getParent().getRight() == toDelete) toDelete.getParent().setRight(child);
                else toDelete.getParent().setLeft(child);
                return;
            case 2:
                T min = toDelete.getRight().getValue(); // Going to right branch to search min, another way - going left to search for max
                BinaryNode searchNode = toDelete;
                searchNode = searchNode.getRight();

                while (searchNode.getLeft().getValue().compareTo(min) < 0){
                    min = searchNode.getLeft().getValue();
                    searchNode = searchNode.getLeft();
                }
                toDelete.setValue(min);

                if (searchNode.countChildren()==1){
                    if(searchNode.getParent().getRight() == toDelete) searchNode.getParent().setRight(null);
                    if(searchNode.getParent().getLeft() == toDelete) searchNode.getParent().setLeft(null);
                    return;
                }
                if (searchNode.countChildren()==2){
                    BinaryNode child_search = searchNode.getLeft() == null ? searchNode.getRight(): searchNode.getLeft(); // if left child is null, we'll use right child, otherwise we'll use right child

                    if(searchNode.getParent().getRight() == searchNode) searchNode.getParent().setRight(child_search);
                    else searchNode.getParent().setLeft(child_search);

                }

        }
    }


    //TODO разобраться до конца как все таки рабоатет симетричный обход
    //TODO add ORDER cases
    @Override
    public void show() {
        Stack<BinaryNode> stack = new Stack<>();
        BinaryNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.getLeft();
            }
            BinaryNode temp = stack.pop();
            System.out.println(temp.getValue());
            current = temp.getRight();
        }
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
