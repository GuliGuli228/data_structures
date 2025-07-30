package org.example.ComplexStructures;

import org.example.BasicStructures.BinarySearchTree;
import org.example.Interfaces.Tree;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    protected class AVLNode extends BinaryNode {
        protected int height = 0;

//        TIP Overloads blocks make protected methods from InnerClass Node
//        visible in BinarySearchTree field

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

        /*---Methods---*/
        protected int countChildren(){
            if (this.left != null && this.right != null) return 2;
            if(this.left == null && this.right == null) return 0;
            else return 1;
        }
        protected int getHeight(){
            return this==null? -1: this.height;
        }

        protected void updateHeight(){
            this.height = Math.max(this.getLeft().height, this.getRight().height) + 1;
        }

        protected void swapValueTo(AVLNode node){
            T value_a = (T)this.value;
            this.value = node.value;
            node.value = value_a;
        }
        protected int getBalance(){
            return (this == null) ? 0 : this.getRight().getHeight()- this.getLeft().getHeight();
        }
    }

    private AVLNode root;
    @Override
    public void add(T value) {
        if(this.IsEmpty()) root = new AVLNode(value);
        else{
            AVLNode current = root;
            AVLNode parent = null;
            Stack<AVLNode> NodeStack = new Stack<>();
            int direction = 0;
            while (current != null){
                parent = current;
                NodeStack.push(current);
                direction = value.compareTo(current.getValue());
                if(direction>0) current = current.getRight();
                if(direction <0) current = current.getLeft();
                if(direction == 0) return;
            }
            AVLNode NodeToAdd = new AVLNode(value);
            NodeStack.push(NodeToAdd);
            if(direction > 0) parent.setRight(NodeToAdd);
            if(direction < 0) parent.setLeft(NodeToAdd);

            while(!NodeStack.isEmpty()){
                AVLNode node = NodeStack.pop();
                int balance = node.getBalance();

                if(balance < 0){
                    this.LeftTurn(node);
                    return;
                }
                if(balance > 0) {
                    this.RightTurn(node);
                    return;
                }
            }
        }
    }
    private void LeftTurn(AVLNode node){

    }

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

    private void swapValues(AVLNode node1, AVLNode node2){
        T value1 = node1.getValue();
        T value2 = node2.getValue();
        node1.setValue(value2);
        node2.setValue(value1);
    }

}
