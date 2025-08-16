package org.example.ComplexStructures;

import org.example.Interfaces.Set;

/**
 * A Set implementation using HashMap as the backing storage.
 * Provides basic set operations: add, remove, contains and clear.
 * Uses a HashMap internally where values are stored as keys with a dummy object as values.
 *
 * @param <V> the type of elements maintained by this set, must implement Comparable
 */

public class HashSet <V extends Comparable<V>> implements Set<V> {
    private HashMap<V,Object> map = new HashMap<>();
    private static final Object DUMMY = new Object();

    /**
     * Adds the specified element to this set if it is not already present.
     * @param value element to be added to this set
     */

    @Override
    public void add(V value) {
        map.add(value,DUMMY);
    }

    /**
     * Removes the specified element from this set if it is present.
     * @param value element to be removed from this set
     */

    @Override
    public void remove(V value) {
        map.remove(value);
    }

    /**
     * Removes all of the elements from this set. The set will be empty after this call returns.
     */


    @Override
    public void clear() {
        map.clear();
    }

    /**
     * Displays the contents of this set to standard output.
     * The output shows all elements currently in the set.
     */

    @Override
    public void show() {
        System.out.println(map.getKeys());
    }

    /**
     * Returns true if this set contains the specified element.
     * @param value element whose presence in this set is to be tested
     * @return true if this set contains the specified element
     */

    @Override
    public boolean contains(V value) {
        return map.containsKey(value);
    }

    /**
     * Returns true if this set contains no elements.
     * @return true if this set contains no elements
     */

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    /**
     * Returns the number of elements in this set (its cardinality).
     * @return the number of elements in this set
     */

    @Override
    public int size() {
        return map.size();
    }
}
