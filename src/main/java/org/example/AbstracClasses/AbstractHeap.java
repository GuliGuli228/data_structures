package org.example.AbstracClasses;

import org.apache.commons.lang.NullArgumentException;

import java.util.NoSuchElementException;

/**
 * An abstract base class for heap data structures.
 * Provides common heap operations and maintains the heap structure using an array.
 * Subclasses must implement the sift-up and sift-down operations to define
 * the specific heap property (min-heap or max-heap).
 *
 * @param <T> the type of elements in the heap, must implement Comparable
 */

public abstract class AbstractHeap <T extends Comparable<T>> {
    protected Object[] heap = new  Object[16];
    int currentLast = 0; // индекс последнего элемента
    int size = 0; // размер

    /*---Abstract Methods---*/

    /**
     * Restores the heap property by moving the element at the specified index upward.
     * @param index the index of the element to sift up
     */

    protected abstract void siftUp(int index);

    /**
     * Restores the heap property by moving the element at the specified index downward.
     * @param index the index of the element to sift down
     */

    protected abstract void siftDown(int index);
    /*---Public Methods---*/

    /**
     * Adds an element to the heap.
     * @param o the element to add
     * @throws NullArgumentException if the element is null
     */

    public void add(T o){
        if (o == null) throw new NullArgumentException("Value is null");
        if (size > heap.length-1) increaseCapacity();
        if(size == 0 ) heap[0] = o;
        else{
            heap[size] = o;
            siftUp(size);
            currentLast++;
        }
        size++;
    }

    /**
     * Removes the last element from the heap.
     * Automatically decreases capacity if heap becomes too sparse.
     */


    private void removeLast(){
        if (currentLast >= 0){
            heap[currentLast] = null;
            currentLast--;
        }
        if (size < heap.length/4 && currentLast > 4 ){
            decreaseCapacity();
        };
    }

    /**
     * Removes the specified element from the heap.
     * @param o the element to remove
     * @throws NoSuchElementException if the element is not found
     */

    public void remove(T o){
        if (!this.contains(o)) throw new NoSuchElementException("Object not found");
        int objectIndex = getIndex(o);

        if (objectIndex == currentLast) {
            removeLast();
            return;
        }

        heap[objectIndex] = heap[currentLast];
        removeLast();
        siftDown(objectIndex);
        size--;
    }

    /**
     * Checks if the heap contains the specified element.
     * @param o the element to search for
     * @return true if the heap contains the element, false otherwise
     */

    public boolean contains(T o){
        if (o == null || size == 0 )return false;
        boolean found = false;
        for (int i = 0; i < size;i++) {
            if (o.equals(heap[i])){
                found = true;
                break;
            }
        }
        return found;
    }

    /**
     * Checks if the heap is empty.
     * @return true if the heap is empty, false otherwise
     */

    public boolean isEmpty(){
        return currentLast == 0;
    }
    public int size(){
        return size;
    }

    /**
     * Clears all elements from the heap and resets it to initial capacity.
     */

    public void clear(){
        currentLast = 0;
        heap = new  Object[16];
        size = 0;
        currentLast = 0;
    }

    /**
     * Returns an array containing all elements in the heap.
     * @return an array of all heap elements
     */

    public Object[] toArray(){
        Object[] array = new Object[size];
        System.arraycopy(heap, 0, array, 0, size);
        return array;
    }


    /*---Private Methods---*/

    /**
     * Doubles the capacity of the heap array when full.
     */

    private  void increaseCapacity(){
        Object[] newHeap = new Object[heap.length*2];
        for (int i =0 ; i < heap.length; i++){
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

    /**
     * Halves the capacity of the heap array when underutilized.
     */

    private  void decreaseCapacity(){
        Object[] newHeap = new Object[heap.length/2];
        for (int i =0 ; i < newHeap.length; i++){
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

    /*---Protected Methods---*/

    /**
     * Gets the parent of the specified element.
     * @param o the element whose parent to find
     * @return the parent element, or null if none exists
     * @throws NoSuchElementException if the element is not found
     */

    protected T getParent(T o){
        if (!this.contains(o)) throw new NoSuchElementException("Object not found");
        try {
            return (T) heap[getParentIndex(o)];
        }
        catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Gets the left child of the specified element.
     * @param o the element whose left child to find
     * @return the left child element, or null if none exists
     * @throws NoSuchElementException if the element is not found
     */

    protected T getLeftChild(T o){
        if (!this.contains(o)) throw new NoSuchElementException("Object not found");
        try {
            return (T) heap[getLeftChildIndex(o)];
        }
        catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Gets the right child of the specified element.
     * @param o the element whose right child to find
     * @return the right child element, or null if none exists
     * @throws NoSuchElementException if the element is not found
     */

    protected T getRightChild(T o){
        if (!this.contains(o)) throw new NoSuchElementException("Object not found");
        try {
            return (T)heap[getRightChildIndex(o)];
        }
        catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /*---Helper Methods---*/

    /**
     * Gets the index of the specified element.
     * @param o the element to find
     * @return the index of the element
     */

    protected int getIndex(T o){
        int searchObjectIndex =0;
        for (int i =0 ; i < heap.length; i++){
            if (heap[i].equals(o)){
                searchObjectIndex = i;
                break;
            }
        }
        return searchObjectIndex;
    }

    /**
     * Gets the parent index of the specified element.
     * @param o the element whose parent index to find
     * @return the parent index
     */

    protected int getParentIndex(T o){
        return (getIndex(o)-1)/2;
    }

    /**
     * Gets the left child index of the specified element.
     * @param o the element whose left child index to find
     * @return the left child index
     */

    protected int getLeftChildIndex(T o){
        return (getIndex(o)*2)+1;
    }

    /**
     * Gets the right child index of the specified element.
     * @param o the element whose right child index to find
     * @return the right child index
     */

    protected int getRightChildIndex(T o){
        return (getIndex(o)*2)+2;
    }

    /**
     * Compares two elements at the specified indices.
     * @param index1 the index of the first element
     * @param index2 the index of the second element
     * @return a negative integer, zero, or positive integer as the first element
     *         is less than, equal to, or greater than the second
     */

    protected int compare(int index1, int index2) {
        T o1 =  get(index1);
        T o2 = get(index2);
        return o1.compareTo(o2);
    }

    /**
     * Swaps two elements at the specified indices.
     * @param index1 the index of the first element
     * @param index2 the index of the second element
     */

    protected void swap(int index1, int index2) {
        Object temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    /**
     * Gets the element at the specified index.
     * @param index the index of the element to get
     * @return the element at the specified index, or null if index is out of bounds
     */

    protected T get(int index){
        try {
            return (T)heap[index];
        }
        catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
    protected int getParentIndex(int index) { return (index - 1) / 2; }
    protected int getLeftChildIndex(int index) { return 2 * index + 1; }
    protected int getRightChildIndex(int index) { return 2 * index + 2; }
}
