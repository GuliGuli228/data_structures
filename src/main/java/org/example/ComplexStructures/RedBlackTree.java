package org.example.ComplexStructures;

import org.example.AbstracClasses.RedBlackTreeColors;
import org.example.BasicStructures.BinarySearchTree;

public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    protected class RBTNode extends BinaryNode{
        protected RedBlackTreeColors color;
    }
}
