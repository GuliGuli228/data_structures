package org.example.BasicStructures;

import org.example.AbstracClasses.AbstractTree;
import org.example.ComplexStructures.Queue;
import org.example.ComplexStructures.Stack;
import org.example.Interfaces.Tree;

import java.util.Comparator;

public class BinarySearchTree<T extends Comparable<T>> extends AbstractTree<T, BinarySearchTree<T>.BinaryNode> implements Tree<T, BinarySearchTree.BinaryNode> {
    protected class BinaryNode extends Node{

        protected BinaryNode(T value){
            super(value);
        }
        protected BinaryNode(){};
//        TIP Overloads blocks make protected methods from InnerClass Node
//        visible in BinarySearchTree field

        /*---Getters Overloads---*/
        protected T getValue(){
            return (T)super.getValue();
        }
        protected BinaryNode getLeft(){
            return (BinaryNode)super.getLeft();
        }
        protected BinaryNode getRight(){
            return (BinaryNode) super.getRight();
        }
        protected BinaryNode getParent(){
            return (BinaryNode) super.getParent();
        }
        /*-----------------------*/

        /*---Setters Overloads---*/
        protected void setValue(T value){
            super.setValue(value);
        }
        protected void setLeft(BinaryNode node){
            super.setLeft(node);
        }
        protected void setRight(BinaryNode node){
            super.setRight(node);
        }
        protected void setParent(BinaryNode node){
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



    //TODO find a way to use method .getParent(), not variable BinaryNode parent;
    private BinaryNode root;

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
            BinaryNode toAdd = new BinaryNode(value);
            if(direction > 0){
                parent.setRight(toAdd);
                toAdd.setParent(parent);

            }
            if(direction < 0) {
                parent.setLeft(toAdd);
                toAdd.setParent(parent);
            }
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

                while (searchNode.getLeft() != null){
                    min = searchNode.getLeft().getValue();
                    searchNode = searchNode.getLeft();
                }
                toDelete.setValue(min);

                //FIXME: исправить логику удаление элемента, замена значений происходит корректно
                if (searchNode.countChildren()==0){
                    if(searchNode.getParent().getRight() == toDelete) searchNode.getParent().setRight(null);
                    if(searchNode.getParent().getLeft() == toDelete) searchNode.getParent().setLeft(null);
                    return;
                }
                if (searchNode.countChildren()==1){
                    BinaryNode child_search = searchNode.getLeft() == null ? searchNode.getRight(): searchNode.getLeft(); // if left child is null, we'll use right child, otherwise we'll use right child

                    if(searchNode.getParent().getRight() == searchNode) searchNode.getParent().setRight(child_search);
                    else searchNode.getParent().setLeft(child_search);

                }

        }
    }

    //TODO разобраться до конца как все таки рабоатет симетричный обход
    //TODO add ORDER cases
    @Override
    public String show() {
        StringBuilder str = new StringBuilder("[ ");
        Stack<BinaryNode> stack = new Stack<>();
        BinaryNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.getLeft();
            }
            BinaryNode temp = stack.pop();
            str.append(temp.getValue()).append(" ");
            current = temp.getRight();
        }
        str.append("]");
        return str.toString();
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
            while(!queue.isEmpty()){
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
