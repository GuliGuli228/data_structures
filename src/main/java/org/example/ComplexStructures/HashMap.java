package org.example.ComplexStructures;

import net.jpountz.xxhash.XXHash32;
import net.jpountz.xxhash.XXHashFactory;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class HashMap <K extends Comparable<K>, V>{

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

    //TODO: Реализовать расширение массива, проверки на null и isEmpty, сделать вывод таблицы

    private RedBlackTree<Node> [] hashMapBuckets = new RedBlackTree[8] ;
    private int amountOfElements = 0;
    private double ratio = amountOfElements/ hashMapBuckets.length;

    private int getBucketIndex(Node node){
       return node.hashCode()  & (hashMapBuckets.length-1);
    }
    private int getBucketIndex(K key){
        byte[]bytes = ConvertToBytes(key);
        return factory.hash(bytes, 0, bytes.length, SEED) &  (hashMapBuckets.length-1);
    }

    public void add(K key, V value){
        Node nodeToAdd = new Node(key, value);
        int hashCode = getBucketIndex(nodeToAdd);
        RedBlackTree<Node> TreeToInsert = hashMapBuckets[getBucketIndex(nodeToAdd)];
        if (TreeToInsert == null) {
            hashMapBuckets[getBucketIndex(nodeToAdd)] = new RedBlackTree();
            hashMapBuckets[getBucketIndex(nodeToAdd)].add(nodeToAdd);
            return;
        }
        hashMapBuckets[getBucketIndex(nodeToAdd)].add(nodeToAdd);
    }
    public void remove(K key){
        Node dummyNode = new Node(key, null);
        hashMapBuckets[getBucketIndex(key)].deleteByValue(dummyNode);
    }
    public V get(K key){
        RedBlackTree<Node> toSearch = hashMapBuckets[getBucketIndex(key)];
        Node dummyNode = new Node(key, null);
        return toSearch.BFS(dummyNode).getValue().getValue();
    }

    public void show(){
        for(int i = 0; i < hashMapBuckets.length; i++){
            if(hashMapBuckets[i] != null)  System.out.println("Bucket: " + i + " [ "+ hashMapBuckets[i].show() + " ]");
        }
    }
    public boolean contains (K key){
        Node dummyNode = new Node(key, null);
        if (hashMapBuckets[getBucketIndex(key)] == null) return false;
        if (hashMapBuckets[getBucketIndex(key)].BFS(dummyNode) == null) return false;
        return true;
    }


}