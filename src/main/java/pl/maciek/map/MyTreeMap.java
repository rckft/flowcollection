package pl.maciek.map;

import pl.maciek.collection.MyCollection;
import pl.maciek.collection.list.MyArrayList;
import pl.maciek.collection.list.MyList;
import pl.maciek.stream.MyStream;

import static pl.maciek.map.MyTreeMap.Node.Color.*;

// treemap na zasadzie kluczy układa KLUCZE poprzez porównanie

// 1. Every node is red or black
// 2. Root is always black
// 3. New insertions are always red
// 4. Every path from root-leaf has the same number of BLACK nodes
// 5. No path can have two consecutive RED nodes
// ExternalNodes/nulls are BLACK

public class MyTreeMap<K extends Comparable<K> ,V> implements MyMap<K, V> {

//    private MyList<ExternalNode> externalNodes = new MyArrayList<>();

    class ExternalNode extends Node<K,V> {

//        private int id =

        public ExternalNode() {
            setColor(BLACK);
//            externalNodes.add(this);
        }

    }

    class Node<K, V> implements MyMap.Entry<K, V> {

        enum Color {
            BLACK, RED
        }

        private K key;
        private V value;
        private Color color;
        private Node<K, V> left;
        private Node<K, V> right;

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return this.color;
        }
    }

    private Node<K, V> root;
    private int size = 0;

    @Override
    public int size() {
        return 0;
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
    public boolean containsValue(V value) {
        return false;
    }

    @Override
    public V get(K key) {
        return null;
    }

    private void traverse() {

    }

    @Override
    public V put(K key, V value) {
        var newNode = new Node<K, V>();
        newNode.setColor(RED);
        if (root == null) {
            newNode.left = new ExternalNode();
            newNode.right = new ExternalNode();
            root = newNode;
            root.setColor(BLACK);
            return newNode.getValue();
        }

        if (root.key.compareTo(key) > 0) {
            var node = root.left;
        }



        return newNode.getValue();
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

    public boolean isRootBlack() {
        return root.color == BLACK;
    }

//    public boolean everyExternalNodeIsBlack() {
//        return new MyStream<>(externalNodes).allMatch(it -> it.getColor() == BLACK);
//    }


}
