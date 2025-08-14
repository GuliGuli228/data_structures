package org.example.BasicStructures;

import org.apache.commons.lang.NullArgumentException;
import org.example.AbstracClasses.AbstractTree;
import org.example.ComplexStructures.Queue;
import org.example.ComplexStructures.Stack;
import org.example.Exceptions.EmptyBinaryTreeException;
import org.example.Interfaces.Tree;

/**
 * A binary search tree implementation that supports adding, removing, and searching elements.
 * Inherits from AbstractTree and implements the Tree interface.
 * @param <T> the type of elements in the tree, must implement Comparable
 * @see AbstractTree
 * @see Tree
 * @see EmptyBinaryTreeException
 */

public class BinarySearchTree<T extends Comparable<T>> extends AbstractTree<T, BinarySearchTree<T>.BinaryNode> implements Tree<T, BinarySearchTree.BinaryNode> {
    protected class BinaryNode extends Node{

        /**
         * A node in the binary search tree, containing a value and references to left, right, and parent nodes.
         * Extends the abstract Node class from AbstractTree.
         * @see AbstractTree.Node
         */

        protected BinaryNode(T value){
            super(value);
        }
        protected BinaryNode(){};

        /**
         * Overloading makes protected fields from InnerClass Node accessible in OuterClass BinarySearchTree<T>
         */

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

        /**
         * Counts the number of children this node has.
         * @return 0 if no children, 1 if one child, 2 if two children
         */

        /*---Methods---*/
        protected int countChildren(){
            if (this.left != null && this.right != null) return 2;
            if(this.left == null && this.right == null) return 0;
            else return 1;
        }
        /*------------*/
    }


    /*---Variables---*/
    protected BinaryNode root;
    /*---------------*/

    /**
     * Adds a new value to the tree.
     * @param value the value to add
     * @throws NullArgumentException if the value is null
     */

    @Override
    public void add(T value) {
        if(value == null ) throw new NullArgumentException("value is null");
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

    /**
     * Deletes the node containing the specified value from the tree. Uses BFS to find node to delete by value
     * @param value the value to delete
     * @see #BFS(Comparable) 
     */

    @Override
    public void deleteByValue(T value) {
        BinaryNode toDelete = BFS(value);
        if(toDelete == null) return;

        switch (toDelete.countChildren()){
            case 0:
                this.deleteNodeWithZeroChildren(toDelete);
                return;
            case 1:
                this.deleteNodeWithOneChild(toDelete);
                return;
            case 2:
                BinaryNode searchNode =  (BinaryNode) this.findMin(toDelete);
                toDelete.setValue(searchNode.getValue());

                if (searchNode.countChildren()==0){
                    this.deleteNodeWithZeroChildren(searchNode);
                    return;
                }
                if (searchNode.countChildren()==1){
                    this.deleteNodeWithOneChild(searchNode);
                }

        }
    }

    /**
     * Returns a string representation of the tree. Realize iterative in-order traversal using Stack
     * @return a string containing all elements of the tree in order
     * @see Stack
     */

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

    /**
     * Updates a value in the tree by replacing it with a new value.
     * @param from the value to be replaced
     * @param to the new value
     * @throws NullArgumentException if either parameter is null
     */

    @Override
    public void update(T from, T to) {
        if (this.IsEmpty()) return;
        if(from == null || to == null) throw new NullArgumentException("from and to can't be null");
        BinaryNode current = BFS(from);
        if(current == null) return;
        current.setValue(to);
    }

    /**
     * Searches for a value in the tree using breadth-first search. Uses Queue for iterative search
     * @param value the value to search for
     * @return the node containing the value, or null if not found
     * @throws EmptyBinaryTreeException if the tree is empty
     * @see Queue
     */

    @Override
    public BinaryNode BFS(T value) {
        if(this.IsEmpty()) throw new EmptyBinaryTreeException();
        BinaryNode current = root;
        Queue<BinaryNode> queue = new Queue<>();
        queue.enqueue(current);

        while(!queue.isEmpty()){
            current = queue.dequeue();
            if(value.compareTo(current.getValue())==0) return current;
            else{
                if(current.getLeft()!= null)queue.enqueue(current.getLeft());
                if(current.getRight()!=null) queue.enqueue(current.getRight());
            }
        }
        return null;
    }


    /**
     * Searches for a value in the tree using depth-first search. Uses Stack for iterative search
     * @param value the value to search for
     * @return the node containing the value, or null if not found
     * @throws EmptyBinaryTreeException if the tree is empty
     * @see Stack
     */

    @Override
    public BinaryNode DPS(T value) {
        if(this.IsEmpty()) throw new EmptyBinaryTreeException();
        BinaryNode current = root;
        Stack<BinaryNode> stack = new Stack<>();
        stack.push(current);

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
        return null;
    }

    /**
     * Checks if the tree is empty.
     * @return true if the tree is empty, false otherwise
     */
    @Override
    public boolean IsEmpty() {
        return (root == null);
    }

    /**
     * Finds the minimum value in the subtree rooted at the specified node.
     * @param toDelete the root of the subtree to search
     * @return the node containing the minimum value
     */

    protected Node findMin (Node toDelete) {
        ((BinaryNode)toDelete).getRight().getValue(); // Going to right branch to search min, another way - going left to search for max
        BinaryNode searchNode = (BinaryNode)toDelete;
        searchNode = searchNode.getRight();

        while (searchNode.getLeft() != null){
            searchNode = searchNode.getLeft();
        }
        return searchNode;
    }

    /**
     * Deletes a node with no children from the tree.
     * @param toDelete the node to delete
     */

    protected void deleteNodeWithZeroChildren(Node toDelete) {
        BinaryNode toDeleteNode = (BinaryNode)toDelete;
        if(toDeleteNode.getParent().getRight() == toDelete) toDeleteNode.getParent().setRight(null);
        if(toDeleteNode.getParent().getLeft() == toDelete) toDeleteNode.getParent().setLeft(null);
    }

    /**
     * Deletes a node with one child from the tree.
     * @param toDelete the node to delete
     */

    protected void deleteNodeWithOneChild(Node toDelete) {
        BinaryNode toDeleteNode = (BinaryNode)toDelete;
        BinaryNode child = toDeleteNode.getLeft() == null ? toDeleteNode.getRight(): toDeleteNode.getLeft(); // if left child is null, we'll use right child, otherwise we'll use right child
        child.setParent(toDeleteNode.getParent());

        if(toDeleteNode.getParent().getRight() == toDelete) toDeleteNode.getParent().setRight(child);
        else toDeleteNode.getParent().setLeft(child);
    }
}
