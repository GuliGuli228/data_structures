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
        @Override
        public boolean equals(Object o) {
            return key.equals(((Node)o).key);
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

    private Object [] hashMapBuckets = new Object[8] ;
    private int amountOfElements = 0;



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
    @SuppressWarnings("unchecked")
    public void add(K key, V value){
        double loadFactor = (double) amountOfElements / hashMapBuckets.length;
        Node nodeToAdd = new Node(key, value);
        Node dummy = new Node(key, null);


        if (hashMapBuckets[getBucketIndex(nodeToAdd)] == null){
            GenericLinkedList<Node> list = new GenericLinkedList<>();
            hashMapBuckets[getBucketIndex(nodeToAdd)] = list;
            list.add(nodeToAdd);
            amountOfElements++;
            return;

        }
        if (hashMapBuckets[getBucketIndex(nodeToAdd)] instanceof GenericLinkedList){
            GenericLinkedList<Node> currentBucket = (GenericLinkedList<Node>) hashMapBuckets[getBucketIndex(nodeToAdd)];
            if (this.containsKey(key)){
                currentBucket.update(nodeToAdd, currentBucket.indexOf(nodeToAdd));
            }
            else {
                if (currentBucket.size() >= 8){
                    RedBlackTree<Node> newBucket = new RedBlackTree<>();
                    for (int i =0; i < currentBucket.size(); i++){
                        newBucket.add(currentBucket.getValueAt(i));
                    }
                }
                else{
                    ((GenericLinkedList<Node>)hashMapBuckets[getBucketIndex(nodeToAdd)]).add(nodeToAdd);
                    amountOfElements++;
                }
            }
        }
        if (hashMapBuckets[getBucketIndex(nodeToAdd)] instanceof RedBlackTree){
            if (this.containsKey(key)){
                RedBlackTree<Node> tempTree = (RedBlackTree<Node>)hashMapBuckets[getBucketIndex(nodeToAdd)];
                tempTree.update(tempTree.BFS(dummy).getValue(),new Node(key,value));
                return;
            }
            else {
                ((RedBlackTree<Node>)hashMapBuckets[getBucketIndex(nodeToAdd)]).add(nodeToAdd);
                amountOfElements++;
            }
        }

        if (loadFactor > 0.75){
            autoResize(hashMapBuckets.length*2);
        }
        if (loadFactor < 0.25 && amountOfElements > 8){
            autoResize(hashMapBuckets.length/2);
        }
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * @param key key whose mapping is to be removed from the map
     */

    @Override
    @SuppressWarnings("unchecked")
    public void remove(K key){
        if (hashMapBuckets[getBucketIndex(key)] == null || !this.containsKey(key)) return;

        Node dummyNode = new Node(key, null);
        if (hashMapBuckets[getBucketIndex(key)] instanceof GenericLinkedList){
            GenericLinkedList<Node> currentBucket = (GenericLinkedList<Node>)hashMapBuckets[getBucketIndex(key)];
            currentBucket.removeAt(currentBucket.indexOf(dummyNode));
        }
        if  (hashMapBuckets[getBucketIndex(key)] instanceof RedBlackTree){
            ((RedBlackTree<Node>)hashMapBuckets[getBucketIndex(key)]).deleteByValue(dummyNode);
        }
        amountOfElements--;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if no mapping exists
     */

    @Override
    @SuppressWarnings("unchecked")
    public V get(K key){
        int number = getBucketIndex(key);
        if (hashMapBuckets[getBucketIndex(key)] == null|| !this.containsKey(key)) return null;

        Node dummyNode = new Node(key, null);
        if (hashMapBuckets[getBucketIndex(key)] instanceof GenericLinkedList){
            GenericLinkedList<Node> currentBucket = (GenericLinkedList<Node>)hashMapBuckets[getBucketIndex(key)];
            return currentBucket.getValueAt(currentBucket.indexOf(dummyNode)).getValue();
        }
        if (hashMapBuckets[getBucketIndex(key)] instanceof RedBlackTree){
            RedBlackTree<Node> toSearch = (RedBlackTree<Node>) hashMapBuckets[getBucketIndex(key)];
            return toSearch.BFS(dummyNode).getValue().getValue();
        }
        return null;
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
    @SuppressWarnings("unchecked")
    public String toString() {
        if (this.isEmpty()) return "Map is empty";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hashMapBuckets.length; i++) {
            if (hashMapBuckets[i] != null) {
                sb.append("Bucket").append(i).append(": ").append("[ ");
                Object[] nodes = null;
                if (hashMapBuckets[i] instanceof GenericLinkedList) {
                    nodes = ((GenericLinkedList<?>)hashMapBuckets[i]).toArray();
                }
                if (hashMapBuckets[i] instanceof RedBlackTree){
                    nodes = ((RedBlackTree<?>)hashMapBuckets[i]).toArray();
                }
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
    @SuppressWarnings("unchecked")
    public boolean containsKey (K key){
        if (amountOfElements == 0) return false;
        if (hashMapBuckets[getBucketIndex(key)] == null) return false;
        Node dummyNode = new Node(key, null);

        if (hashMapBuckets[getBucketIndex(key)] instanceof GenericLinkedList){
            return ((GenericLinkedList<Node>) hashMapBuckets[getBucketIndex(key)]).indexOf(dummyNode) >= 0;
        }
        if (hashMapBuckets[getBucketIndex(key)] instanceof RedBlackTree<?>){
            try {
                if (((RedBlackTree<Node>)hashMapBuckets[getBucketIndex(key)]).BFS(dummyNode) == null) return false;
            } catch (EmptyBinaryTreeException e) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Returns true if this map contains a mapping for the specified value.
     * @param value the key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified value
     */



    @Override
    @SuppressWarnings("unchecked")

    public boolean containsValue (V value){
        if (amountOfElements == 0) return false;
        for(int i = 0; i < hashMapBuckets.length; i++){
            if (hashMapBuckets[i] == null) continue;
            Node[] Bucket = null;
            if (hashMapBuckets[i] instanceof GenericLinkedList){
                 Bucket = (Node[])((GenericLinkedList<?>)hashMapBuckets[i]).toArray();
            }
            if (hashMapBuckets[i] instanceof RedBlackTree<?>){
                Bucket = (Node[])((RedBlackTree<?>)hashMapBuckets[i]).toArray();
            }
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

    @SuppressWarnings("unchecked")
    public Object[] getKeys() {
        GenericLinkedList<K> keys = new GenericLinkedList<>();
        for(int i = 0; i < hashMapBuckets.length; i++){
            if(hashMapBuckets[i] == null) continue;
            Object[]Bucket = null;
            if (hashMapBuckets[i] instanceof GenericLinkedList){
                Bucket = ((GenericLinkedList<?>)hashMapBuckets[i]).toArray();
            }
            if (hashMapBuckets[i] instanceof RedBlackTree){
                Bucket = ((RedBlackTree<?>)hashMapBuckets[i]).toArray();
            }
            for (int j = 0; j < Bucket.length; j++) {
                Node temp= (Node)Bucket[j];
                keys.add(temp.getKey());
            }
        }
        return keys.toArray();
    }


    /**
     * Resizes hashMap array
     * @param newSize new size of array*/

    @SuppressWarnings("unchecked")
    private void autoResize(int newSize) {
        Object[] newHashMapBuckets = new Object[newSize];
        for(int i = 0; i < hashMapBuckets.length; i++){
            if(hashMapBuckets[i] != null){
                Object[] node = null;
                if (hashMapBuckets[i] instanceof GenericLinkedList){
                    node = ((GenericLinkedList<?>)hashMapBuckets[i]).toArray();
                }
                if (hashMapBuckets[i] instanceof RedBlackTree){
                    node = ((RedBlackTree<?>)hashMapBuckets[i]).toArray();
                }
                for (int j = 0; j < node.length; j++) {

                    if(newHashMapBuckets[getBucketIndex((Node) node[j])] == null){
                        newHashMapBuckets[getBucketIndex((Node) node[j])] = new GenericLinkedList<>();
                        ((GenericLinkedList<Node>)newHashMapBuckets[getBucketIndex((Node) node[j])]).add((Node) node[j]);
                    }
                    else{
                        if (newHashMapBuckets[getBucketIndex((Node) node[j])] instanceof GenericLinkedList){
                            ((GenericLinkedList<Node>)newHashMapBuckets[getBucketIndex((Node) node[j])]).add((Node) node[j]);
                        }
                        if (newHashMapBuckets[getBucketIndex((Node) node[j])] instanceof RedBlackTree){
                            ((RedBlackTree<Node>)newHashMapBuckets[getBucketIndex((Node) node[j])]).add((Node) node[j]);
                        }

                    }
                }
            }
        }
        hashMapBuckets = newHashMapBuckets;
    }


}