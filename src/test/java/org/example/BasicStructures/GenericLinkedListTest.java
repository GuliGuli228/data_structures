package org.example.BasicStructures;

import org.example.Exceptions.EmptyLinkedListException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class GenericLinkedListTest {
    // TODO: Промотреть весь код, ТЕСТЫ НАПИСАНЫ НЕЙРОНКОЙ!!!
    GenericLinkedList<Integer> IntList;
    GenericLinkedList<Double> DoubleList;
    GenericLinkedList<Character> CharList;
    GenericLinkedList<Boolean> BoolList;

    private static <T> GenericLinkedList<T>.LinkedNode getNode(GenericLinkedList<T> list, int place) {
        if (list.isEmpty()) throw new NoSuchElementException();
        if (place < 0 || place >= list.size()) throw new IllegalArgumentException();
        GenericLinkedList<T>.LinkedNode current = list.head;
        for (int i = 0; i < place; i++) {
            current = current.next;
        }
        return current;
    }

    @BeforeEach
    void setup() {
        IntList = new GenericLinkedList<>();
        DoubleList = new GenericLinkedList<>();
        CharList = new GenericLinkedList<>();
        BoolList = new GenericLinkedList<>();
    }

    @DisplayName("Insert one at Head In Empty")
    @Test
    void insertAtHeadUsingInsert() {
        IntList.add(1);
        DoubleList.add(1.0);
        CharList.add('a');
        BoolList.add(true);

        assertEquals(1, IntList.size());
        assertEquals(1, DoubleList.size());
        assertEquals(1, CharList.size());
        assertEquals(1, BoolList.size());
        assertEquals(1, IntList.head.getValue());
        assertEquals(1.0, DoubleList.head.getValue());
        assertEquals('a', CharList.head.getValue());
        assertEquals(true, BoolList.head.getValue());
        assertEquals(1, IntList.tail.getValue());
        assertEquals(1.0, DoubleList.tail.getValue());
        assertEquals('a', CharList.tail.getValue());
        assertEquals(true, BoolList.tail.getValue());
    }

    @DisplayName("Insert multiple in tail")
    @Test
    void insertAtTailUsingInsert() {
        int IntSize = IntList.size();
        int DoubleSize = DoubleList.size();
        int CharSize = CharList.size();
        int BoolSize = BoolList.size();
        for (int i = 0; i < 10; i++) {
            IntList.add(i);
            DoubleList.add((double) i);
            CharList.add((char) i);
            BoolList.add(i > 5);
        }

        assertTrue(IntSize < IntList.size());
        assertTrue(DoubleSize < DoubleList.size());
        assertTrue(CharSize < CharList.size());
        assertTrue(BoolSize < BoolList.size());
        assertEquals(9, IntList.tail.getValue());
        assertEquals(9.0, DoubleList.tail.getValue());
        assertEquals((char) 9, CharList.tail.getValue());
        assertEquals(true, BoolList.tail.getValue());
        assertEquals(10, IntList.size());
    }

    @DisplayName("tail.next is null")
    @Test
    void isTailNextNull() {
        IntList.add(1);
        DoubleList.add(1.0);
        CharList.add('a');
        BoolList.add(true);

        assertNull(IntList.tail.next);
        assertNull(DoubleList.tail.next);
        assertNull(CharList.tail.next);
        assertNull(BoolList.tail.next);
    }

    @DisplayName("Insert at beginning in non-empty list")
    @Test
    void insertInBeginning() {
        IntList.add(0);
        DoubleList.add(0.0);
        CharList.add('a');
        BoolList.add(true);

        IntList.add(1, 0);
        DoubleList.add(1.0, 0);
        CharList.add('b', 0);
        BoolList.add(false, 0);

        assertEquals(1, IntList.getValueAt(0));
        assertEquals(1.0, DoubleList.getValueAt(0));
        assertEquals('b', CharList.getValueAt(0));
        assertEquals(false, BoolList.getValueAt(0));
    }

    @DisplayName("Insert at end is tail")
    @Test
    void insertInEnd() {
        IntList.add(0);
        DoubleList.add(0.0);
        CharList.add('a');
        BoolList.add(true);

        IntList.add(1, IntList.size());
        DoubleList.add(1.0, DoubleList.size());
        CharList.add('b', CharList.size());
        BoolList.add(false, BoolList.size());

        assertEquals(1, IntList.tail.getValue());
        assertEquals(1.0, DoubleList.tail.getValue());
        assertEquals('b', CharList.tail.getValue());
        assertEquals(false, BoolList.tail.getValue());
    }

    @DisplayName("Insert in middle")
    @Test
    void insertInMiddle() {
        for (int i = 1; i <= 5; i++) {
            if (i == 3) continue;
            IntList.add(i);
            DoubleList.add((double) i);
            CharList.add((char) i);
            BoolList.add(true);
        }

        IntList.add(3, 2);
        DoubleList.add(3.0, 2);
        CharList.add((char) 3, 2);
        BoolList.add(false, 2);

        assertEquals(5, IntList.size());
        assertEquals(3, IntList.getValueAt(2));
        assertEquals(5, DoubleList.size());
        assertEquals(3.0, DoubleList.getValueAt(2));
        assertEquals(5, CharList.size());
        assertEquals((char) 3, CharList.getValueAt(2));
        assertEquals(5, BoolList.size());
        assertEquals(false, BoolList.getValueAt(2));
    }

    @DisplayName("Wrong index insertions")
    @Test
    void insertInWrongPosition() {
        assertThrows(IndexOutOfBoundsException.class, () -> IntList.add(1, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> IntList.add(1, 5));
        assertThrows(IndexOutOfBoundsException.class, () -> DoubleList.add(1.0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> DoubleList.add(1.0, 5));
        assertThrows(IndexOutOfBoundsException.class, () -> CharList.add('a', -1));
        assertThrows(IndexOutOfBoundsException.class, () -> CharList.add('a', 5));
        assertThrows(IndexOutOfBoundsException.class, () -> BoolList.add(true, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> BoolList.add(true, 5));
    }

    @DisplayName("Wrong index remove")
    @Test
    void removeAtWrongIndex() {

        IntList.add(0);
        DoubleList.add(0.0);
        CharList.add('a');
        BoolList.add(true);

        assertThrows(IndexOutOfBoundsException.class, () -> IntList.removeAt(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> DoubleList.removeAt(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> CharList.removeAt(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> BoolList.removeAt(-1));
    }

    @DisplayName("Empty list remove")
    @Test
    void deleteInEmptyList() {
        assertThrows(EmptyLinkedListException.class, () -> IntList.removeAt(0));
        assertThrows(EmptyLinkedListException.class, () -> DoubleList.removeAt(0));
        assertThrows(EmptyLinkedListException.class, () -> CharList.removeAt(0));
        assertThrows(EmptyLinkedListException.class, () -> BoolList.removeAt(0));
    }

    @DisplayName("Head remove")
    @Test
    void headRemove() {
        IntList.add(1);
        IntList.add(2);
        DoubleList.add(1.0);
        DoubleList.add(2.0);
        CharList.add('a');
        CharList.add('b');
        BoolList.add(true);
        BoolList.add(false);

        IntList.removeAt(0);
        DoubleList.removeAt(0);
        CharList.removeAt(0);
        BoolList.removeAt(0);

        assertEquals(2, IntList.getValueAt(0));
        assertEquals(2.0, DoubleList.getValueAt(0));
        assertEquals('b', CharList.getValueAt(0));
        assertEquals(false, BoolList.getValueAt(0));
    }

    @DisplayName("Tail remove")
    @Test
    void removeTail() {
        IntList.add(1);
        IntList.add(2);
        IntList.add(3);
        DoubleList.add(1.0);
        DoubleList.add(2.0);
        DoubleList.add(3.0);
        CharList.add('a');
        CharList.add('b');
        CharList.add('c');
        BoolList.add(true);
        BoolList.add(false);
        BoolList.add(true);

        IntList.removeAt(IntList.size() - 1);
        DoubleList.removeAt(DoubleList.size() - 1);
        CharList.removeAt(CharList.size() - 1);
        BoolList.removeAt(BoolList.size() - 1);

        assertEquals(2, IntList.tail.getValue());
        assertEquals(2.0, DoubleList.tail.getValue());
        assertEquals('b', CharList.tail.getValue());
        assertEquals(false, BoolList.tail.getValue());
    }

    @DisplayName("Middle remove")
    @Test
    void removeAtMiddle() {
        IntList.add(1);
        IntList.add(2);
        IntList.add(3);
        DoubleList.add(1.0);
        DoubleList.add(2.0);
        DoubleList.add(3.0);
        CharList.add('a');
        CharList.add('b');
        CharList.add('c');
        BoolList.add(true);
        BoolList.add(false);
        BoolList.add(true);

        IntList.removeAt(1);
        DoubleList.removeAt(1);
        CharList.removeAt(1);
        BoolList.removeAt(1);

        assertEquals(3, IntList.getValueAt(1));
        assertEquals(3.0, DoubleList.getValueAt(1));
        assertEquals('c', CharList.getValueAt(1));
        assertEquals(true, BoolList.getValueAt(1));
    }

    @DisplayName("Remove single element")
    @Test
    void removeSingleElement() {
        IntList.add(1);
        DoubleList.add(1.0);
        CharList.add('a');
        BoolList.add(true);

        IntList.removeAt(0);
        DoubleList.removeAt(0);
        CharList.removeAt(0);
        BoolList.removeAt(0);

        assertTrue(IntList.isEmpty());
        assertTrue(DoubleList.isEmpty());
        assertTrue(CharList.isEmpty());
        assertTrue(BoolList.isEmpty());
        assertNull(IntList.head);
        assertNull(IntList.tail);
        assertNull(DoubleList.head);
        assertNull(DoubleList.tail);
        assertNull(CharList.head);
        assertNull(CharList.tail);
        assertNull(BoolList.head);
        assertNull(BoolList.tail);
    }

    @DisplayName("Empty list getValue")
    @Test
    void getValueAtEmpty() {
        assertThrows(EmptyLinkedListException.class, () -> IntList.getValueAt(0));
        assertThrows(EmptyLinkedListException.class, () -> DoubleList.getValueAt(0));
        assertThrows(EmptyLinkedListException.class, () -> CharList.getValueAt(0));
        assertThrows(EmptyLinkedListException.class, () -> BoolList.getValueAt(0));
    }

    @DisplayName("First element getValue")
    @Test
    void getValueAtFirst() {
        IntList.add(1);
        DoubleList.add(1.0);
        CharList.add('a');
        BoolList.add(true);

        assertEquals(1, IntList.getValueAt(0));
        assertEquals(1.0, DoubleList.getValueAt(0));
        assertEquals('a', CharList.getValueAt(0));
        assertEquals(true, BoolList.getValueAt(0));
    }

    @DisplayName("Last element getValue")
    @Test
    void getValueAtLast() {
        IntList.add(1);
        IntList.add(2);
        IntList.add(3);
        DoubleList.add(1.0);
        DoubleList.add(2.0);
        DoubleList.add(3.0);
        CharList.add('a');
        CharList.add('b');
        CharList.add('c');
        BoolList.add(true);
        BoolList.add(false);
        BoolList.add(true);

        assertEquals(3, IntList.getValueAt(IntList.size() - 1));
        assertEquals(3.0, DoubleList.getValueAt(DoubleList.size() - 1));
        assertEquals('c', CharList.getValueAt(CharList.size() - 1));
        assertEquals(true, BoolList.getValueAt(BoolList.size() - 1));
    }

    @DisplayName("Middle getValue")
    @Test
    void getValueAtMiddle() {
        IntList.add(1);
        IntList.add(2);
        IntList.add(3);
        DoubleList.add(1.0);
        DoubleList.add(2.0);
        DoubleList.add(3.0);
        CharList.add('a');
        CharList.add('b');
        CharList.add('c');
        BoolList.add(true);
        BoolList.add(false);
        BoolList.add(true);

        assertEquals(2, IntList.getValueAt(1));
        assertEquals(2.0, DoubleList.getValueAt(1));
        assertEquals('b', CharList.getValueAt(1));
        assertEquals(false, BoolList.getValueAt(1));
    }

    @DisplayName("Wrong index getValue")
    @Test
    void getValueAtWrongIndex() {
        IntList.add(1);
        IntList.add(2);
        IntList.add(3);
        DoubleList.add(1.0);
        DoubleList.add(2.0);
        DoubleList.add(3.0);
        CharList.add('a');
        CharList.add('b');
        CharList.add('c');
        BoolList.add(true);
        BoolList.add(false);
        BoolList.add(true);

        assertThrows(IndexOutOfBoundsException.class, () -> IntList.getValueAt(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> IntList.getValueAt(3));
        assertThrows(IndexOutOfBoundsException.class, () -> DoubleList.getValueAt(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> DoubleList.getValueAt(3));
        assertThrows(IndexOutOfBoundsException.class, () -> CharList.getValueAt(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> CharList.getValueAt(3));
        assertThrows(IndexOutOfBoundsException.class, () -> BoolList.getValueAt(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> BoolList.getValueAt(3));
    }

    @DisplayName("Update in empty list")
    @Test
    void updateInEmptyList() {
        assertThrows(EmptyLinkedListException.class, () -> IntList.update(1, 0));
        assertThrows(EmptyLinkedListException.class, () -> DoubleList.update(1.0, 0));
        assertThrows(EmptyLinkedListException.class, () -> CharList.update('a', 0));
        assertThrows(EmptyLinkedListException.class, () -> BoolList.update(true, 0));
    }

    @DisplayName("First element update")
    @Test
    void updateFirstElement() {
        IntList.add(1);
        DoubleList.add(1.0);
        CharList.add('a');
        BoolList.add(true);

        IntList.update(2, 0);
        DoubleList.update(2.0, 0);
        CharList.update('b', 0);
        BoolList.update(false, 0);

        assertEquals(2, IntList.getValueAt(0));
        assertEquals(2.0, DoubleList.getValueAt(0));
        assertEquals('b', CharList.getValueAt(0));
        assertEquals(false, BoolList.getValueAt(0));
    }

    @DisplayName("Last element update")
    @Test
    void updateLastElement() {
        IntList.add(1);
        IntList.add(2);
        DoubleList.add(1.0);
        DoubleList.add(2.0);
        CharList.add('a');
        CharList.add('b');
        BoolList.add(true);
        BoolList.add(true);

        IntList.update(3, IntList.size() - 1);
        DoubleList.update(3.0, DoubleList.size() - 1);
        CharList.update('c', CharList.size() - 1);
        BoolList.update(false, BoolList.size() - 1);

        assertEquals(3, IntList.getValueAt(IntList.size() - 1));
        assertEquals(3.0, DoubleList.getValueAt(DoubleList.size() - 1));
        assertEquals('c', CharList.getValueAt(CharList.size() - 1));
        assertEquals(false, BoolList.getValueAt(BoolList.size() - 1));
    }

    @DisplayName("Middle update")
    @Test
    void updateInMiddle() {
        IntList.add(1);
        IntList.add(2);
        IntList.add(3);
        DoubleList.add(1.0);
        DoubleList.add(2.0);
        DoubleList.add(3.0);
        CharList.add('a');
        CharList.add('b');
        CharList.add('c');
        BoolList.add(true);
        BoolList.add(true);
        BoolList.add(true);

        IntList.update(4, 1);
        DoubleList.update(4.0, 1);
        CharList.update('d', 1);
        BoolList.update(false, 1);

        assertEquals(4, IntList.getValueAt(1));
        assertEquals(4.0, DoubleList.getValueAt(1));
        assertEquals('d', CharList.getValueAt(1));
        assertEquals(false, BoolList.getValueAt(1));
    }

    @DisplayName("Wrong index update")
    @Test
    void updateAtWrongIndex() {
        IntList.add(1);
        IntList.add(2);
        IntList.add(3);
        DoubleList.add(1.0);
        DoubleList.add(2.0);
        DoubleList.add(3.0);
        CharList.add('a');
        CharList.add('b');
        CharList.add('c');
        BoolList.add(true);
        BoolList.add(true);
        BoolList.add(true);

        assertThrows(IndexOutOfBoundsException.class, () -> IntList.update(5, 3));
        assertThrows(IndexOutOfBoundsException.class, () -> DoubleList.update(5.0, 3));
        assertThrows(IndexOutOfBoundsException.class, () -> CharList.update('e', 3));
        assertThrows(IndexOutOfBoundsException.class, () -> BoolList.update(false, 3));
    }

    @DisplayName("Empty list getSize")
    @Test
    void getSizeAtEmptyList() {
        assertEquals(0, IntList.size());
        assertEquals(0, DoubleList.size());
        assertEquals(0, CharList.size());
        assertEquals(0, BoolList.size());
    }

    @DisplayName("Get size after insertion")
    @Test
    void getSizeAfterInsertion() {
        int IntSize = IntList.size();
        int DoubleSize = DoubleList.size();
        int CharSize = CharList.size();
        int BoolSize = BoolList.size();

        IntList.add(1);
        DoubleList.add(1.0);
        CharList.add('a');
        BoolList.add(true);

        assertTrue(IntSize < IntList.size());
        assertTrue(DoubleSize < DoubleList.size());
        assertTrue(CharSize < CharList.size());
        assertTrue(BoolSize < BoolList.size());
    }

    @DisplayName("Get size after delete")
    @Test
    void getSizeAfterDelete() {
        IntList.add(1);
        DoubleList.add(1.0);
        CharList.add('a');
        BoolList.add(true);

        int IntSize = IntList.size();
        int DoubleSize = DoubleList.size();
        int CharSize = CharList.size();
        int BoolSize = BoolList.size();

        IntList.removeAt(0);
        DoubleList.removeAt(0);
        CharList.removeAt(0);
        BoolList.removeAt(0);

        assertTrue(IntSize > IntList.size());
        assertTrue(DoubleSize > DoubleList.size());
        assertTrue(CharSize > CharList.size());
        assertTrue(BoolSize > BoolList.size());
    }

    @DisplayName("Show empty list")
    @Test
    void showEmptyList() {
        assertThrows(EmptyLinkedListException.class, () -> IntList.show());
        assertThrows(EmptyLinkedListException.class, () -> DoubleList.show());
        assertThrows(EmptyLinkedListException.class, () -> CharList.show());
        assertThrows(EmptyLinkedListException.class, () -> BoolList.show());
    }

    @DisplayName("Show single element list")
    @Test
    void showSingleElement() {
        IntList.add(1);
        DoubleList.add(1.0);
        CharList.add('a');
        BoolList.add(true);

        assertEquals("[ 1 ]", IntList.toString());
        assertEquals("[ 1.0 ]", DoubleList.toString());
        assertEquals("[ a ]", CharList.toString());
        assertEquals("[ true ]", BoolList.toString());
    }

    @DisplayName("Show multiple elements")
    @Test
    void showMultipleElements() {
        IntList.add(1);
        IntList.add(2);
        IntList.add(3);
        DoubleList.add(1.0);
        DoubleList.add(2.0);
        DoubleList.add(3.0);
        CharList.add('a');
        CharList.add('b');
        CharList.add('c');
        BoolList.add(true);
        BoolList.add(false);
        BoolList.add(true);

        assertEquals("[ 1 , 2 , 3 ]", IntList.toString());
        assertEquals("[ 1.0 , 2.0 , 3.0 ]", DoubleList.toString());
        assertEquals("[ a , b , c ]", CharList.toString());
        assertEquals("[ true , false , true ]", BoolList.toString());
    }

    @DisplayName("IsEmpty after operations")
    @Test
    void isEmptyAfterOperations() {
        assertTrue(IntList.isEmpty());
        IntList.add(1);
        assertFalse(IntList.isEmpty());
        IntList.removeAt(0);
        assertTrue(IntList.isEmpty());
    }

    @DisplayName("Node connections after operations")
    @Test
    void nodeConnections() {
        IntList.add(1);
        IntList.add(2);
        IntList.add(3, 1);

        assertEquals(1, IntList.head.getValue());
        assertEquals(3, IntList.head.next.getValue());
        assertEquals(2, IntList.head.next.next.getValue());
        assertEquals(2, IntList.tail.getValue());
        assertNull(IntList.tail.next);
    }
    @DisplayName("To array test")
    @Test
    void toArray() {
        IntList.add(1);
        IntList.add(2);
        IntList.add(3);

        Object[] expected = { 1, 2, 3 };
        assertArrayEquals(expected, IntList.toArray());
    }
}