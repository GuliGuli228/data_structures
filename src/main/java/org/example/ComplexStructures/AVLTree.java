package org.example.ComplexStructures;

import org.apache.commons.lang.NullArgumentException;
import org.example.BasicStructures.BinarySearchTree;

/**
 * An AVL tree implementation that extends BinarySearchTree and maintains balance
 * through rotations after insertions and deletions.
 * @param <T> the type of elements in the tree, must implement Comparable
 * @see BinarySearchTree
 */

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    protected class AVLNode extends BinaryNode {
        protected int height = 0;

        /**
         * Overloading makes protected fields from InnerClass BinaryNode accessible in OuterClass AVLTree<T>
         */

        protected AVLNode (T value){
            this.value= value;
        }
        protected AVLNode (){
        }
        /*---Getters Overloads---*/
        protected T getValue(){
            return (T)super.getValue();
        }
        protected AVLNode getLeft(){
            return (AVLNode) super.getLeft();
        }
        protected AVLNode getRight(){
            return (AVLNode) super.getRight();
        }
        protected AVLNode getParent(){
            return (AVLNode) super.getParent();
        }
        /*-----------------------*/

        /*---Setters Overloads---*/
        protected void setValue(T value){
            super.setValue(value);
        }
        protected void setLeft(AVLNode node){
            super.setLeft(node);
        }
        protected void setRight(AVLNode node){
            super.setRight(node);
        }
        protected void setParent(AVLNode node){
            super.setParent(node);
        }
        /*-----------------------*/

        /**
         * Updates the height of this node based on children's heights..
         * Leaf nodes have height 0 (declared in height field).
         */

        protected void updateHeight(){
            int LeftHeight = this.getLeft() == null ? -1 : this.getLeft().height;
            int RightHeight = this.getRight() == null ? -1 : this.getRight().height;
            this.height = Math.max(LeftHeight, RightHeight) + 1;
        }
        /**
         * Calculates the balance factor of this node.
         * Balance factor is difference between right and left subtree heights.
         * @return positive if right-heavy, negative if left-heavy, 0 if balanced
         */

        protected int getBalance(){
            int LeftHeight = this.getLeft() == null ? -1 : this.getLeft().height;
            int RightHeight = this.getRight() == null ? -1 : this.getRight().height;
            return RightHeight - LeftHeight;
        }
    }

    /**
     * Adds a value to the tree and rebalances if necessary.<br>
     * The method:<br>
     * 1. Finds the insertion point while tracking visited nodes<br>
     * 2. Inserts the new node<br>
     * 3. Updates heights of affected nodes<br>
     * 4. Performs rotations to restore balance if needed<br>
     * @param value the value to add
     * @throws NullArgumentException if the value is null
     */

    @Override
    public void add(T value) {
        if(value == null) throw new NullArgumentException("value is null");

        if(this.IsEmpty()){
            root = new AVLNode(value);
            return;
        }

        AVLNode current = (AVLNode) root;
        AVLNode parent = null;
        Stack<AVLNode> NodeStack = new Stack<>();
        Stack<AVLNode> NodeStackToUpdateHeights = new Stack<>(); // Stack to update heights in proper order, caused by iterative, not recursive approach
        int direction = 0;
        while (current != null){
            parent = current;
            NodeStack.push(current);
            NodeStackToUpdateHeights.push(current);
            direction = value.compareTo(current.getValue());
            if(direction>0) current = current.getRight();
            if(direction <0) current = current.getLeft();
            if(direction == 0) return;
        }
        AVLNode NodeToAdd = new AVLNode(value);
        if(direction > 0) {
            parent.setRight(NodeToAdd);
            NodeToAdd.setParent(parent);
        }
        if(direction < 0) {
            parent.setLeft(NodeToAdd);
            NodeToAdd.setParent(parent);
        }

        while (!NodeStackToUpdateHeights.isEmpty()){
            current = NodeStackToUpdateHeights.pop();
            current.updateHeight();
        }

        while(!NodeStack.isEmpty()){
            AVLNode node = NodeStack.pop();
            this.balance(node);
        }
        AVLNode RootNode = (AVLNode) root;
        while(RootNode.getParent() != null){
            RootNode = RootNode.getParent();
        }
        root = RootNode;
    }

    /**
     * Deletes a value from the tree and rebalances if necessary.<br>
     * The method:<br>
     * 1. Finds the node to delete and tracks its ancestors<br>
     * 2. Deletes the node using parent class implementation<br>
     * 3. Updates heights of affected nodes<br>
     * 4. Performs rotations to restore balance if needed<br>
     * @param value the value to delete
     * @throws NullArgumentException if the value is null
     */

    @Override
    public void deleteByValue(T value) {
        if(value == null) throw new NullArgumentException("value is null");
        Stack<AVLNode> NodeStackToUpUpdateHeights = this.findPathToValue(BFS(value));
        super.deleteByValue(value);

        while(!NodeStackToUpUpdateHeights.isEmpty()){
            AVLNode temp = NodeStackToUpUpdateHeights.pop();
            temp.updateHeight();
        }
        AVLNode current = (AVLNode) root;
        while(!NodeStackToUpUpdateHeights.isEmpty()){
            AVLNode node = NodeStackToUpUpdateHeights.pop();
            this.balance(node);
        }
        while (current.getParent() != null){
            current=current.getParent();
        }
        root = current;

    }

    /**
     * Performs breadth-first search for a value in the tree.
     * @param value the value to search for
     * @return the node containing the value, or null if not found
     */

    @Override
    public AVLNode BFS(T value){
        return (AVLNode) super.BFS(value);
    }

    /**
     * Performs depth-first search for a value in the tree.
     * @param value the value to search for
     * @return the node containing the value, or null if not found
     */

    @Override
    public AVLNode DPS (T value){
        return (AVLNode) super.DPS(value);
    }

    /**
     * Returns string representation of the tree using in-order traversal.
     */

    @Override
    public void show(){
        super.show();
    }


    /**
     * Updates a value in the tree by replacing it with a new value.
     * @param from the value to be replaced
     * @param to the new value
     */

    @Override
    public void update(T from, T to){
        super.update(from,to);
    }

    /*---private methods---*/

    /**
     * Performs a right rotation around the specified node.
     * Used when left subtree is taller (left-left or left-right case).
     * @param node the node to rotate around
     */

    private void RightTurn(AVLNode node){
        this.swapValues(node, node.getLeft());
        AVLNode temp = node.getRight();
        node.setRight(node.getLeft());
        node.setLeft(node.getRight().getLeft());
        node.getRight().setLeft(node.getRight().getRight());
        node.getRight().setRight(temp);
        node.getRight().updateHeight();
        node.updateHeight();
    }

    /**
     * Performs a left rotation around the specified node.
     * Used when right subtree is taller (right-right or right-left case).
     * @param node the node to rotate around
     */

    private void LeftTurn(AVLNode node){
        this.swapValues(node,node.getRight());
        AVLNode temp = node.getLeft();
        node.setLeft(node.getRight());
        node.setRight(node.getLeft().getRight());
        node.getLeft().setRight(node.getLeft().getLeft());
        node.getLeft().setLeft(temp);
        node.getLeft().updateHeight();
        node.updateHeight();

    }

    /**
     * Swaps values between two nodes.
     * @param node1 the first node
     * @param node2 the second node
     */

    private void swapValues(AVLNode node1, AVLNode node2){
        T value1 = node1.getValue();
        T value2 = node2.getValue();
        node1.setValue(value2);
        node2.setValue(value1);
    }

    /**
     * Finds and returns the path from a node to the root as a stack.
     * @param node the starting node
     * @return stack containing the path from node to root
     */

    private Stack<AVLNode> findPathToValue(AVLNode node){
        Stack<AVLNode> path = new Stack<>();

        while(node!=null){
            path.push(node);
            node = node.getParent();
        }
        return path;
    }

    /**
     * Balances the tree at the specified node if needed.<br>
     * Performs rotations based on balance factor:<br>
     * - Right rotation for left-heavy (balance -2)<br>
     * - Left rotation for right-heavy (balance +2)<br>
     * - Double rotations when inner subtrees are heavier<br>
     * @param node the node to balance
     */

    private void balance(AVLNode node){
        int balance = node.getBalance();
        if(balance == -2){
            if(node.getLeft().getBalance()==1) this.LeftTurn(node.getLeft());
            this.RightTurn(node);
            return;
        }
        if(balance == 2) {
            if(node.getRight().getBalance()==-1) this.RightTurn(node.getRight());
            this.LeftTurn(node);
        }
    }
}
