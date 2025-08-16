package org.example.ComplexStructures;

import org.apache.commons.lang.NullArgumentException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

class MaxHeapTest {
    private MaxHeap<Integer> maxHeap;

    @BeforeEach
    void setUp() {
        maxHeap = new MaxHeap<>();
    }

    @Test
    @DisplayName("Empty heap size")
    void emptyHeapSize() {
        assertEquals(0, maxHeap.size());
    }

    @Test
    @DisplayName("Single element insertion")
    void singleElementInsertion() {
        maxHeap.add(10);
        assertEquals(1, maxHeap.size());
        assertEquals(10, maxHeap.getMax());
    }

    @Test
    @DisplayName("Multiple elements maintain max")
    void multipleElementsMaintainMax() {
        maxHeap.add(10);
        maxHeap.add(20);
        maxHeap.add(30);
        assertEquals(30, maxHeap.getMax());
    }

    @Test
    @DisplayName("Extract max maintains heap")
    void extractMaxMaintainsHeap() {
        maxHeap.add(10);
        maxHeap.add(30);
        maxHeap.add(20);
        assertEquals(30, maxHeap.extractMax());
        assertEquals(20, maxHeap.getMax());
    }

    @Test
    @DisplayName("Insert descending order")
    void insertDescendingOrder() {
        maxHeap.add(30);
        maxHeap.add(20);
        maxHeap.add(10);
        assertEquals(30, maxHeap.toArray()[0]);
        assertEquals(20, maxHeap.toArray()[1]);
        assertEquals(10, maxHeap.toArray()[2]);
    }

    @Test
    @DisplayName("Insert ascending order")
    void insertAscendingOrder() {
        maxHeap.add(10);
        maxHeap.add(20);
        maxHeap.add(30);
        assertEquals(30, maxHeap.toArray()[0]);
        assertEquals(10, maxHeap.toArray()[1]);
        assertEquals(20, maxHeap.toArray()[2]);
    }

    @Test
    @DisplayName("Duplicate values handling")
    void duplicateValuesHandling() {
        maxHeap.add(10);
        maxHeap.add(10);
        maxHeap.add(20);
        assertEquals(20, maxHeap.extractMax());
        assertEquals(10, maxHeap.extractMax());
        assertEquals(10, maxHeap.extractMax());
    }

    @Test
    @DisplayName("Remove non-existent element")
    void removeNonExistentElement() {
        maxHeap.add(10);
        maxHeap.add(20);
        assertThrows(NoSuchElementException.class, () -> maxHeap.remove(30));
        assertEquals(2, maxHeap.size());
    }

    @Test
    @DisplayName("Remove middle element")
    void removeMiddleElement() {
        maxHeap.add(10);
        maxHeap.add(30);
        maxHeap.add(20);
        assertEquals(3, maxHeap.size());
        assertEquals(30, maxHeap.getMax());
    }

    @Test
    @DisplayName("Extract from empty heap")
    void extractFromEmptyHeap() {
        assertThrows(NoSuchElementException.class, () -> maxHeap.extractMax());
    }

    @Test
    @DisplayName("Heap property after multiple operations")
    void heapPropertyAfterOperations() {
        maxHeap.add(50);
        maxHeap.add(30);
        maxHeap.add(70);
        maxHeap.add(10);
        maxHeap.add(20);

        assertEquals(70, maxHeap.extractMax());
        assertEquals(50, maxHeap.extractMax());
        assertEquals(30, maxHeap.extractMax());
        assertEquals(20, maxHeap.extractMax());
        assertEquals(10, maxHeap.extractMax());
    }

    @Test
    @DisplayName("Clear heap")
    void clearHeap() {
        maxHeap.add(10);
        maxHeap.add(20);
        maxHeap.clear();
        assertEquals(0, maxHeap.size());
        assertTrue(maxHeap.isEmpty());
    }

    @Test
    @DisplayName("Contains check")
    void containsCheck() {
        maxHeap.add(10);
        maxHeap.add(20);
        assertTrue(maxHeap.contains(10));
        assertFalse(maxHeap.contains(30));
    }

    @Test
    @DisplayName("Large value insertion")
    void largeValueInsertion() {
        maxHeap.add(Integer.MAX_VALUE);
        maxHeap.add(Integer.MIN_VALUE);
        assertEquals(Integer.MAX_VALUE, maxHeap.getMax());
    }

    @Test
    @DisplayName("Null value handling")
    void nullValueHandling() {
        assertThrows(NullArgumentException.class, () -> maxHeap.add(null));
    }

    @Test
    @DisplayName("ToArray correctness")
    void toArrayCorrectness() {
        maxHeap.add(10);
        maxHeap.add(30);
        maxHeap.add(20);
        Object[] expected = {30, 10, 20};
        assertArrayEquals(expected, maxHeap.toArray());
    }
    @Test
    @DisplayName("Array extension test")
    void arrayExtensionTest() {
        for (int i = 0; i < 20; i++) {
            maxHeap.add(i);
        }
    }
    @Test
    @DisplayName("Array decrease size test")
    void arrayDecreaseSizeTest() {
        for (int i = 0; i < 20; i++) {
            maxHeap.add(i);
        }
        for (int i = 0; i <16 ; i++) {
            maxHeap.extractMax();
        }
    }
}