package pl.maciek.map;

import pl.maciek.collection.MyCollection;
import pl.maciek.collection.list.MyArrayList;
import pl.maciek.collection.list.MyList;

import java.util.function.Function;

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

    private final int DEFAULT_CAPACITY = 10;
    private final double LOAD_FACTOR_THRESHOLD = 0.75;
    private final int REHASH_FACTOR = 2;

    private Node<K, V>[] buckets = new Node[DEFAULT_CAPACITY];
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (size() == 0) return false;
        return getEntryWithKey(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if (size() == 0) return false;
        for (var head: buckets) {
            for (var node = head; node != null; node = node.next) {
                if (node.value == value) return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        if (size() == 0) return null;
        var entry = getEntryWithKey(key);
        return entry != null ? entry.getValue() : null;
    }


    @Override
    public V put(K key, V value) {
        if (loadFactor() > LOAD_FACTOR_THRESHOLD) {
            try {
                rehash();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        int bucketIndex = bucket(key);
        var newNode = new Node<K, V>();
        newNode.key = key;
        newNode.value = value;
        var head = buckets[bucketIndex];

        if (head == null) {
            buckets[bucketIndex] = newNode;
            size++;
            return newNode.value;
        }

        for (var node = head; ;node = node.next) {
            if (node.key == key) {
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }

            if (node.next == null) {
                node.next = newNode;
                size++;
                return newNode.value;
            }
        }
    }

    @Override
    public V remove(K key) {
        if (size == 0) return null;
        var index = bucket(key);
        var head = buckets[index];
        if (head == null) return null;

        if (head.key == key) {
            buckets[index] = head.next;
            size--;
            return head.value;
        }

        for (var node = head; node.next != null; node = node.next) {
            var nextNode = node.next;
            if (nextNode.key == key) {
                V value = nextNode.value;
                node.next = nextNode.next;
                size--;
                return value;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        buckets = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public MyList<K> keySet() {
        return mapToList(Node::getKey);
    }

    @Override
    public MyCollection<V> values() {
        return mapToList(Node::getValue);
    }

    @Override
    public MyList<Entry<K, V>> entrySet() {
        return mapToList(entry -> entry);
    }

    private int bucket(K key) {
        int hash = key == null ? 0 : Math.abs(key.hashCode());
        return hash % buckets.length;
    }

    private Entry<K, V> getEntryWithKey(K key) {
        for (var node = buckets[bucket(key)]; node != null; node = node.next) {
            if (node.key == key) return node;
        }
        return null;
    }

    private <R> MyList<R> mapToList(Function<Node<K, V>, R> mappingFunction) {
        MyList<R> list = new MyArrayList<>();
        for (var head: buckets) {
            for (var node = head; node != null; node = node.next) {
                list.add(mappingFunction.apply(node));
            }
        }
        return list;
    }

    private void rehash() {
        var entrySet = entrySet();
        buckets = new Node[size * REHASH_FACTOR];
        size = 0;
        for (int i = 0; i < entrySet.size(); i++) {
            var entry = entrySet.get(i);
            put(entry.getKey(), entry.getValue());
        }
    }

    private double loadFactor() {
        return (double) size / buckets.length;
    }
}
