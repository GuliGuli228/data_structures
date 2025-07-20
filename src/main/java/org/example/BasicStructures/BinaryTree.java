package org.example.BasicStructures;

import org.example.AbstracClasses.AbstractTree;
import org.example.Interfaces.Tree;

import java.util.Comparator;

public class BinaryTree<T extends Comparable<T>> extends AbstractTree<T> implements Tree<T> {
    protected class BinaryNode extends Node{
        @Override
        protected T getValue(){
            return super.getValue();
        }
        @Override
        protected void setValue(T value){
            super.setValue(value);
        }

    }
    private class BinaryTreeComparator implements Comparator<BinaryNode>{
        @Override
        public int compare(BinaryNode node1, BinaryNode node2) {
            return node1.getValue().compareTo(node2.getValue());
        }
    }

    @Override
    public void add(T value) {

    }

    @Override
    public void deleteAt(int place) {

    }

    @Override
    public void show() {

    }

    @Override
    public void update() {

    }
}
