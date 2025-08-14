package org.example.ComplexStructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {

    @Test
    void addAndGet_shouldWorkCorrectly() {
        HashMap<String, Integer> map = new HashMap<>();
        map.add("one", 1);
        map.add("two", 2);
        assertEquals(1, map.get("one"));
        assertEquals(2, map.get("two"));
    }

    @Test
    void get_shouldReturnNullForMissingKey() {
        HashMap<String, Integer> map = new HashMap<>();
        assertNull(map.get("missing"));
    }

    @Test
    void remove_shouldDeleteKey() {
        HashMap<String, Integer> map = new HashMap<>();
        map.add("key", 123);
        map.add("key2", 456);
        map.remove("key");
        assertFalse(map.contains("key"));
    }

    @Test
    void contains_shouldReturnFalseForEmptyMap() {
        HashMap<String, Integer> map = new HashMap<>();
        assertFalse(map.contains("test"));
    }

    @Test
    void add_shouldHandleCollisions() {
        HashMap<Integer, String> map = new HashMap<>();
        // Подобрать ключи с одинаковым hashCode (зависит от реализации)
        map.add(1, "first");
        map.add(9, "second"); // Предположим, что 1 и 9 дают одинаковый hashCode
        assertEquals("first", map.get(1));
        assertEquals("second", map.get(9));
    }

    @Test
    void convertToBytes_shouldThrowForUnsupportedKeyType() {
        HashMap<Boolean, String> map = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> map.add(true, "fail"));
    }

    @Test
    void remove_shouldNotThrowOnMissingKey() {
        HashMap<String, Integer> map = new HashMap<>();
        assertDoesNotThrow(() -> map.remove("missing"));
    }
}