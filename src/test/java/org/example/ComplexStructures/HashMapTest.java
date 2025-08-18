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
        assertFalse(map.containsKey("key"));
    }

    @Test
    void contains_shouldReturnFalseForEmptyMap() {
        HashMap<String, Integer> map = new HashMap<>();
        assertFalse(map.containsKey("test"));
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

    @Test
    void showTest(){
        HashMap<String, Integer> map = new HashMap<>();
        map.add("one", 1);
        map.add("two", 2);
        map.add("three", 3);
        map.add("four", 4);
        System.out.println(map);
    }
    @Test
    void addingDuplicates(){
        HashMap<String, Integer> map = new HashMap<>();
        map.add("one", 1);
        map.add("two", 2);
        map.add("three", 3);
        map.add("one", 4);
        System.out.println(map);
        System.out.println(map.size());
    }

    @Test
    void stressTest(){
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 100_000_00; i++) {
            map .add( ((Integer)i).toString(), i);
        }
    }
}