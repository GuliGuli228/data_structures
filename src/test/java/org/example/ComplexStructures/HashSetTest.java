package org.example.ComplexStructures;

import org.example.BasicStructures.DoublyLinkedList;
import org.example.Interfaces.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashSetTest extends DoublyLinkedList {
    Set<Integer> set;
    @BeforeEach
    void setUp() {
        set = new HashSet<>();
    }
    @Test
    @DisplayName("Add test with duplicates")
    void testAdd() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);
        set.add(5);
        set.show();
    }

    @Test
    void remove() {
    }

    @Test
    void clear() {
    }

    @Test
    void testShow() {
    }

    @Test
    void contains() {
    }

    @Test
    void testIsEmpty() {
    }

    @Test
    void testSize() {
    }
}