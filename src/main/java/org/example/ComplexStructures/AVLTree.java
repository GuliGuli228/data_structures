package org.example.ComplexStructures;

import org.example.BasicStructures.BinarySearchTree;
import org.example.Interfaces.Tree;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    protected class AVLNode extends BinaryNode {

//        TIP Overloads blocks make protected methods from InnerClass Node
//        visible in BinarySearchTree field

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
    }

}
