package org.example.AbstracClasses;

import java.util.Comparator;

public abstract class AbstractTree <T,N extends AbstractTree.Node> {
    protected class Node  {
        protected T value;
        protected N left;
        protected N right;
        protected N parent;

        protected Node(){};
        protected Node(T value){
            this.value =value;
        }
        protected T getValue(){
            return this.value;
        }
        protected void setLeft(N left){
            this.left = left;
        }

        protected void setValue(T value){
            this.value = value;
        }
    }
}
