package org.example.ComplexStructures;

import org.example.AbstracClasses.AbstractHeap;

/**
 * A min-heap implementation that extends {@link AbstractHeap}.
 * Maintains the min-heap property where each parent node is less than or equal to its children.
 * Provides efficient O(1) access to the minimum element and O(log n) insertion/extraction operations.
 *
 * @param <T> the type of elements in the heap, must implement {@link Comparable}
 *
 * @see AbstractHeap
 * @see Comparable
 */

public class MinHeap <T extends Comparable<T>> extends AbstractHeap<T> {

    /**
     * Restores the heap property by moving the element at the specified index upward.
     * Continues swapping with parent until the parent is smaller or root is reached.
     *
     * @param index the index of the element to sift up
     *
     * @implNote Time complexity: O(log n)
     * @implNote Space complexity: O(1) as it operates in-place
     */

    @Override
    protected void siftUp(int index) {
        while (index > 0) {
            int parentIndex = getParentIndex(index);
            if (compare(index, parentIndex) >= 0)  break;
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    /**
     * Restores the heap property by moving the element at the specified index downward.
     * Continues swapping with the smaller child until both children are larger or leaf is reached.
     *
     * @param index the index of the element to sift down
     *
     * @implNote Time complexity: O(log n) in worst case
     * @implNote Space complexity: O(1) as it operates in-place
     */


    @Override
    protected void siftDown(int index) {
        int tempIndex = index;
        int leftChildIndex = getLeftChildIndex(index);
        int rightChildIndex = getRightChildIndex(index);

        if (get(tempIndex) != null && get(leftChildIndex) != null) {
            if (leftChildIndex < heap.length && compare(leftChildIndex, tempIndex) < 0) tempIndex = leftChildIndex;
        }
        if ((get(tempIndex) != null && get(rightChildIndex) != null)){
            if (rightChildIndex < heap.length && compare(rightChildIndex, tempIndex) < 0) tempIndex = rightChildIndex;
        }
        if (tempIndex != index) {
            swap(tempIndex, index);
            siftDown(tempIndex);
        }
    }

    /**
     * Removes and returns the minimum element from the heap (the root).
     *
     * @return the minimum element in the heap
     * @throws java.util.NoSuchElementException if the heap is empty
     *
     * @implNote Time complexity: O(log n) due to siftDown after removal
     */

    public T extractMin(){
        T temp = get(0);
        remove(temp);
        return temp;
    }

    /**
     * Returns the minimum element from the heap without removing it.
     *
     * @return the minimum element in the heap (root element)
     * @throws java.util.NoSuchElementException if the heap is empty
     *
     * @implNote Time complexity: O(1)
     */

    public T getMin(){
        return get(0);
    }

}
