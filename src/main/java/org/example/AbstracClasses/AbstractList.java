package org.example.AbstracClasses;

public abstract class AbstractList<T> {
    protected class Node{
        public T value;
        protected Node (T value){
            this.value = value;
        }
        protected Node(){};
    }
}
