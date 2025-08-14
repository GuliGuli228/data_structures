package org.example.ComplexStructures;

import net.jpountz.xxhash.XXHash32;
import net.jpountz.xxhash.XXHashFactory;
import org.example.Exceptions.EmptyBinaryTreeException;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * A hash map implementation using Red-Black trees for collision resolution.
 * Provides basic map operations: put, get, remove and contains.
 * Uses XXHash algorithm for hash computation and maintains buckets as Red-Black trees.
 * @param <K> the type of keys maintained by this map, must implement Comparable
 * @param <V> the type of mapped values
 * @see RedBlackTree
 */

public class HashMap <K extends Comparable<K>, V>{

    /**
     * A node representing key-value pair in the hash map.
     * Implements Comparable for storage in Red-Black trees.
     */

    private class Node implements Comparable<Node> {
        final private K key;
        final private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
        @Override
        public int compareTo(@NotNull HashMap<K, V>.Node o) {
            return this.key.compareTo(o.key);
        }

        /**
         * Computes hash code for this node using XXHash algorithm.
         * @return the hash code value for this node
         */

        @Override
        public int hashCode() {
            byte[] bytes = ConvertToBytes(key);
            return factory.hash(bytes, 0, bytes.length, SEED);
        }
        @Override
        public String toString() {
            return "K: " + key + "; V: " + value;
        }
        public V getValue(){
            return value;
        }
    }

    /**
     * Converts key to byte array for hashing.
     * Supports Integer, Long, Double, Character and String keys.
     * @param key the key to convert
     * @return byte array representation of the key
     * @throws IllegalArgumentException if key type is not supported
     */

    private byte[] ConvertToBytes(K key){
        if (key instanceof Integer) return ByteBuffer.allocate(4).putInt((Integer) key).array();
        if (key instanceof Long) return ByteBuffer.allocate(8).putLong((Long) key).array();
        if(key instanceof Double) return ByteBuffer.allocate(8).putDouble((Double) key).array();
        if(key instanceof Character) return ByteBuffer.allocate(8).putChar((Character) key).array();
        if(key instanceof String) return ((String)key).getBytes(StandardCharsets.UTF_8);
        throw new IllegalArgumentException("Key must be Integer, Long, Double, Character");
    }

    final int SEED = 0xDEADBEEF;
    XXHash32 factory = XXHashFactory.fastestInstance().hash32();

    private final RedBlackTree<Node> [] hashMapBuckets = new RedBlackTree[8] ;
    //TODO: реализовать расширение массива при loadFactor >= 0.75
    /*
    private int amountOfElements = 0;
    private double loadFactor = amountOfElements/ hashMapBuckets.length;
    */

    /**
     * Computes bucket index for a given node.
     * @param node the node to compute index for
     * @return bucket index in the hash map
     */

    private int getBucketIndex(Node node){
       return node.hashCode()  & (hashMapBuckets.length-1);
    }

    /**
     * Computes bucket index for a given key.
     * @param key the key to compute index for
     * @return bucket index in the hash map
     */

    private int getBucketIndex(K key){
        byte[]bytes = ConvertToBytes(key);
        return factory.hash(bytes, 0, bytes.length, SEED) &  (hashMapBuckets.length-1);
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced.
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     */

    public void add(K key, V value){
        Node nodeToAdd = new Node(key, value);
        RedBlackTree<Node> targetTree = hashMapBuckets[getBucketIndex(nodeToAdd)];
        if (targetTree == null) {
            hashMapBuckets[getBucketIndex(nodeToAdd)] = new RedBlackTree();
            hashMapBuckets[getBucketIndex(nodeToAdd)].add(nodeToAdd);
            return;
        }
        hashMapBuckets[getBucketIndex(nodeToAdd)].add(nodeToAdd);
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * @param key key whose mapping is to be removed from the map
     */

    public void remove(K key){
        Node dummyNode = new Node(key, null);
        if (hashMapBuckets[getBucketIndex(key)] == null ||hashMapBuckets[getBucketIndex(key)].BFS(dummyNode)==null ) return;
        hashMapBuckets[getBucketIndex(key)].deleteByValue(dummyNode);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if no mapping exists
     */

    public V get(K key){
        RedBlackTree<Node> toSearch = hashMapBuckets[getBucketIndex(key)];
        Node dummyNode = new Node(key, null);
        if (toSearch == null || toSearch.BFS(dummyNode)==null ) return null;
        return toSearch.BFS(dummyNode).getValue().getValue();
    }

    /**
     * Prints the contents of the hash map to standard output.
     * Shows each bucket and its contents in order.
     */

    public void show(){
        for(int i = 0; i < hashMapBuckets.length; i++){
            if(hashMapBuckets[i] != null)  System.out.println("Bucket: " + i + " [ "+ hashMapBuckets[i].show() + " ]");
        }
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     * @param key the key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key
     */

    public boolean contains (K key){
        try {
            Node dummyNode = new Node(key, null);
            if (hashMapBuckets[getBucketIndex(key)] == null) return false;
            if (hashMapBuckets[getBucketIndex(key)].BFS(dummyNode) == null) return false;
        } catch (EmptyBinaryTreeException e) {
            return false;
        }
        return true;
    }


}