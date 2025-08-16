package org.example.ComplexStructures;

import net.jpountz.xxhash.XXHash32;
import net.jpountz.xxhash.XXHashFactory;
import org.example.BasicStructures.GenericLinkedList;
import org.example.Exceptions.EmptyBinaryTreeException;
import org.example.Interfaces.Map;
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

public class HashMap <K extends Comparable<K>, V> implements Map<K,V> {

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
        public K getKey(){
            return key;
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

    private RedBlackTree<Node> [] hashMapBuckets = new RedBlackTree[8] ;
    private int amountOfElements = 0;
    //TODO: реализовать расширение массива при loadFactor >= 0.75
    ;
    /*
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

    @Override
    public void add(K key, V value){
        if (this.containsKey(key)) return ;
        Node nodeToAdd = new Node(key, value);
        RedBlackTree<Node> targetTree = hashMapBuckets[getBucketIndex(nodeToAdd)];
        if (targetTree == null) {
            hashMapBuckets[getBucketIndex(nodeToAdd)] = new RedBlackTree();
            hashMapBuckets[getBucketIndex(nodeToAdd)].add(nodeToAdd);
            amountOfElements++;
        }
        else {
            hashMapBuckets[getBucketIndex(nodeToAdd)].add(nodeToAdd);
            amountOfElements++;
        }
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * @param key key whose mapping is to be removed from the map
     */

    @Override
    public void remove(K key){
        Node dummyNode = new Node(key, null);
        if (hashMapBuckets[getBucketIndex(key)] == null ||hashMapBuckets[getBucketIndex(key)].BFS(dummyNode)==null ) return;
        hashMapBuckets[getBucketIndex(key)].deleteByValue(dummyNode);
        amountOfElements--;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if no mapping exists
     */

    @Override
    public V get(K key){
        RedBlackTree<Node> toSearch = hashMapBuckets[getBucketIndex(key)];
        Node dummyNode = new Node(key, null);
        if (toSearch == null || toSearch.BFS(dummyNode)==null ) return null;
        return toSearch.BFS(dummyNode).getValue().getValue();
    }

    /**
     * Removes all of the mappings from this map. The map will be empty after this call returns.
     */

    @Override
    public void clear() {
        hashMapBuckets = new RedBlackTree[8];
        amountOfElements = 0;
    }

    /**
     * Prints the contents of the hash map to standard output.
     * Shows each bucket and its contents in order.
     */

    @Override
    public void show(){
        System.out.println(this);
    }

    /**
     * Returns a string representation of the hash map.
     * The string displays each bucket and its contents in order.
     * @return a string representation of the hash map
     */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hashMapBuckets.length; i++) {
            if (hashMapBuckets[i] != null) {
                sb.append("Bucket").append(i).append(": ").append("[ ");
                Object[] nodes = hashMapBuckets[i].toArray();
                for (int j = 0; j < nodes.length; j++) {
                    Node node = (Node) nodes[j];
                    sb.append("[ K: ").append(node.getKey()).append(", V: ").append(node.getValue()).append(" ]");
                    if (j < nodes.length - 1) {
                        sb.append(" , ");
                    }
                }
                sb.append(" ]\n");
            }
        }
        return sb.toString();
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     * @param key the key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key
     */

    @Override
    public boolean containsKey (K key){
        if (amountOfElements == 0) return false;
        try {
            Node dummyNode = new Node(key, null);
            if (hashMapBuckets[getBucketIndex(key)] == null) return false;
            if (hashMapBuckets[getBucketIndex(key)].BFS(dummyNode) == null) return false;
        } catch (EmptyBinaryTreeException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if this map contains a mapping for the specified value.
     * @param value the key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified value
     */



    @Override
    public boolean containsValue (V value){
        if (amountOfElements == 0) return false;
        for(int i = 0; i < hashMapBuckets.length; i++){
            if (hashMapBuckets[i] == null) continue;
            Node[] Bucket = (Node[])hashMapBuckets[i].toArray();
            for (int j = 0; j < Bucket.length; j++) {
                if (value == null){
                    if (Bucket[j].getValue() == null) return true;
                }
                else if (Bucket[j].getValue().equals(value)) return true;
            }
        }
        return  false;
    }

    /**
     * Returns true if this map contains no key-value mappings.
     * @return true if this map contains no key-value mappings
     */

    @Override
    public boolean isEmpty() {
        return amountOfElements == 0;
    }

    /**
     * Returns the number of key-value mappings in this map.
     * @return the number of key-value mappings in this map
     */

    @Override
    public int size() {
        return amountOfElements;
    }

    /**
     * Returns a list of all keys contained in this map.
     * The order of keys is not specified.
     * @return a GenericLinkedList containing all keys in this map
     */


    public GenericLinkedList<K> getKeys() {
        GenericLinkedList<K> keys = new GenericLinkedList<>();
        for(int i = 0; i < hashMapBuckets.length; i++){
            if(hashMapBuckets[i] == null) continue;
            Object[]Bucket = hashMapBuckets[i].toArray();
            for (int j = 0; j < Bucket.length; j++) {
                Node temp= (Node)Bucket[j];
                keys.add(temp.getKey());
            }
        }
        return keys;
    }

}