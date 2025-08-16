package org.example.ComplexStructures;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.lang.NullArgumentException;
import org.junit.jupiter.api.*;

import java.util.NoSuchElementException;

class MinHeapTest {
    private MinHeap<Integer> minHeap;

    @BeforeEach
    void setUp() {
        minHeap = new MinHeap<>();
    }

    @Test
    @DisplayName("Empty heap size")
    void emptyHeapSize() {
        assertEquals(0, minHeap.size());
    }

    @Test
    @DisplayName("Single element insertion")
    void singleElementInsertion() {
        minHeap.add(10);
        assertEquals(1, minHeap.size());
        assertEquals(10, minHeap.getMin());
    }

    @Test
    @DisplayName("Multiple elements maintain max")
    void multipleElementsMaintainMax() {
        minHeap.add(10);
        minHeap.add(20);
        minHeap.add(30);
        assertEquals(10, minHeap.getMin());
    }

    @Test
    @DisplayName("Extract max maintains heap")
    void extractMaxMaintainsHeap() {
        minHeap.add(10);
        minHeap.add(30);
        minHeap.add(20);
        assertEquals(10, minHeap.extractMin());
        assertEquals(20, minHeap.getMin());
    }

    @Test
    @DisplayName("Insert descending order")
    void insertDescendingOrder() {
        minHeap.add(30);
        minHeap.add(20);
        minHeap.add(10);
        assertEquals(10, minHeap.toArray()[0]);
        assertEquals(30, minHeap.toArray()[1]);
        assertEquals(20, minHeap.toArray()[2]);
    }

    @Test
    @DisplayName("Insert ascending order")
    void insertAscendingOrder() {
        minHeap.add(10);
        minHeap.add(20);
        minHeap.add(30);
        assertEquals(10, minHeap.toArray()[0]);
        assertEquals(20, minHeap.toArray()[1]);
        assertEquals(30, minHeap.toArray()[2]);
    }

    @Test
    @DisplayName("Duplicate values handling")
    void duplicateValuesHandling() {
        minHeap.add(10);
        minHeap.add(10);
        minHeap.add(20);
        assertEquals(10, minHeap.extractMin());
        assertEquals(10, minHeap.extractMin());
        assertEquals(20, minHeap.extractMin());
    }

    @Test
    @DisplayName("Remove non-existent element")
    void removeNonExistentElement() {
        minHeap.add(10);
        minHeap.add(20);
        assertThrows(NoSuchElementException.class, () -> minHeap.remove(30));
        assertEquals(2, minHeap.size());
    }

    @Test
    @DisplayName("Remove middle element")
    void removeMiddleElement() {
        minHeap.add(10);
        minHeap.add(30);
        minHeap.add(20);
        assertEquals(3, minHeap.size());
        assertEquals(10, minHeap.getMin());
    }

    @Test
    @DisplayName("Extract from empty heap")
    void extractFromEmptyHeap() {
        assertThrows(NoSuchElementException.class, () -> minHeap.extractMin());
    }

    @Test
    @DisplayName("Heap property after multiple operations")
    void heapPropertyAfterOperations() {
        minHeap.add(50);
        minHeap.add(30);
        minHeap.add(70);
        minHeap.add(10);
        minHeap.add(20);

        assertEquals(10, minHeap.extractMin());
        assertEquals(20, minHeap.extractMin());
        assertEquals(30, minHeap.extractMin());
        assertEquals(50, minHeap.extractMin());
        assertEquals(70, minHeap.extractMin());
    }

    @Test
    @DisplayName("Clear heap")
    void clearHeap() {
        minHeap.add(10);
        minHeap.add(20);
        minHeap.clear();
        assertEquals(0, minHeap.size());
        assertTrue(minHeap.isEmpty());
    }

    @Test
    @DisplayName("Contains check")
    void containsCheck() {
        minHeap.add(10);
        minHeap.add(20);
        assertTrue(minHeap.contains(10));
        assertFalse(minHeap.contains(30));
    }

    @Test
    @DisplayName("Large value insertion")
    void largeValueInsertion() {
        minHeap.add(Integer.MAX_VALUE);
        minHeap.add(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, minHeap.getMin());
    }

    @Test
    @DisplayName("Null value handling")
    void nullValueHandling() {
        assertThrows(NullArgumentException.class, () -> minHeap.add(null));
    }

    @Test
    @DisplayName("ToArray correctness")
    void toArrayCorrectness() {
        minHeap.add(10);
        minHeap.add(30);
        minHeap.add(20);
        Object[] expected = {10, 30, 20};
        assertArrayEquals(expected, minHeap.toArray());
    }
}