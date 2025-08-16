package org.example.ComplexStructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircularListTest{

    CircularList<Integer> circularList;
    @BeforeEach
    void setUp(){
        circularList = new CircularList<>();
    }

    @Test
    @DisplayName("Insert At by value")
    void testAdd() {
        circularList.add(1);
        circularList.add(2);
        circularList.add(3);
        circularList.add(4);

        assertEquals("List: [ 1 , 2 , 3 , 4 ]", circularList.show());
    }

    @Test
    @DisplayName("Insert at index")
    void testAddIndex() {
        circularList.add(1);
        circularList.add(2);
        circularList.add(3);
        circularList.add(4, 1);
        assertEquals("List: [ 1 , 4 , 2 , 3 ]", circularList.show());
    }
    @Test
    @DisplayName("Insert at head")
    void testAddHead() {
        circularList.add(1);
        circularList.add(2);
        circularList.add(3);
        circularList.add(4,0);
        assertEquals("List: [ 4 , 1 , 2 , 3 ]", circularList.show());
    }


    @Test
    @DisplayName("Remove middle")
    void testRemoveAtMiddle() {
        circularList.add(1);
        circularList.add(2);
        circularList.add(3);
        circularList.add(4);
        circularList.removeAt(2);
        assertEquals("List: [ 1 , 2 , 4 ]", circularList.show());
        assertEquals(3, circularList.size());
    }

    @Test
    @DisplayName("Remove head")
    void testRemoveAtHead() {
        circularList.add(1);
        circularList.add(2);
        circularList.add(3);
        circularList.add(4);
        circularList.removeAt(0);
        assertEquals("List: [ 2 , 3 , 4 ]", circularList.show());
        assertEquals(3, circularList.size());
    }

    @Test
    @DisplayName("Remove tail")
    void testRemoveAtTail() {
        circularList.add(1);
        circularList.add(2);
        circularList.add(3);
        circularList.add(4);
        circularList.removeAt(circularList.size()-1);
        assertEquals("List: [ 1 , 2 , 3 ]", circularList.show());
        assertEquals(3, circularList.size());
    }

    @Test
    @DisplayName("To array test")
    void testToArray() {
        circularList.add(1);
        circularList.add(2);
        circularList.add(3);
        Object[] expected = {1,2,3};
        assertArrayEquals(expected, circularList.toArray());
    }
}