package org.example.AbstracClasses;

import org.example.BasicStructures.BinarySearchTree;

import java.util.Comparator;

public abstract class AbstractTree <T ,N extends AbstractTree<T,N>.Node> {
    protected abstract class Node  {
        protected T value;
        protected N left =null;
        protected N right= null;
        protected N parent = null;

        protected Node(){};
        protected Node(T value){
            this.value =value;
        }
        /*---Getters---*/
        protected T getValue(){
            return this.value;
        }
        protected N getLeft(){
            return this.left;
        }
        protected N getRight(){
            return this.right;
        }
        protected N getParent(){
            return this.parent;
        }
        /*-----------*/

        /*---Setters---*/
        protected void setValue(T value){
            this.value = value;
        }
        protected void setLeft(N node){
            this.left = node;
        }
        protected void setRight(N node){
            this.right = node;
        }
        protected void setParent(N node){
            this.parent = node;
        }
        /*------------*/
    }
}
