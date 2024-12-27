package pl.maciek.map;

import pl.maciek.collection.MyCollection;
import pl.maciek.collection.list.MyList;

public class MyHashMap<K, V> implements MyMap<K, V> {

    class Node<K, V> implements MyMap.Entry<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
    }

    private Node<K, V>[] buckets = new Node[10];
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public boolean containsValue(K value) {
        return false;
    }

    // jak zmienić szkrótem klaw typ parametru w metodzie interfejsu ( ctrl + shift + f6) ?
    
    @Override
    public V get(K key) {
        int bucketIndex = bucket(key);
        Node<K, V> node = buckets[bucketIndex];
        while (node.key != key && node.next != null) {
            node = node.next;
        }
        return node.value;
    }


    @Override
    public V put(K key, V value) {
        int bucketIndex = bucket(key);
        Node<K, V> newNode = new Node<>();
        newNode.key = key;
        newNode.value = value;

        var head = buckets[bucketIndex];
        if (head != null) {
            newNode.next = head;
        }

        buckets[bucketIndex] = newNode;
        size++;
        return newNode.value;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public MyList<K> keySet() {
        return null;
    }

    @Override
    public MyCollection<V> values() {
        return null;
    }

    @Override
    public MyList<Entry<K, V>> entrySet() {
        return null;
    }

    private int bucket(K key) {
        int hash = key == null ? 0 : Math.abs(key.hashCode());
        return hash % buckets.length; // capacity to rozmiar tablicy
    }
}
