package org.example.BasicStructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
class GenericLinkedListTest {

    GenericLinkedList<Integer> IntList;
    GenericLinkedList<Double> DoubleList;
    GenericLinkedList<Character> CharList;
    GenericLinkedList<Boolean> BoolList;

    private static <T> GenericLinkedList<T>.LinkedNode getNode(GenericLinkedList<T> list,int place){
        if(list.IsEmpty()) throw new NoSuchElementException();
        if(place <0 || place > list.length-1) throw new IllegalArgumentException();

        GenericLinkedList<T>.LinkedNode current = list.head;

        for(int i = 0; i < place; i++){
            current = current.next;
        }
        return current;
    }

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
    @DisplayName("Head remove")
    @Test
    void HeadRemove(){
        IntList.insertAt(1);
        IntList.insertAt(2);

        CharList.insertAt((char)1);
        CharList.insertAt((char)2);

        DoubleList.insertAt((double)1);
        DoubleList.insertAt((double)2);

        BoolList.insertAt(true);
        BoolList.insertAt(false);

        IntList.removeAt(0);
        DoubleList.removeAt(0);
        CharList.removeAt(0);
        BoolList.removeAt(0);

        assertEquals(IntList.head.getValue(), IntList.getValueAt(0));
        assertEquals(DoubleList.head.getValue(), DoubleList.getValueAt(0));
        assertEquals(CharList.head.getValue(), CharList.getValueAt(0));
        assertEquals(BoolList.head.getValue(), BoolList.getValueAt(0));
    }

    @DisplayName("Tail remove")
    @Test
    void removeTail(){
        IntList.insertAt(1);
        IntList.insertAt(2);
        IntList.insertAt(3);

        CharList.insertAt((char)1);
        CharList.insertAt((char)2);
        CharList.insertAt((char)3);

        DoubleList.insertAt((double)1);
        DoubleList.insertAt((double)2);
        DoubleList.insertAt((double)3);

        BoolList.insertAt(true);
        BoolList.insertAt(false);
        BoolList.insertAt(false);


        IntList.removeAt(IntList.getSize()-1);
        DoubleList.removeAt(DoubleList.getSize() -1);
        CharList.removeAt(CharList.getSize()-1);
        BoolList.removeAt(BoolList.getSize()-1);

        assertEquals(IntList.tail.getValue(), IntList.getValueAt(IntList.getSize()-1));
        assertEquals(DoubleList.tail.getValue(), DoubleList.getValueAt(DoubleList.getSize()-1));
        assertEquals(CharList.tail.getValue(), CharList.getValueAt(CharList.getSize()-1));
        assertEquals(BoolList.tail.getValue(), BoolList.getValueAt(BoolList.getSize()-1));
    }

    @DisplayName("Middle remove")
    @Test
    void removeAtMiddle(){
        IntList.insertAt(1);
        IntList.insertAt(2);
        IntList.insertAt(3);

        CharList.insertAt((char)1);
        CharList.insertAt((char)2);
        CharList.insertAt((char)3);

        DoubleList.insertAt((double)1);
        DoubleList.insertAt((double)2);
        DoubleList.insertAt((double)3);

        BoolList.insertAt(true);
        BoolList.insertAt(false);
        BoolList.insertAt(false);

        assertEquals(IntList.head.next, IntList.tail);
        assertEquals(DoubleList.head.next, DoubleList.tail);
        assertEquals(CharList.head.next, CharList.tail);
        assertEquals(BoolList.head.next, BoolList.tail);
    }
    @DisplayName("EmptyList getvalue")
    @Test
    void getValueAtEmpty() {
        assertThrows(NullPointerException.class, ()->{IntList.getValueAt(0);});
        assertThrows(NullPointerException.class, ()->{DoubleList.getValueAt(0);});
        assertThrows(NullPointerException.class, ()->{CharList.getValueAt(0);});
        assertThrows(NullPointerException.class, ()->{BoolList.getValueAt(0);});
    }

    @DisplayName("First element getValue")
    @Test
    void getValueAtFirst(){
        IntList.insertAt(1);
        DoubleList.insertAt((double)1);
        CharList.insertAt((char)1);
        BoolList.insertAt(true);

        assertEquals(1, IntList.getValueAt(0));
        assertEquals((double) 1, DoubleList.getValueAt(0));
        assertEquals((char)1, CharList.getValueAt(0));
        assertEquals(true, BoolList.getValueAt(0));
    }

