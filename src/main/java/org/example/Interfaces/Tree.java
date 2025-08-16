package org.example.Interfaces;

/**
 * Interface representing basic tree operations.
 * Defines the contract for tree data structures regardless of their specific implementation.
 *
 * @param <T> the type of elements stored in the tree
 * @param <N> the type of nodes used in the tree implementation
 *
 * @see org.example.AbstracClasses.AbstractTree
 * @see org.example.BasicStructures.BinarySearchTree
 * @see org.example.ComplexStructures.AVLTree
 * @see org.example.ComplexStructures.RedBlackTree
 */

public interface Tree<T, N> {
    void add (T value);
    void deleteByValue(T value);
    void show();
    void update(T from, T to);
    N BFS(T value);
    N DPS(T value);
    boolean IsEmpty();
}
