package org.example.BasicStructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
class GenericLinkedListTest {

    GenericLinkedList<Integer> IntList;
    GenericLinkedList<Double> DoubleList;
    GenericLinkedList<Character> CharList;
    GenericLinkedList<Boolean> BoolList;

    @BeforeEach
    void setup(){
        IntList = new GenericLinkedList<>();
        DoubleList = new GenericLinkedList<>();
        CharList = new GenericLinkedList<>();
        BoolList = new GenericLinkedList<>();
    }

    @DisplayName("Insert one at Head In Empty")
    @Test
    void InsetAtHeadUsingInsert() {
        IntList.insertAt(1);
        DoubleList.insertAt(1.0);
        CharList.insertAt('a');
        BoolList.insertAt(true);

        assertEquals(1, IntList.getSize());
        assertEquals(1, DoubleList.getSize());
        assertEquals(1, CharList.getSize());
        assertEquals(1, BoolList.getSize());
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
    void InsetAtHeadUsingInsertAt(){
        int IntSize = IntList.getSize();
        int DoubleSize = DoubleList.getSize();
        int CharSize = CharList.getSize();
        int BoolSize = BoolList.getSize();
        for (int i = 0; i < 10; i++){
            IntList.insertAt(i);
            DoubleList.insertAt((double)i);
            CharList.insertAt((char)i);
            BoolList.insertAt(i >5);
        }

        assertTrue(IntSize < IntList.getSize());
        assertTrue(DoubleSize < DoubleList.getSize());
        assertTrue(CharSize < CharList.getSize());
        assertTrue(BoolSize < BoolList.getSize());
        assertEquals(9, IntList.tail.getValue());
        assertEquals(9.0, DoubleList.tail.getValue());
        assertEquals((char)9, CharList.tail.getValue());
        assertEquals(true, BoolList.tail.getValue());
    }

    @DisplayName("tail.next is null ???")
    @Test
    void isTailNextNull(){
        IntList.insertAt(1);
        DoubleList.insertAt(1.0);
        CharList.insertAt('a');
        BoolList.insertAt(true);

        assertNull(IntList.tail.next);
        assertNull(DoubleList.tail.next);
        assertNull(CharList.tail.next);
        assertNull(BoolList.tail.next);
    }

    @DisplayName("Insert In beg in not empty")
    @Test

    void insertInBeginning(){
        IntList.insertAt(0);
        DoubleList.insertAt(0.0);
        CharList.insertAt('a');
        BoolList.insertAt(true);

        IntList.insertAt(1, 0);
        DoubleList.insertAt(1.0, 0);
        CharList.insertAt('b', 0);
        BoolList.insertAt(false, 0);

        assertEquals(IntList.getValueAt(0), IntList.head.getValue());
        assertEquals(DoubleList.getValueAt(0), DoubleList.head.getValue());
        assertEquals(CharList.getValueAt(0), CharList.head.getValue());
        assertEquals(BoolList.getValueAt(0), BoolList.head.getValue());
    }
    @DisplayName("Insert in end is tail")
    @Test
    void insertInEnd(){
        IntList.insertAt(0);
        DoubleList.insertAt(0.0);
        CharList.insertAt('a');
        BoolList.insertAt(true);

        IntList.insertAt(1, IntList.getSize()-1);
        DoubleList.insertAt(1.0, DoubleList.getSize()-1);
        CharList.insertAt('b', CharList.getSize()-1);
        BoolList.insertAt(false, BoolList.getSize()-1);

        assertEquals(IntList.tail.getValue(), IntList.getValueAt(IntList.getSize()-1));
        assertEquals(DoubleList.tail.getValue(), DoubleList.getValueAt(DoubleList.getSize()-1));
        assertEquals(CharList.tail.getValue(), CharList.getValueAt(CharList.getSize()-1));
        assertEquals(BoolList.tail.getValue(), BoolList.getValueAt(BoolList.getSize()-1));
    }
    @DisplayName("Insert in middle")
    @Test
    void insertInMiddle(){
        for(int i = 1; i <= 5; i++){
            if(i==3) continue;;
            IntList.insertAt(i);
            DoubleList.insertAt((double) i);
            CharList.insertAt((char) i);
            BoolList.insertAt(true);
        }

        IntList.insertAt(3,2);
        DoubleList.insertAt((double)3,2);
        CharList.insertAt((char)3, 2);
        BoolList.insertAt(false, 2);

        assertTrue(IntList.getSize()==5 && IntList.getValueAt(2)==3);
        assertTrue(DoubleList.getSize()==5 && DoubleList.getValueAt(2)==(double)3);
        assertTrue(CharList.getSize()==5 && CharList.getValueAt(2)==(char)3);
        assertTrue(BoolList.getSize()==5 && BoolList.getValueAt(2) ==false);
    }

    @DisplayName("Wrong index insertions")
    @Test
    void insertInWrongPosition(){
        assertThrows(IllegalArgumentException.class, () -> {IntList.insertAt(1, -1);});
        assertThrows(IllegalArgumentException.class, () -> {IntList.insertAt(1, 5);});
        assertThrows(IllegalArgumentException.class, () -> {DoubleList.insertAt((double)1, -1);});
        assertThrows(IllegalArgumentException.class, () -> {DoubleList.insertAt((double)1,5);});
        assertThrows(IllegalArgumentException.class, () -> {CharList.insertAt((char)1, -1);});
        assertThrows(IllegalArgumentException.class, () -> {CharList.insertAt((char)1, 5);});
        assertThrows(IllegalArgumentException.class, () -> {BoolList.insertAt(true, -1);});
        assertThrows(IllegalArgumentException.class, () -> {BoolList.insertAt(true, -1);});
    }

    @DisplayName("Wrong index remove")
    @Test
    void removeAt() {
        assertThrows(NoSuchElementException.class, ()->{IntList.removeAt(-1);});
        assertThrows(NoSuchElementException.class, ()->{DoubleList.removeAt(-1);});
        assertThrows(NoSuchElementException.class, ()->{CharList.removeAt(-1);});
        assertThrows(NoSuchElementException.class, ()->{BoolList.removeAt(-1);});
    }

    @DisplayName("Empty List remove")
    @Test
    void deleteInEmptyList(){
        assertThrows(NoSuchElementException.class, ()->{IntList.removeAt(0);});
        assertThrows(NoSuchElementException.class, ()->{DoubleList.removeAt(0);});
        assertThrows(NoSuchElementException.class, ()->{CharList.removeAt(0);});
        assertThrows(NoSuchElementException.class, ()->{BoolList.removeAt(0);});
    }
    @Disabled
    @Test
    void getValueAt() {
    }

    @Disabled
    @Test
    void show() {
    }

    @Disabled
    @Test
    void isEmpty() {
    }

    @Disabled
    @Test
    void update() {
    }

    @Disabled
    @Test
    void getSize() {
    }

    @Disabled
    @Test
    void testInsertAt() {
    }
}