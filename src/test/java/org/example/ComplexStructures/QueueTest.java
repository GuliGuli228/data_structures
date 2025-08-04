package org.example.ComplexStructures;

import org.apache.commons.lang.NullArgumentException;
import org.example.Exceptions.EmptyQueueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Queue Tests")
class QueueTest {

    private Queue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new Queue<>();
    }

    @Nested
    @DisplayName("Tests for Empty Queue")
    class EmptyQueueTests {

        @Test
        @DisplayName("New queue should be empty")
        void testIsEmptyOnNewQueue() {
            assertTrue(queue.isEmpty(), "New queue should be empty");
        }

        @Test
        @DisplayName("New queue should have size 0")
        void testSizeOnNewQueue() {
            assertEquals(0, queue.size(), "New queue should have size 0");
        }

        @Test
        @DisplayName("Peek on empty queue should return null or throw exception")
        void testPeekOnEmptyQueue() {
            assertThrows(EmptyQueueException.class, () -> queue.peek(), "Peek on empty queue should throw IndexOutOfBoundsException");
        }

        @Test
        @DisplayName("Dequeue on empty queue should return null or throw exception")
        void testDequeueOnEmptyQueue() {
            assertThrows(EmptyQueueException.class, () -> queue.dequeue(), "Dequeue on empty queue should throw IndexOutOfBoundsException");
        }
    }

    @Nested
    @DisplayName("Tests for Enqueue and Dequeue Operations")
    class EnqueueDequeueTests {

        @Test
        @DisplayName("Enqueue single element should increase size and update isEmpty")
        void testEnqueueSingleElement() {
            queue.enqueue(42);
            assertEquals(1, queue.size(), "Size should be 1 after enqueuing one element");
            assertFalse(queue.isEmpty(), "Queue should not be empty after enqueuing");
        }

        @Test
        @DisplayName("Enqueue and dequeue should follow FIFO order")
        void testEnqueueDequeueFIFO() {
            queue.enqueue(1);
            queue.enqueue(2);
            queue.enqueue(3);
            assertEquals(3, queue.size(), "Size should be 3 after enqueuing three elements");
            assertEquals(1, queue.dequeue(), "Dequeue should return first element (1)");
            assertEquals(2, queue.dequeue(), "Dequeue should return second element (2)");
            assertEquals(3, queue.dequeue(), "Dequeue should return third element (3)");
            assertEquals(0, queue.size(), "Size should be 0 after dequeuing all elements");
            assertTrue(queue.isEmpty(), "Queue should be empty after dequeuing all elements");
        }

        @Test
        @DisplayName("Peek should return first element without removing it")
        void testPeekDoesNotRemoveElement() {
            queue.enqueue(1);
            queue.enqueue(2);
            assertEquals(1, queue.peek(), "Peek should return first element (1)");
            assertEquals(2, queue.size(), "Size should remain 2 after peek");
            assertEquals(1, queue.peek(), "Peek should still return first element (1)");
        }
    }

    @Nested
    @DisplayName("Tests for Edge Cases")
    class EdgeCaseTests {

        @Test
        @DisplayName("Enqueue and dequeue large number of elements")
        void testLargeNumberOfElements() {
            int numberOfElements = 1000;
            for (int i = 0; i < numberOfElements; i++) {
                queue.enqueue(i);
            }
            assertEquals(numberOfElements, queue.size(), "Size should match number of enqueued elements");
            for (int i = 0; i < numberOfElements; i++) {
                assertEquals(i, queue.dequeue(), "Dequeue should return elements in FIFO order");
            }
            assertTrue(queue.isEmpty(), "Queue should be empty after dequeuing all elements");
        }

        @Test
        @DisplayName("Enqueue null element should throw exception or handle appropriately")
        void testEnqueueNullElement() {
            assertThrows(NullArgumentException.class, () -> queue.enqueue(null), "Enqueuing null should throw NullPointerException");
        }
    }

    @Nested
    @DisplayName("Tests with Custom Objects")
    class CustomObjectTests {

        class Person {
            private final String name;

            Person(String name) {
                this.name = name;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Person person = (Person) o;
                return name.equals(person.name);
            }

            @Override
            public int hashCode() {
                return name.hashCode();
            }
        }

        @Test
        @DisplayName("Enqueue and dequeue custom objects")
        void testCustomObjectQueue() {
            Queue<Person> personQueue = new Queue<>();
            Person person1 = new Person("Alice");
            Person person2 = new Person("Bob");
            personQueue.enqueue(person1);
            personQueue.enqueue(person2);
            assertEquals(2, personQueue.size(), "Size should be 2 after enqueuing two persons");
            assertEquals(person1, personQueue.dequeue(), "Dequeue should return first person (Alice)");
            assertEquals(person2, personQueue.dequeue(), "Dequeue should return second person (Bob)");
            assertTrue(personQueue.isEmpty(), "Queue should be empty after dequeuing");
        }
    }
}