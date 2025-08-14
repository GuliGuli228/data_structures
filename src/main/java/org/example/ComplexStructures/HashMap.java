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
