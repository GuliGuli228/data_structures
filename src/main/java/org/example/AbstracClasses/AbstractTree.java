package org.example.AbstracClasses;

import java.util.Comparator;

public abstract class AbstractTree <T> {
    protected class Node  {
        protected T value;
        protected Node left;
        protected Node right;

        protected Node(){};

        protected T getValue(){
            return this.value;
        }

        protected void setValue(T value){
            this.value = value;
        }
    }
}
