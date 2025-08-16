package org.example.ComplexStructures;

import org.example.Interfaces.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashSetTest {
    Set<Integer> set;
    @BeforeEach
    void setUp() {
        set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);
        set.add(5);
    }
    @Test
    @DisplayName("Add test with duplicates")
    void testAdd() {
        assertEquals(5, set.size());
    }

    @Test
    void remove() {
        set.remove(1);
        assertFalse(set.contains(1));
    }

    @Test
    void clear() {
        set.clear();
        assertTrue(set.isEmpty());
    }

    @Test
    void testShow() {
        set.show();
    }

    @Test
    void contains() {
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
        assertTrue(set.contains(3));
        assertTrue(set.contains(4));
        assertTrue(set.contains(5));
    }

    @Test
    void testIsEmpty() {
        set.remove(1);
        set.remove(2);
        set.remove(3);
        set.remove(4);
        set.remove(5);
        assertTrue(set.isEmpty());


    }

    @Test
    void testSize() {
        assertEquals(5, set.size());
    }
}