package pl.maciek.map;

import pl.maciek.collection.MyCollection;
import pl.maciek.collection.list.MyArrayList;
import pl.maciek.collection.list.MyList;

import java.util.function.Function;

// treemap na zasadzie kluczy układa KLUCZE poprzez porównanie

// 1. Every node is red or black
// 2. Root is always black
// 3. New insertions are always red
// 4. Every path from root-leaf has the same number of BLACK nodes
// 5. No path can have two consecutive RED nodes
// ExternalNodes/nulls are BLACK

public class MyBinarySearchTreeMap<K extends Comparable<K>, V> implements MyMap<K, V> {

    static class Node<K extends Comparable<K>, V> implements Entry<K, V> {

        private K key;
        private V value;
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

        @Override
        public String toString() {
            return "[key: " + key + " value: " + value +"]";
        }

    }

    private Node<K, V> root;
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean traverseAndSearchKey(Node<K, V> subTreeRoot, K key) {
        if (subTreeRoot.key.compareTo(key) == 0) {
            return true;
        }

        if (subTreeRoot.left != null && subTreeRoot.key.compareTo(key) > 0) {
            return traverseAndSearchKey(subTreeRoot.left, key);
        }

        if (subTreeRoot.right != null && subTreeRoot.key.compareTo(key) < 0) {
            return traverseAndSearchKey(subTreeRoot.right, key);
        }

        return false;
    }

    @Override
    public boolean containsKey(K key) {
        return !isEmpty() && traverseAndSearchKey(root, key);
    }

    @Override
    public boolean containsValue(V value) {
        if (isEmpty()) return false;
        return traverseAndSearchValue(root, value);
    }

    @Override
    public V get(K key) {
        Node<K, V> node = traverseToNode(root, key);
        return node != null ? node.value : null;
    }

    private Node<K, V> traverseToNode(Node<K, V> subTreeRoot, K key) {
        if (subTreeRoot.key.compareTo(key) > 0) {
            return (subTreeRoot.left == null) ? null : traverseToNode(subTreeRoot.left, key);
        }
        if (subTreeRoot.key.compareTo(key) < 0) {
            return (subTreeRoot.right == null) ? null : traverseToNode(subTreeRoot.right, key);
        }
        return subTreeRoot;
    }

    private V traverseAndInsert(Node<K, V> subTreeRoot, Node<K, V> nodeToInsert) {
        int compareResult = subTreeRoot.key.compareTo(nodeToInsert.key);

        if (compareResult == 0) {
            V value = subTreeRoot.value;
            subTreeRoot.value = nodeToInsert.value;
            return value;
        }

        if (compareResult > 0) {
            if (subTreeRoot.left == null) {
                subTreeRoot.left = nodeToInsert;
                size++;
            } else {
                return traverseAndInsert(subTreeRoot.left, nodeToInsert);
            }
        }

        if (compareResult < 0) {
            if (subTreeRoot.right == null) {
                subTreeRoot.right = nodeToInsert;
                size++;
            } else {
                return traverseAndInsert(subTreeRoot.right, nodeToInsert);
            }
        }
        return nodeToInsert.value;
    }





    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException();
        }

        var newNode = new Node<K, V>();
        newNode.key = key;
        newNode.value = value;

        if (root == null) {
            root = newNode;
            size++;
            return newNode.getValue();
        }
        return traverseAndInsert(root, newNode);
    }

    private Node<K, V> traverseToAndRemoveLargestNode(Node<K, V> subTreeRoot, Node<K, V> subTreeRootParent) {
        if (subTreeRoot.right != null) {
            return traverseToAndRemoveLargestNode(subTreeRoot.right, subTreeRoot);
        }
        subTreeRootParent.right = subTreeRoot.left;
        size--;
        return subTreeRoot;
    }

    private V traverseAndRemove(Node<K, V> subTreeRoot, K keyToRemove, Node<K, V> parent) {
        var keyCompareResult = subTreeRoot.key.compareTo(keyToRemove);

        if (keyCompareResult > 0) {
            return traverseAndRemove(subTreeRoot.left, keyToRemove, subTreeRoot);
        }

        if (keyCompareResult < 0) {
            return traverseAndRemove(subTreeRoot.right, keyToRemove, subTreeRoot);
        }

        if (subTreeRoot.left == null) {
            if (parent.key.compareTo(subTreeRoot.key) > 0) {
                parent.left = subTreeRoot.right;
            } else {
                parent.right = subTreeRoot.right;
            }
        } else {
            Node<K, V> removedLargestLeftTreeNode = traverseToAndRemoveLargestNode(subTreeRoot.left, subTreeRoot);
            subTreeRoot.key = removedLargestLeftTreeNode.key;
            subTreeRoot.value = removedLargestLeftTreeNode.value;
        }

        return subTreeRoot.value;
    }

    @Override
    public V remove(K key) {
        return isEmpty() ? null : traverseAndRemove(root, key, null);
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public MyList<K> keySet() {
       return mapNodes(node -> node.key);
    }

    @Override
    public MyCollection<V> values() {
        return mapNodes(node -> node.value);
    }

    @Override
    public MyList<Entry<K, V>> entrySet() {
        return mapNodes(node -> node);
    }

    private <R> MyList<R> mapNodes(Function<Node<K, V>, R> mappingFunction) {
        MyList<R> result = new MyArrayList<>(size);
        traverseAndMap(root, result, mappingFunction);
        return result;
    }

    private <R> void traverseAndMap(Node<K, V> subTreeRoot, MyList<R> target, Function<Node<K, V>, R> mappingFunction) {
        if (subTreeRoot.left != null) {
            traverseAndMap(subTreeRoot.left, target, mappingFunction);
        }
        target.add(mappingFunction.apply(subTreeRoot));
        if (subTreeRoot.right != null) {
            traverseAndMap(subTreeRoot.right, target, mappingFunction);
        }
    }


    private boolean traverseAndSearchValue(Node<K, V> subTreeRoot, V value) {
        if (subTreeRoot == null) {
            return false;
        }

        if (subTreeRoot.value.equals(value)) {
            return true;
        }

//        if (subTreeRoot.left != null) {
//            return traverseAndSearchValue(subTreeRoot.left, value);
//        }
//
//        if (subTreeRoot.right != null) {
//            return traverseAndSearchValue(subTreeRoot.right, value);
//        }
        return traverseAndSearchValue(subTreeRoot.left, value) || traverseAndSearchValue(subTreeRoot.right, value);
    }


}
