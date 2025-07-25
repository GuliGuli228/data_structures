package org.example.AbstracClasses;

public abstract class AbstractList{
    protected class Node<T>{
        protected T value;
        protected Node (T value){
            this.value = value;
        }
        protected Node(){};
        /*---Getters---*/
        protected T getValue(){
            return this.value;
        }
        /*------------*/

        /*---Setters---*/
        protected void setValue(T value){
            this.value = value;
        }
        /*------------*/
    }
}
