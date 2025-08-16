package org.example.ComplexStructures;

import org.example.AbstracClasses.AbstractHeap;

/**
 * A max-heap implementation that extends {@link AbstractHeap}.
 * Maintains the max-heap property where each parent node is greater than or equal to its children.
 * Provides efficient O(1) access to the maximum element and O(log n) insertion/extraction.
 *
 * @param <T> the type of elements in the heap, must implement {@link Comparable}
 *
 * @see AbstractHeap
 * @see Comparable
 */

public class MaxHeap <T extends Comparable<T>> extends AbstractHeap<T> {

    /**
     * Restores the heap property by moving the element at the specified index upward.
     * Continues swapping with parent until the parent is greater or root is reached.
     *
     * @param index the index of the element to sift up
     *
     * @implNote Time complexity: O(log n)
     */

    @Override
    protected void siftUp(int index) {
        while (index != 0){
            int parentIndex = getParentIndex(index);
            if (compare(index,parentIndex) <= 0) break;// Пришли в правильную позицию
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    /**
     * Restores the heap property by moving the element at the specified index downward.
     * Continues swapping with the larger child until both children are smaller or leaf is reached.
     *
     * @param index the index of the element to sift down
     *
     * @implNote Time complexity: O(log n)
     */

    @Override
    protected void siftDown(int index) {
        int tempIndex = index;
        int leftChildIndex = getLeftChildIndex(index);
        int rightChildIndex = getRightChildIndex(index);

        if (get(tempIndex) != null && get(leftChildIndex) != null){
            if (leftChildIndex < heap.length && compare(leftChildIndex, tempIndex) > 0) tempIndex = leftChildIndex;
        }
        if (get(tempIndex) != null && get(rightChildIndex) != null) {
            if (rightChildIndex < heap.length && compare(rightChildIndex, tempIndex) > 0) tempIndex = rightChildIndex;
        }

        if (tempIndex != index){
            swap(index, tempIndex);
            siftDown(tempIndex);
        }
    }

    /**
     * Removes and returns the maximum element from the heap (the root).
     *
     * @return the maximum element in the heap
     * @throws IllegalStateException if the heap is empty
     *
     * @implNote Time complexity: O(log n) due to siftDown after removal
     */

    public T extractMax(){
        T value = get(0);
        remove(value);
        return value;
    }

    /**
     * Returns the maximum element from the heap without removing it.
     *
     * @return the maximum element in the heap (root element)
     * @throws IllegalStateException if the heap is empty
     *
     * @implNote Time complexity: O(1)
     */

    public T getMax(){
        return get(0);
    }

}