    @DisplayName("Last element getValue")
    @Test
    void getValueAtLast(){

        IntList.insertAt(1);
        IntList.insertAt(2);
        IntList.insertAt(3);

        CharList.insertAt((char)1);
        CharList.insertAt((char)2);
        CharList.insertAt((char)3);

        DoubleList.insertAt((double)1);
        DoubleList.insertAt((double)2);
        DoubleList.insertAt((double)3);

        BoolList.insertAt(true);
        BoolList.insertAt(false);
        BoolList.insertAt(false);

        assertEquals(IntList.tail.getValue(), IntList.getValueAt(IntList.getSize()-1));
        assertEquals(DoubleList.tail.getValue(), DoubleList.getValueAt(DoubleList.getSize()-1));
        assertEquals(CharList.tail.getValue(), CharList.getValueAt(CharList.getSize()-1));
        assertEquals(BoolList.tail.getValue(), BoolList.getValueAt(BoolList.getSize()-1));
    }

    @DisplayName("getValue at middle")
    @Test
    void getValueAtMiddle(){
        IntList.insertAt(1);
        IntList.insertAt(2);
        IntList.insertAt(3);

        CharList.insertAt((char)1);
        CharList.insertAt((char)2);
        CharList.insertAt((char)3);

        DoubleList.insertAt((double)1);
        DoubleList.insertAt((double)2);
        DoubleList.insertAt((double)3);

        BoolList.insertAt(true);
        BoolList.insertAt(false);
        BoolList.insertAt(false);

        assertEquals(2, IntList.getValueAt(IntList.getSize()/2));
        assertEquals((double) 2, DoubleList.getValueAt(DoubleList.getSize()/2));
        assertEquals((char) 2, CharList.getValueAt(CharList.getSize()/2));
        assertEquals(false, BoolList.getValueAt(BoolList.getSize()/2));
    }

    @DisplayName("Wrong index getValue")
    @Test
    void getValueAtWrongIndex(){
        IntList.insertAt(1);
        IntList.insertAt(2);
        IntList.insertAt(3);

        CharList.insertAt((char)1);
        CharList.insertAt((char)2);
        CharList.insertAt((char)3);

        DoubleList.insertAt((double)1);
        DoubleList.insertAt((double)2);
        DoubleList.insertAt((double)3);

        BoolList.insertAt(true);
        BoolList.insertAt(false);
        BoolList.insertAt(false);

        assertThrows(IllegalArgumentException.class, ()->{IntList.getValueAt(-1);});
        assertThrows(IllegalArgumentException.class, ()->{DoubleList.getValueAt(-1);});
        assertThrows(IllegalArgumentException.class, ()->{CharList.getValueAt(-1);});
        assertThrows(IllegalArgumentException.class, ()->{BoolList.getValueAt(-1);});
    }

    @DisplayName("Update in empty list")
    @Test
    void updateInEmptyList(){
        assertThrows(NoSuchElementException.class, ()->{IntList.update(1,0);});
        assertThrows(NoSuchElementException.class, ()->{DoubleList.update((double)1,0);});
        assertThrows(NoSuchElementException.class, ()->{CharList.update((char)1,0);});
        assertThrows(NoSuchElementException.class, ()->{BoolList.update(true,0);});
    }

    @DisplayName("First element update")
    @Test
    void updateFirstElement(){
        IntList.insertAt(1);
        DoubleList.insertAt((double)1);
        CharList.insertAt((char)1);
        BoolList.insertAt(true);

        IntList.update(2, 0);
        DoubleList.update((double)2, 0);
        CharList.update((char)2, 0);
        BoolList.update(false, 0);

        assertEquals(2, IntList.getValueAt(0));
        assertEquals((double)2, DoubleList.getValueAt(0));
        assertEquals((char)2, CharList.getValueAt(0));
        assertEquals(false, BoolList.getValueAt(0));
    }

    @DisplayName("Last element update")
    @Test
    void updateLastElement(){
        IntList.insertAt(1);
        IntList.insertAt(2);
        DoubleList.insertAt((double)1);
        DoubleList.insertAt((double)2);
        CharList.insertAt((char)1);
        CharList.insertAt((char)2);
        BoolList.insertAt(true);
        BoolList.insertAt(true);

        IntList.update(3, IntList.getSize()-1);
        DoubleList.update((double)3, DoubleList.getSize()-1);
        CharList.update((char)3, CharList.getSize()-1);
        BoolList.update(false, BoolList.getSize()-1);

        assertEquals(3, IntList.getValueAt(IntList.getSize()-1));
        assertEquals((double) 3, DoubleList.getValueAt(DoubleList.getSize()-1));
        assertEquals((char) 3, CharList.getValueAt(CharList.getSize()-1));
        assertEquals(false, BoolList.getValueAt(BoolList.getSize()-1));
    }

