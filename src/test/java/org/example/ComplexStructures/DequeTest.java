package org.example.ComplexStructures;

import org.example.BasicStructures.GenericLinkedList;
import org.example.Exceptions.EmptyQueueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DequeTest {
    private Deque<Integer> deque;

    @BeforeEach
    void setUp() {
        deque = new Deque<>();
    }

    @Test
    void testNewDequeIsEmpty() {
        assertEquals(0, deque.size());
        assertThrows(EmptyQueueException.class, () -> deque.peekFirst());
        assertThrows(EmptyQueueException.class, () -> deque.peekLast());
    }

    @Test
    void testAddFirstIncreasesSize() {
        deque.addFirst(10);
        assertEquals(1, deque.size());
        deque.addFirst(20);
        assertEquals(2, deque.size());
    }

    @Test
    void testAddLastIncreasesSize() {
        deque.addLast(10);
        assertEquals(1, deque.size());
        deque.addLast(20);
        assertEquals(2, deque.size());
    }

    @Test
    void testAddFirstAndPeekFirst() {
        deque.addFirst(10);
        assertEquals(10, deque.peekFirst());
        deque.addFirst(20);
        assertEquals(20, deque.peekFirst());
    }

    @Test
    void testAddLastAndPeekLast() {
        deque.addLast(10);
        assertEquals(10, deque.peekLast());
        deque.addLast(20);
        assertEquals(20, deque.peekLast());
    }

    @Test
    void testRemoveFirstDecreasesSize() {
        deque.addFirst(10);
        deque.addFirst(20);
        deque.removeFirst();
        assertEquals(1, deque.size());
        deque.removeFirst();
        assertEquals(0, deque.size());
    }

    @Test
    void testRemoveLastDecreasesSize() {
        deque.addLast(10);
        deque.addLast(20);
        deque.removeLast();
        assertEquals(1, deque.size());
        deque.removeLast();
        assertEquals(0, deque.size());
    }

    @Test
    void testRemoveFirstReturnsCorrectValue() {
        deque.addFirst(10);
        deque.addFirst(20);
        assertEquals(20, deque.removeFirst());
        assertEquals(10, deque.removeFirst());
    }

    @Test
    void testRemoveLastReturnsCorrectValue() {
        deque.addLast(10);
        deque.addLast(20);
        assertEquals(20, deque.removeLast());
        assertEquals(10, deque.removeLast());
    }

    @Test
    void testRemoveFirstOnEmptyDequeThrowsException() {
        assertThrows(EmptyQueueException.class, () -> deque.removeFirst());
    }

    @Test
    void testRemoveLastOnEmptyDequeThrowsException() {
        assertThrows(EmptyQueueException.class, () -> deque.removeLast());
    }
    @Test
    void PeekLast(){
        deque.addFirst(10);
        deque.addFirst(20);
        assertEquals(10, deque.peekLast());
    }
    @Test
    void PeekFirst(){
        deque.addFirst(10);
        deque.addFirst(20);
        assertEquals(20, deque.peekFirst());
    }

    @Test
    void testPeekFirstOnEmptyDequeThrowsException() {
        assertThrows(EmptyQueueException.class, () -> deque.peekFirst());
    }

    @Test
    void testPeekLastOnEmptyDequeThrowsException() {
        assertThrows(EmptyQueueException.class, () -> deque.peekLast());
    }

    @Test
    void testMultipleOperationsMaintainCorrectOrder() {
        deque.addFirst(10);      // [10]
        deque.addLast(20);       // [10, 20]
        deque.addFirst(5);       // [5, 10, 20]
        deque.addLast(25);      // [5, 10, 20, 25]

        assertEquals(4, deque.size());
        assertEquals(5, deque.peekFirst());
        assertEquals(25, deque.peekLast());

        assertEquals(5, deque.removeFirst());  // [10, 20, 25]
        assertEquals(25, deque.removeLast());  // [10, 20]

        assertEquals(2, deque.size());
        assertEquals(10, deque.peekFirst());
        assertEquals(20, deque.peekLast());
    }

    @Test
    void testSizeAfterMixedOperations() {
        assertEquals(0, deque.size());

        deque.addFirst(1);
        assertEquals(1, deque.size());

        deque.addLast(2);
        assertEquals(2, deque.size());

        deque.removeFirst();
        assertEquals(1, deque.size());

        deque.removeLast();
        assertEquals(0, deque.size());
    }

    @Test
    void showTest(){
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addFirst(7);
        deque.show();
        System.out.println(Arrays.toString(deque.toArray()));
    }
}