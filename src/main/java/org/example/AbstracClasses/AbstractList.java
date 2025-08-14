package org.example.AbstracClasses;

/**
 * An abstract base class for list implementations.
 * Provides common node structure and basic operations for derived list classes.
 * Subclasses must implement specific list operations (insertion, deletion, etc.)
 * using the protected {@link Node} class as building blocks.
 *
 * @see org.example.BasicStructures.GenericLinkedList
 * @see org.example.BasicStructures.DoublyLinkedList
 */

public abstract class AbstractList{
    /**
     * A protected inner class representing a node in the list.
     * Serves as a base for list nodes in concrete implementations.
     * @param <T> the type of element stored in the node
     */

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
