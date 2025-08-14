package org.example.AbstracClasses;

/**
 * An abstract base class for tree data structures.
 * Provides common node structure and basic tree operations for derived classes.
 *
 * @param <T> the type of elements stored in the tree
 * @param <N> the type of nodes used in the tree, must extend {@link Node}
 *
 * @see org.example.BasicStructures.BinarySearchTree
 * @see org.example.ComplexStructures.AVLTree
 * @see org.example.ComplexStructures.RedBlackTree
 */
public abstract class AbstractTree <T ,N extends AbstractTree.Node> {

    /**
     * A protected abstract inner class representing a node in the tree.
     * Contains basic tree node structure with value, left/right children, and parent references.
     *
     * <p>This serves as the base node type for all concrete tree implementations.
     * Subclasses should extend this node with additional functionality as needed.
     */

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
