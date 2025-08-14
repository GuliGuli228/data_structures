package org.example.ComplexStructures;

import net.jpountz.xxhash.XXHash32;
import net.jpountz.xxhash.XXHashFactory;
import org.apache.commons.lang.NullArgumentException;
import org.example.AbstracClasses.RBTColors;
import org.example.BasicStructures.BinarySearchTree;
import org.example.Exceptions.EmptyBinaryTreeException;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;


public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    protected class RBTNode extends BinaryNode{
        protected RBTColors color;
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
        protected boolean IsNIL(){
            return this instanceof NILNode;
        }

        protected RBTNode getGrandDad(){
            return this.getParent().getParent();
        }

        protected RBTNode getUncle(){
            RBTNode gDad = this.getGrandDad();
            if (gDad.getLeft() == this.getParent()) return gDad.getRight();
            if (gDad.getRight() == this.getParent()) return gDad.getLeft();
            return null;
        }
        protected RBTNode getBrother(){
            if(this.getParent().getRight() == this) return this.getParent().getLeft();
            return this.getParent().getRight();
        }
        @Override
        protected int countChildren() {
            if (!this.getLeft().IsNIL()&& !this.getRight().IsNIL()) return 2;
            if(this.getLeft().IsNIL()&& this.getRight().IsNIL()) return 0;
            else return 1;
        }

        /*------------*/


    }
    protected class NILNode extends RBTNode{
        protected NILNode (){
            this.value = null;
            this.left = null;
            this.right = null;
            this.color = RBTColors.BLACK;
        }
    }


    //TODO: реализовать полиморфизм в super методе, чтобы не повторять код вставки
    @Override
    public void add(T value){
        if(value == null) throw new NullArgumentException("value is null");

        if(this.IsEmpty()){
            root = new RBTNode(value, RBTColors.BLACK); // Black height
            return;
        }

        RBTNode current = (RBTNode)root;
        RBTNode parent = null;
        int direction = 0;

        while (!current.IsNIL()){
            parent = current;
            direction = value.compareTo(current.getValue());
            if(direction>0) current = current.getRight();
            if(direction <0) current = current.getLeft();
            if(direction == 0) return;
        }
        RBTNode toAdd = new RBTNode(value, RBTColors.RED);
        if(direction > 0){
            parent.setRight(toAdd);
            toAdd.setParent(parent);

        }
        if(direction < 0) {
            parent.setLeft(toAdd);
            toAdd.setParent(parent);
        }
        this.insertBalance(toAdd);

        RBTNode RootNode = toAdd;
        while (RootNode.getParent() != null) RootNode = RootNode.getParent();
        root = RootNode;
        ((RBTNode) root).setColor(RBTColors.BLACK);
    }

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

    @Override
    public String show(){
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
    /*---private methods ---*/
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

    private void deleteRedNode(RBTNode toDelete){
        NILNode nodeNil = new NILNode();
        nodeNil.setParent(toDelete.getParent());

        if (toDelete.getParent().getRight() == toDelete) toDelete.getParent().setRight(nodeNil);
        else toDelete.getParent().setLeft(nodeNil);
    }

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
    private void deleteBlackNodeWithOneChildren(RBTNode toDelete){
        RBTNode children = toDelete.getRight().IsNIL()? toDelete.getLeft() : toDelete.getRight();
        toDelete.setValue(children.getValue());
        if(children.getColor() ==  RBTColors.RED){
            this.deleteRedNode(children);
        }
    }
}