    @DisplayName("Middle update")
    @Test
    void updateInMiddle(){
        IntList.insertAt(1);
        IntList.insertAt(2);
        IntList.insertAt(3);

        CharList.insertAt((char)1);
        CharList.insertAt((char)2);
        CharList.insertAt((char)3);

        DoubleList.insertAt((double)1);
        DoubleList.insertAt((double)2);
        DoubleList.insertAt((double)3);

        BoolList.insertAt(true);
        BoolList.insertAt(true);
        BoolList.insertAt(true);

        GenericLinkedList.LinkedNode IntMiddle = GenericLinkedListTest.getNode(IntList,IntList.getSize()/2);
        GenericLinkedList.LinkedNode DoubleMiddle = GenericLinkedListTest.getNode(DoubleList,IntList.getSize()/2);
        GenericLinkedList.LinkedNode CharMiddle = GenericLinkedListTest.getNode(CharList,IntList.getSize()/2);
        GenericLinkedList.LinkedNode BoolMiddle = GenericLinkedListTest.getNode(BoolList,IntList.getSize()/2);

        //TODO Find out why update throws IllegalArgumentException
        IntList.update(4, IntList.getSize()/2);
        DoubleList.update((double)4, DoubleList.getSize()/2);
        CharList.update((char)4, CharList.getSize()/2);
        BoolList.update(false, BoolList.getSize()/2);

        assertTrue(IntList.getValueAt(IntList.getSize()/2) == 4 && IntMiddle == GenericLinkedListTest.getNode(IntList,IntList.getSize()/2) );
        assertTrue(DoubleList.getValueAt(DoubleList.getSize()/2) == (double)4 && DoubleMiddle == GenericLinkedListTest.getNode(DoubleList,DoubleList.getSize()/2) );
        assertTrue(CharList.getValueAt(CharList.getSize()/2) == (char)4 && CharMiddle == GenericLinkedListTest.getNode(CharList,CharList.getSize()/2) );
        assertTrue(BoolList.getValueAt(BoolList.getSize()/2) == false && BoolMiddle == GenericLinkedListTest.getNode(BoolList,BoolList.getSize()/2) );

    }

    @DisplayName("Wrong index update")
    @Test
    void updateAtWrongIndex(){
        IntList.insertAt(1);
        IntList.insertAt(2);
        IntList.insertAt(3);

        CharList.insertAt((char)1);
        CharList.insertAt((char)2);
        CharList.insertAt((char)3);

        DoubleList.insertAt((double)1);
        DoubleList.insertAt((double)2);
        DoubleList.insertAt((double)3);

        BoolList.insertAt(true);
        BoolList.insertAt(true);
        BoolList.insertAt(true);

        assertThrows(IllegalArgumentException.class, ()-> {IntList.update(5,5);});
        assertThrows(IllegalArgumentException.class, ()-> {DoubleList.update((double)5,5);});
        assertThrows(IllegalArgumentException.class, ()-> {CharList.update((char)5,5);});
        assertThrows(IllegalArgumentException.class, ()-> {BoolList.update(false,5);});
    }


    @DisplayName("Empty list getSize")
    @Test
    void getSizeAtEmptyList(){
        assertEquals(0, IntList.getSize());
        assertEquals(0, DoubleList.getSize());
        assertEquals(0, CharList.getSize());
        assertEquals(0, BoolList.getSize());
    }

    @DisplayName("get size after insertion")
    @Test
    void getSizeAfterInsertion(){
        int IntSize = IntList.getSize();
        int DoubleSize = DoubleList.getSize();
        int CharSize = CharList.getSize();
        int BoolSize = BoolList.getSize();


        IntList.insertAt(1);
        DoubleList.insertAt((double)1);
        CharList.insertAt((char)1);
        BoolList.insertAt(true);

        assertTrue(IntSize < IntList.getSize());
        assertTrue(DoubleSize < DoubleList.getSize());
        assertTrue(CharSize < CharList.getSize());
        assertTrue(BoolSize < BoolList.getSize());
    }
    @DisplayName("get size after delete")
    @Test
    void getSizeAfterDelete(){

        IntList.insertAt(1);
        DoubleList.insertAt((double)1);
        CharList.insertAt((char)1);
        BoolList.insertAt(true);

        int IntSize = IntList.getSize();
        int DoubleSize = DoubleList.getSize();
        int CharSize = CharList.getSize();
        int BoolSize = BoolList.getSize();

        IntList.removeAt(0);
        DoubleList.removeAt(0);
        CharList.removeAt(0);
        BoolList.removeAt(0);

        assertTrue(IntSize > IntList.getSize());
        assertTrue(DoubleSize > DoubleList.getSize());
        assertTrue(CharSize > CharList.getSize());
        assertTrue(BoolSize > BoolList.getSize());
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