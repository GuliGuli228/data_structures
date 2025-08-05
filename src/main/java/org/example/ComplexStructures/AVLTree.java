package org.example.ComplexStructures;

import org.apache.commons.lang.NullArgumentException;
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


        protected void updateHeight(){
            int LeftHeight = this.getLeft() == null ? -1 : this.getLeft().height;
            int RightHeight = this.getRight() == null ? -1 : this.getRight().height;
            this.height = Math.max(LeftHeight, RightHeight) + 1;
        }

        protected void swapValueTo(AVLNode node){
            T value_a = (T)this.value;
            this.value = node.value;
            node.value = value_a;
        }
        protected int getBalance(){
            return (this == null) ? 0 : this.getRight().height- this.getLeft().height;
        }
    }
    /*---Variables---*/

    /*---------------*/

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
        if(direction > 0) parent.setRight(NodeToAdd);
        if(direction < 0) parent.setLeft(NodeToAdd);

        while (!NodeStack.isEmpty()){
            current = NodeStack.pop();
            current.updateHeight();
        }

        while(!NodeStack.isEmpty()){
            AVLNode node = NodeStack.pop();
            int balance = node.getBalance();
            //TODO: вынести этот код в отдельный метод балансировки
            if(balance == -2){
                if(node.getLeft().getBalance()==1) this.LeftTurn(node.getLeft());
                this.RightTurn(node);
                return;
            }
            if(balance == 2) {
                if(node.getRight().getBalance()==1) this.RightTurn(node.getRight());
                this.LeftTurn(node);
                return;
            }
        }
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

    private void LeftTurn(AVLNode node){
        this.swapValues(node,node.getRight());
        AVLNode temp = node.getLeft();
        node.setLeft(node.getRight());
        node.setRight(node.getLeft().getRight());
        node.getRight().setLeft(node.getRight().getRight());
        node.getLeft().setLeft(temp);
        node.getLeft().updateHeight();
        node.updateHeight();

    }

    private void swapValues(AVLNode node1, AVLNode node2){
        T value1 = node1.getValue();
        T value2 = node2.getValue();
        node1.setValue(value2);
        node2.setValue(value1);
    }
    @Override
    public AVLNode BFS(T value){
        return (AVLNode) super.BFS(value);
    }
    @Override
    public AVLNode DPS (T value){
        return (AVLNode) super.DPS(value);
    }
    @Override
    public boolean IsEmpty(){
        return super.IsEmpty();
    }
    private Stack<AVLNode> findPathToValue(AVLNode node){
        Stack<AVLNode> path = new Stack<>();

        while(node!=null){
            path.push(node.getParent());
            node = node.getParent();
        }
        return path;
    };
}
