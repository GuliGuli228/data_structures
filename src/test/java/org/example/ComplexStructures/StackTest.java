package org.example.ComplexStructures;

import org.apache.commons.lang.NullArgumentException;
import org.example.Exceptions.EmptyStackException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Stack Tests")
class StackTest {

    private Stack<Integer> integerStack;
    private Stack<String> stringStack;

    @BeforeEach
    void setUp() {
        integerStack = new Stack<>();
        stringStack = new Stack<>();
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {
        @Test
        @DisplayName("New stack should be empty")
        void testNewStackIsEmpty() {
            assertTrue(integerStack.isEmpty(), "New stack should be empty");
        }
    }

    @Nested
    @DisplayName("Push Method Tests")
    class PushTests {
        @Test
        @DisplayName("Push single integer element")
        void testPushSingleInteger() {
            integerStack.push(5);
            assertFalse(integerStack.isEmpty(), "Stack should not be empty after push");
            assertEquals(5, integerStack.peek(), "Peek should return pushed element");
        }

        @Test
        @DisplayName("Push single string element")
        void testPushSingleString() {
            stringStack.push("A");
            assertFalse(stringStack.isEmpty(), "Stack should not be empty after push");
            assertEquals("A", stringStack.peek(), "Peek should return pushed element");
        }

        @Test
        @DisplayName("Push multiple elements")
        void testPushMultipleElements() {
            integerStack.push(1);
            integerStack.push(2);
            integerStack.push(3);
            assertEquals(3, integerStack.peek(), "Peek should return last pushed element");
        }
    }

    @Nested
    @DisplayName("Pop Method Tests")
    class PopTests {
        @Test
        @DisplayName("Pop from stack with one element")
        void testPopSingleElement() {
            integerStack.push(10);
            assertEquals(10, integerStack.pop(), "Pop should return pushed element");
            assertTrue(integerStack.isEmpty(), "Stack should be empty after pop");
        }

        @Test
        @DisplayName("Pop from stack with multiple elements")
        void testPopMultipleElements() {
            integerStack.push(1);
            integerStack.push(2);
            integerStack.push(3);
            assertEquals(3, integerStack.pop(), "Pop should return last pushed element");
            assertEquals(2, integerStack.peek(), "Peek should return next element");
        }

        @Test
        @DisplayName("Pop from empty stack should throw exception")
        void testPopEmptyStack() {
            assertThrows(EmptyStackException.class, () -> integerStack.pop(),
                    "Pop on empty stack should throw IndexOutOfBoundsException");
        }
    }

    @Nested
    @DisplayName("Peek Method Tests")
    class PeekTests {
        @Test
        @DisplayName("Peek on stack with one element")
        void testPeekSingleElement() {
            integerStack.push(100);
            assertEquals(100, integerStack.peek(), "Peek should return top element");
            assertFalse(integerStack.isEmpty(), "Stack should remain unchanged after peek");
        }

        @Test
        @DisplayName("Peek does not remove element")
        void testPeekDoesNotRemove() {
            integerStack.push(50);
            assertEquals(50, integerStack.peek(), "First peek should return top element");
            assertEquals(50, integerStack.peek(), "Second peek should return same element");
            assertFalse(integerStack.isEmpty(), "Stack should remain unchanged");
        }

        @Test
        @DisplayName("Peek on empty stack should throw exception")
        void testPeekEmptyStack() {
            assertThrows(EmptyStackException.class, () -> integerStack.peek(),
                    "Peek on empty stack should throw IndexOutOfBoundsException");
        }
    }

    @Nested
    @DisplayName("IsEmpty Method Tests")
    class IsEmptyTests {
        @Test
        @DisplayName("Empty stack returns true")
        void testIsEmptyTrue() {
            assertTrue(integerStack.isEmpty(), "New stack should be empty");
        }

        @Test
        @DisplayName("Non-empty stack returns false")
        void testIsEmptyFalse() {
            integerStack.push(1);
            assertFalse(integerStack.isEmpty(), "Stack with elements should not be empty");
        }

        @Test
        @DisplayName("Stack becomes empty after pop")
        void testIsEmptyAfterPop() {
            integerStack.push(1);
            integerStack.pop();
            assertTrue(integerStack.isEmpty(), "Stack should be empty after popping all elements");
        }
    }

    @Nested
    @DisplayName("Complex Operation Tests")
    class ComplexTests {
        @Test
        @DisplayName("Sequence of push, pop, peek operations")
        void testOperationSequence() {
            integerStack.push(1);
            integerStack.push(2);
            assertEquals(2, integerStack.pop(), "Pop should return 2");
            integerStack.push(3);
            assertEquals(3, integerStack.peek(), "Peek should return 3");
            assertEquals(3, integerStack.pop(), "Pop should return 3");
            assertEquals(1, integerStack.pop(), "Pop should return 1");
            assertTrue(integerStack.isEmpty(), "Stack should be empty");
        }

        @Test
        @DisplayName("Large number of elements")
        void testLargeStack() {
            for (int i = 0; i < 1000; i++) {
                integerStack.push(i);
            }
            assertEquals(999, integerStack.pop(), "Pop should return last element");
            assertEquals(998, integerStack.peek(), "Peek should return next element");
        }
    }

    @Nested
    @DisplayName("Negative Scenario Tests")
    class NegativeTests {
        @Test
        @DisplayName("Push null element")
        void testPushNull() {
            assertThrows(NullArgumentException.class, () -> stringStack.push(null),
                    "Pushing null should throw NullPointerException if GenericLinkedList does not support null");
        }
    }
}