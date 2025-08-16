package org.example.BasicStructures;

import org.apache.commons.lang.NullArgumentException;
import org.example.Exceptions.EmptyDoublyLinkedListException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DoublyLinkedListTest {
    private DoublyLinkedList<String> list;

    @BeforeEach
    void setUp() {
        list = new DoublyLinkedList<>();
    }

    @Nested
    class InsertAtEndTests {
        @Test
        void insertIntoEmptyList() {
            list.add("A");
            assertEquals(1, list.length);
            assertEquals("List: [ A ]", list.show());
            assertEquals("A", list.head.getValue());
            assertEquals("A", list.tail.getValue());
        }

        @Test
        void insertIntoNonEmptyList() {
            list.add("A");
            list.add("B");
            assertEquals(2, list.length);
            assertEquals("List: [ A , B ]", list.show());
            assertEquals("A", list.head.getValue());
            assertEquals("B", list.tail.getValue());
        }

        @Test
        void insertMultipleValues() {
            list.add("A");
            list.add("B");
            list.add("C");
            assertEquals(3, list.length);
            assertEquals("List: [ A , B , C ]", list.show());
            assertEquals("C", list.tail.getValue());
        }

        @Test
        void insertNullValueThrowsException() {
            assertThrows(NullArgumentException.class, () -> list.add(null));
        }
    }

    @Nested
    class InsertAtIndexTests {
        @Test
        void insertAtIndexZeroInEmptyListThrowsException() {
            assertThrows(EmptyDoublyLinkedListException.class, () -> list.add("A", 0));
        }

        @Test
        void insertAtIndexZeroInNonEmptyList() {
            list.add("B");
            list.add("A", 0);
            assertEquals(2, list.length);
            assertEquals("List: [ A , B ]", list.show());
            assertEquals("A", list.head.getValue());
        }

        @Test
        void insertAtLastIndex() {
            list.add("A");
            list.add("B", 1);
            assertEquals(2, list.length);
            assertEquals("List: [ A , B ]", list.show());
            assertEquals("B", list.tail.getValue());
        }

        @Test
        void insertInMiddle() {
            list.add("A");
            list.add("C");
            list.add("B", 1);
            assertEquals(3, list.length);
            assertEquals("List: [ A , B , C ]", list.show());
        }

        @Test
        void insertWithNegativeIndexThrowsException() {
            list.add("A");
            assertThrows(IndexOutOfBoundsException.class, () -> list.add("B", -1));
        }

        @Test
        void insertWithIndexGreaterThanLengthThrowsException() {
            list.add("A");
            assertThrows(IndexOutOfBoundsException.class, () -> list.add("B", 2));
        }

        @Test
        void insertNullValueThrowsException() {
            list.add("A");
            assertThrows(NullArgumentException.class, () -> list.add(null, 1));
        }
    }

    @Nested
    class RemoveAtTests {
        @Test
        void removeFromEmptyListThrowsException() {
            assertThrows(EmptyDoublyLinkedListException.class, () -> list.removeAt(0));
        }
        @Test
        void smt(){
        }

        @Test
        void removeAtIndexZero() {
            list.add("A");
            list.add("B");
            list.removeAt(0);
            assertEquals(1, list.length);
            assertEquals("List: [ B ]", list.show());
            assertEquals("B", list.head.getValue());
        }

        @Test
        void removeAtLastIndex() {
            list.add("A");
            list.add("B");
            list.removeAt(1);
            assertEquals(1, list.length);
            assertEquals("List: [ A ]", list.show());
            assertEquals("A", list.tail.getValue());
        }

        @Test
        void removeInMiddle() {
            list.add("A");
            list.add("B");
            list.add("C");
            list.removeAt(1);
            assertEquals(2, list.length);
            assertEquals("List: [ A , C ]", list.show());
        }

        @Test
        void removeWithNegativeIndexThrowsException() {
            list.add("A");
            assertThrows(IndexOutOfBoundsException.class, () -> list.removeAt(-1));
        }

        @Test
        void removeWithIndexGreaterThanOrEqualToLengthThrowsException() {
            list.add("A");
            assertThrows(IndexOutOfBoundsException.class, () -> list.removeAt(1));
        }
    }

    @Nested
    class ShowTests {
        @Test
        void showEmptyListThrowsException() {
            assertThrows(EmptyDoublyLinkedListException.class, () -> list.show());
        }

        @Test
        void showSingleElementList() {
            list.add("A");
            assertEquals("List: [ A ]", list.show());
        }

        @Test
        void showMultiElementList() {
            list.add("A");
            list.add("B");
            list.add("C");
            assertEquals("List: [ A , B , C ]", list.show());
        }
    }

    @Nested
    class IsEmptyTests {
        @Test
        void isEmptyOnEmptyList() {
            assertTrue(list.isEmpty());
        }

        @Test
        void isEmptyOnNonEmptyList() {
            list.add("A");
            assertFalse(list.isEmpty());
        }
    }

    @Nested
    class UpdateTests {
        @Test
        void updateInEmptyListThrowsException() {
            assertThrows(EmptyDoublyLinkedListException.class, () -> list.update("A", 0));
        }

        @Test
        void updateAtIndexZero() {
            list.add("A");
            list.update("B", 0);
            assertEquals("List: [ B ]", list.show());
            assertEquals("B", list.head.getValue());
        }

        @Test
        void updateAtLastIndex() {
            list.add("A");
            list.add("B");
            list.update("C", 1);
            assertEquals("List: [ A , C ]", list.show());
            assertEquals("C", list.tail.getValue());
        }

        @Test
        void updateInMiddle() {
            list.add("A");
            list.add("B");
            list.add("C");
            list.update("D", 1);
            assertEquals("List: [ A , D , C ]", list.show());
        }

        @Test
        void updateWithNegativeIndexThrowsException() {
            list.add("A");
            assertThrows(IndexOutOfBoundsException.class, () -> list.update("B", -1));
        }

        @Test
        void updateWithIndexGreaterThanOrEqualToLengthThrowsException() {
            list.add("A");
            assertThrows(IndexOutOfBoundsException.class, () -> list.update("B", 1));
        }

        @Test
        void updateWithNullValueThrowsException() {
            list.add("A");
            assertThrows(NullArgumentException.class, () -> list.update(null, 0));
        }
    }

    @Nested
    class StructuralIntegrityTests {
        @Test
        void verifyLinksAfterInsertions() {
            list.add("A");
            list.add("B");
            list.add("C", 1);
            assertEquals("A", list.head.getValue());
            assertEquals("C", list.head.next.getValue());
            assertEquals("B", list.tail.getValue());
            assertNull(list.head.prev);
            assertEquals("A", list.head.next.prev.getValue());
            assertNull(list.tail.next);
        }

        @Test
        void verifyLinksAfterRemovals() {
            list.add("A");
            list.add("B");
            list.add("C");
            list.removeAt(1);
            assertEquals("A", list.head.getValue());
            assertEquals("C", list.head.next.getValue());
            assertEquals("C", list.tail.getValue());
            assertNull(list.head.prev);
            assertEquals("A", list.tail.prev.getValue());
        }

        @Test
        void largeListOperations() {
            for (int i = 0; i < 1000; i++) {
                list.add("X" + i);
            }
            assertEquals(1000, list.length);
            list.removeAt(500);
            assertEquals(999, list.length);
            list.update("Y", 499);
            assertTrue(list.show().contains("Y"));
        }

        @Test
        void alternatingInsertionsAndDeletions() {
            list.add("A");
            list.add("B", 0);
            list.removeAt(1);
            list.add("C", 1);
            list.removeAt(0);
            assertEquals("List: [ C ]", list.show());
            assertEquals(1, list.length);
        }
        @Test
        void toArrayTest(){
            list.add("A");
            list.add("B");
            list.add("C");
            Object[] expected = { "A", "B", "C" };
            assertArrayEquals(expected, list.toArray());
        }
    }
}