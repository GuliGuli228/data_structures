package org.example.ComplexStructures;

import org.apache.commons.lang.NullArgumentException;
import org.example.AbstracClasses.RBTColors;
import org.example.BasicStructures.BinarySearchTree;
import org.example.BasicStructures.GenericLinkedList;
import org.example.Exceptions.EmptyBinaryTreeException;

/**
 * A Red-Black tree implementation that extends BinarySearchTree.
 * Maintains balance through color properties and rotations after insertions and deletions.
 * @param <T> the type of elements in the tree, must implement Comparable
 * @see BinarySearchTree
 */

public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    /**
     * A node in the Red-Black tree that extends BinaryNode and adds color tracking.
     * Contains methods for tree navigation and color management.
     */

    protected class RBTNode extends BinaryNode{
        protected RBTColors color;

        /**
         * Constructs an RBT node with specified value and color.
         * Automatically creates NIL children nodes.
         * @param value the value to store in this node
         * @param color the initial color of this node
         */

        protected RBTNode (T value, RBTColors color){
            NILNode leftNil = new NILNode();
            NILNode rightNil = new NILNode();
            leftNil.setParent(this);
            rightNil.setParent(this);
            this.value = value;
            this.color = color;
            this.left = leftNil;
            this.right = rightNil;
        }
        protected RBTNode (){}

        /*---Getters Overloads---*/
        public T getValue(){
            return (T)super.getValue();
        }
        protected RBTNode getLeft(){
            return (RBTNode) super.getLeft();
        }
        protected RBTNode getRight(){
            return (RBTNode) super.getRight();
        }
        protected RBTNode getParent(){
            return (RBTNode) super.getParent();
        }
        protected RBTColors getColor(){
            return color;
        }
        /*-----------------------*/

        /*---Setters Overloads---*/
        protected void setValue(T value){
            super.setValue(value);
        }
        protected void setLeft(RBTNode node){
            super.setLeft(node);
        }
        protected void setRight(RBTNode node){
            super.setRight(node);
        }
        protected void setParent(RBTNode node){
            super.setParent(node);
        }
        protected void setColor(RBTColors color){
            this.color = color;
        }
        /*-----------------------*/

        /*---Methods---*/

        /**
         * Checks if this node is a NIL node.
         * @return true if this is a NIL node, false otherwise
         */

        protected boolean IsNIL(){
            return this instanceof NILNode;
        }

        /**
         * Gets the grandparent of this node.
         * @return the grandparent node, or null if doesn't exist
         */

        protected RBTNode getGrandDad(){
            return this.getParent().getParent();
        }

        /**
         * Gets the uncle of this node (parent's sibling).
         * @return the uncle node, or null if doesn't exist
         */

        protected RBTNode getUncle(){
            RBTNode gDad = this.getGrandDad();
            if (gDad.getLeft() == this.getParent()) return gDad.getRight();
            if (gDad.getRight() == this.getParent()) return gDad.getLeft();
            return null;
        }
        /**
         * Gets the sibling of this node.
         * @return the sibling node
         */

        protected RBTNode getBrother(){
            if(this.getParent().getRight() == this) return this.getParent().getLeft();
            return this.getParent().getRight();
        }

        /**
         * Counts the number of non-NIL children this node has.
         * @return 0 if no children, 1 if one child, 2 if two children
         */

        @Override
        protected int countChildren() {
            if (!this.getLeft().IsNIL()&& !this.getRight().IsNIL()) return 2;
            if(this.getLeft().IsNIL()&& this.getRight().IsNIL()) return 0;
            else return 1;
        }
        /*------------*/

    }


    /**
     * Represents a NIL (leaf) node in the Red-Black tree.
     * All NIL nodes are black and have no children.
     */

    protected class NILNode extends RBTNode{
        protected NILNode (){
            this.value = null;
            this.left = null;
            this.right = null;
            this.color = RBTColors.BLACK;
        }
    }

    /**
     * Adds a value to the tree and rebalances if necessary.<br>
     * The method:<br>
     * 1. Finds the insertion point while maintaining tree structure<br>
     * 2. Inserts new node as red<br>
     * 3. Performs recoloring and rotations to restore Red-Black properties<br>
     * @param value the value to add
     * @throws NullArgumentException if the value is null
     */

    @Override
    public void add(T value){
        if(value == null) throw new NullArgumentException("value is null");

        if(this.IsEmpty()){
            root = new RBTNode(value, RBTColors.BLACK); // Black height
            return;
        }

        RBTNode current = (RBTNode)root;
        RBTNode toAdd = new RBTNode(value, RBTColors.RED);

        RBTNode parent = null;
        int direction = 0;

        while (!current.IsNIL()){
            parent = current;
            direction = value.compareTo(current.getValue());
            if(direction>0) current = current.getRight();
            if(direction <0) current = current.getLeft();
            if(direction == 0) update(BFS(value).getValue(), value);
        }

        if(direction > 0){
            parent.setRight(toAdd);
            toAdd.setParent(parent);

        }
        if(direction < 0) {
            parent.setLeft(toAdd);
            toAdd.setParent(parent);
        }
        this.insertBalance(toAdd); // Fix any Red-Black violations

        RBTNode RootNode = toAdd;
        while (RootNode.getParent() != null) RootNode = RootNode.getParent();
        root = RootNode;
        ((RBTNode) root).setColor(RBTColors.BLACK);
    }

    /**
     * Deletes a value from the tree and rebalances if necessary.
     * Handles different cases based on node color and number of children.
     * @param value the value to delete
     * @throws NullArgumentException if the value is null
     * @throws EmptyBinaryTreeException if tree is empty
     */

    @Override
    public void deleteByValue(T value){
        if(value == null) throw new NullArgumentException("value is null");
        if (this.IsEmpty()) throw new EmptyBinaryTreeException();
        RBTNode toDelete = BFS(value);

        if (toDelete == root) {
            root = null;
            return;
        }

        if (toDelete.countChildren() == 2){
            this.deleteAnyNodeWithTwoChildren(toDelete);
            return;
        }
        if (toDelete.countChildren() == 1) {
            this.deleteBlackNodeWithOneChildren(toDelete);
            return;
        }
        if (toDelete.countChildren() == 0){
            if(toDelete.getColor() == RBTColors.RED) this.deleteRedNode(toDelete);
            if (toDelete.getColor() == RBTColors.BLACK) this.deleteBlackNodeWithZeroChildren(toDelete);
        }
        RBTNode RootNode = toDelete;
        while (RootNode.getParent() != null){
            RootNode = RootNode.getParent();
        }
        root = RootNode;
    }

    /**
     * Finds the minimum value in the subtree rooted at the specified node.
     * @param toDelete the root of the subtree to search
     * @return the node containing the minimum value
     */

    @Override
    protected Node findMin(Node toDelete){
        ((RBTNode)toDelete).getRight().getValue(); // Going to right branch to search min, another way - going left to search for max
        RBTNode searchNode = (RBTNode)toDelete;
        searchNode = searchNode.getRight();

        while (!searchNode.getLeft().IsNIL()){
            searchNode = searchNode.getLeft();
        }
        return searchNode;
    }

    /**
     * Performs breadth-first search for a value in the tree. Uses queue to realize iterative search
     * @param value the value to search for
     * @return the node containing the value, or null if not found
     * @throws EmptyBinaryTreeException if tree is empty
     * @see Queue
     */


    @Override
    public RBTNode BFS(T value){
        if(this.IsEmpty()) throw new EmptyBinaryTreeException();
        RBTNode current = (RBTNode) root;
        Queue<RBTNode> queue = new Queue<>();
        queue.enqueue(current);

        while(!queue.isEmpty()){
            current = queue.dequeue();
            if(value.compareTo(current.getValue())==0) return current;
            else{
                if(!current.getLeft().IsNIL())queue.enqueue(current.getLeft());
                if(!current.getRight().IsNIL()) queue.enqueue(current.getRight());
            }
        }
        return null;
    }

    /**
     * Performs depth-first search for a value in the tree. Uses stack to realize iterative search
     * @param value the value to search for
     * @return the node containing the value, or null if not found
     * @throws EmptyBinaryTreeException if tree is empty
     * @see Stack
     */

    @Override
    public RBTNode DPS(T value){
        if(this.IsEmpty()) throw new EmptyBinaryTreeException();
        RBTNode current = (RBTNode) root;
        Stack<RBTNode> stack = new Stack<>();
        stack.push(current);

        while (!stack.isEmpty()){
            current = stack.pop();
            if(value.compareTo(current.getValue()) == 0){
                return current;
            }
            else{
                if(!current.getRight().IsNIL()) stack.push(current.getRight());
                if(!current.getLeft().IsNIL())   stack.push(current.getLeft());
            }
        }
        return null;
    }

    /**
     * Returns string representation of the tree using in-order traversal. Uses stack for in-order traversal
     *
     * @see Stack
     */

    @Override
    public void show(){
        System.out.println(this);
    }
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("[ ");
        Stack<RBTNode> stack = new Stack<>();
        RBTNode current = (RBTNode) root;
        if (this.IsEmpty()) {
            return "null";
        }

        while ( !current.IsNIL() || !stack.isEmpty()) {
            while (!current.IsNIL()) {
                stack.push(current);
                current = current.getLeft();
            }
            RBTNode temp = stack.pop();
            if (temp.IsNIL()) continue;
            str.append(temp.getValue()).append(" ");
            current = temp.getRight();
        }
        str.append("]");
        return str.toString();
    }

    public Object[] toArray(){
        GenericLinkedList<T> list = new GenericLinkedList<>();
        Stack<RBTNode> stack = new Stack<>();
        RBTNode current = (RBTNode) root;
        if (this.IsEmpty()) return null;
        while ( !current.IsNIL() || !stack.isEmpty()) {
            while (!current.IsNIL()) {
                stack.push(current);
                current = current.getLeft();
            }
            RBTNode temp = stack.pop();
            list.add(temp.getValue());
            if (temp.IsNIL()) continue;
            current = temp.getRight();
        }
        return list.toArray();
    }
    /*---private methods ---*/

    //TODO: переписать все комментарии на английский
    /**
     * Balances the tree after insertion to maintain Red-Black properties.
     * Handles different cases based on uncle's color and node position.
     * @param node the newly inserted node to balance around
     */

    private void insertBalance(RBTNode node){

        if (node.getParent().color == RBTColors.RED){ // Нарушение балансировки
            if(node.getUncle().color == RBTColors.RED){ // Если дядя красный
                node.getParent().setColor(RBTColors.BLACK); // Перекрашиваем родителя и дядю в черный
                node.getUncle().setColor(RBTColors.BLACK);
                if(node.getGrandDad() != root) node.getGrandDad().setColor(RBTColors.RED); // Если дед не корень перекрашиваем в красный
            }
            if (node.getUncle().color == RBTColors.BLACK){ // если дядя черный
                // Сначала обработка левых случаев (лево-левый, лево-правый), затем обработка правых случаев
                if(node.getGrandDad() != null && node.getGrandDad().getLeft() == node.getParent()){
                    if (node.getParent().getRight() == node){ //если новый узел правый ребенок родителя (лево-правый случай и переход в лево-левый)
                        this.LeftTurn(node.getParent());
                        node = node.getLeft();
                    }
                    node.getParent().setColor(RBTColors.BLACK);
                    node.getGrandDad().setColor(RBTColors.RED);// лево-левый случай
                    this.RightTurn(node.getGrandDad());
                }
                //Обработка правых случаев
                if(node.getGrandDad() !=null && node.getGrandDad().getRight() == node.getParent()){
                    if(node.getParent().getLeft() == node){ // Новый узел левый ребенок родителя
                        this.RightTurn(node.getParent());
                        node = node.getRight();
                    }
                    node.getParent().setColor(RBTColors.BLACK);
                    node.getGrandDad().setColor(RBTColors.RED); // право-правый случай
                    this.LeftTurn(node.getGrandDad());
                }
            }
        }
    }

    //TODO: Проверить левый и правый поворот в AVL дереве
    /**
     * Performs a left rotation around the specified node.
     * Used to balance the tree during insertions and deletions.
     * @param node the node to rotate around
     */

    private void LeftTurn(RBTNode node) {
        if (node == null || node.getRight() == null) return;

        RBTNode rightChild = node.getRight();
        RBTNode temp = rightChild.getLeft();
        rightChild.setParent(node.getParent());

        if (node.getParent() == null) this.root = rightChild;
        else if (node.getParent().getLeft() == node) node.getParent().setLeft(rightChild);
        else node.getParent().setRight(rightChild);

        rightChild.setLeft(node);
        node.setParent(rightChild);
        node.setRight(temp);

        if (temp != null) temp.setParent(node);
    }

    /**
     * Performs a right rotation around the specified node.
     * Used to balance the tree during insertions and deletions.
     * @param node the node to rotate around
     */

    private void RightTurn(RBTNode node) {
        RBTNode leftChild = node.getLeft();
        RBTNode temp = leftChild.getRight();
        leftChild.setParent(node.getParent());

        if (node.getParent() == null) {
            // Если node - корень, обновляем корень дерева
            this.root = leftChild;
        } else if (node.getParent().getLeft() == node) {
            node.getParent().setLeft(leftChild);
        } else {
            node.getParent().setRight(leftChild);
        }

        leftChild.setRight(node);
        node.setParent(leftChild);
        node.setLeft(temp);

        if (temp != null) {
            temp.setParent(node);
        }
    }

    /**
     * Handles deletion of a node with two children by replacing it with its successor.
     * @param toDelete the node to delete
     */

    private void deleteAnyNodeWithTwoChildren(RBTNode toDelete){

        RBTNode searchNode = (RBTNode)this.findMin(toDelete);

        toDelete.setValue(searchNode.getValue());
        if (searchNode.getColor() ==  RBTColors.RED){
            this.deleteRedNode(searchNode);
        }
        if(searchNode.getColor() ==  RBTColors.BLACK){
            if(searchNode.countChildren() == 0) this.deleteBlackNodeWithZeroChildren(searchNode);
            if(searchNode.countChildren() == 1) this.deleteBlackNodeWithOneChildren(searchNode);
        }
    }

    /**
     * Deletes a red node from the tree (simple case with no balance violations).
     * @param toDelete the red node to delete
     */

    private void deleteRedNode(RBTNode toDelete){
        NILNode nodeNil = new NILNode();
        nodeNil.setParent(toDelete.getParent());

        if (toDelete.getParent().getRight() == toDelete) toDelete.getParent().setRight(nodeNil);
        else toDelete.getParent().setLeft(nodeNil);
    }

    /**
     * Deletes a black node with no children, handling possible balance violations.
     * Implements Red-Black tree deletion cases 1-4.
     * @param toDelete the black node to delete
     */

    private void deleteBlackNodeWithZeroChildren (RBTNode toDelete){

        NILNode nodeNil = new NILNode();
        if (toDelete.getParent().getRight() == toDelete) toDelete.getParent().setRight(nodeNil);
        else toDelete.getParent().setLeft(nodeNil);
        nodeNil.setParent(toDelete.getParent());

        RBTNode current = nodeNil;
        while (current != root && current.getColor()== RBTColors.BLACK) {
            RBTNode brother = current.getBrother();
            RBTNode parent = current.getParent();
            if (current == current.getParent().getLeft()){ // если current - левый ребенок
                // Случай 1: Брат - красный -> cводим к случаям 2-4
                if (brother.getColor() == RBTColors.RED) {
                    brother.setColor(RBTColors.BLACK);
                    parent.setColor(RBTColors.RED);
                    this.LeftTurn(parent);
                    brother = parent.getRight();
                }
                // Случай 2: Брат - черный, оба его ребенка тоже
                if (brother.getLeft().getColor() == RBTColors.BLACK && brother.getRight().getColor() == RBTColors.BLACK){
                    brother.setColor(RBTColors.RED);
                    current = parent;
                }
                //Случай 3: Брат - черный, правый племянник - черный, левый - красный
                if (brother.getRight().getColor() == RBTColors.BLACK &&  brother.getLeft().getColor() == RBTColors.RED){
                    brother.getLeft().setColor(RBTColors.BLACK);
                    brother.setColor(RBTColors.RED);
                    this.RightTurn(brother);
                    brother = parent.getRight();
                }
                //Случай 4: Брат - черный, правый племянник - красный, левый - черный
                if (brother.getRight().getColor() == RBTColors.RED &&  brother.getLeft().getColor() == RBTColors.BLACK){
                    brother.setColor(parent.getColor());
                    parent.setColor(RBTColors.BLACK);
                    brother.getRight().setColor(RBTColors.BLACK);
                    this.LeftTurn(parent);
                    current = (RBTNode)root;
                }
            }
            if (current == current.getParent().getRight()){  // если current - правый ребенок
                // Случай 1: Брат - красный -> cводим к случаям 2-4
                if (brother.getColor() == RBTColors.RED) {
                    brother.setColor(RBTColors.BLACK);
                    parent.setColor(RBTColors.RED);
                    this.RightTurn(parent);
                    brother = parent.getLeft();
                }
                // Случай 2: Брат - черный, оба его ребенка тоже
                if (brother.getLeft().getColor() == RBTColors.BLACK && brother.getRight().getColor() == RBTColors.BLACK){
                    brother.setColor(RBTColors.RED);
                    current = parent;
                }
                //Случай 3: Брат - черный, правый племянник - черный, левый - красный
                if (brother.getRight().getColor() == RBTColors.BLACK &&  brother.getLeft().getColor() == RBTColors.RED){
                    brother.getRight().setColor(RBTColors.BLACK);
                    brother.setColor(RBTColors.RED);
                    this.LeftTurn(brother);
                    brother = parent.getLeft();
                }
                //Случай 4: Брат - черный, правый племянник - красный, левый - черный
                if (brother.getRight().getColor() == RBTColors.RED &&  brother.getLeft().getColor() == RBTColors.BLACK){
                    brother.setColor(parent.getColor());
                    parent.setColor(RBTColors.BLACK);
                    brother.getLeft().setColor(RBTColors.BLACK);
                    this.RightTurn(parent);
                    current = (RBTNode)root;
                }
            }
        }
        current.setColor(RBTColors.BLACK);
    }

    /**
     * Deletes a black node with one child (which must be red by Red-Black properties).
     * @param toDelete the black node to delete
     */

    private void deleteBlackNodeWithOneChildren(RBTNode toDelete){
        RBTNode children = toDelete.getRight().IsNIL()? toDelete.getLeft() : toDelete.getRight();
        toDelete.setValue(children.getValue());
        if(children.getColor() ==  RBTColors.RED){
            this.deleteRedNode(children);
        }
    }
}
