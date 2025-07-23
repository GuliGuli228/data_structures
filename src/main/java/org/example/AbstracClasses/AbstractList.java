package org.example.AbstracClasses;

public abstract class AbstractList{
    protected class Node<T>{
        protected T value;
        protected Node (T value){
            this.value = value;
        }
        protected Node(){};

        protected T getValue(){
            return this.value;
        };
        protected void setValue(T value){
            this.value = value;
        }
    }
}
