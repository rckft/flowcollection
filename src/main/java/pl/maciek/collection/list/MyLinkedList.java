package pl.maciek.collection.list;

import java.util.Collection;

public class MyLinkedList<T> implements MyList<T> {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        T value;
        Node next;
        Node previous;

        private void prependNode(Node node) {
            this.previous = node;
            node.next = this;
        }

        private void appendNode(Node node) {
            this.next = node;
            node.previous = this;
        }
    }

    @Override
    public boolean add(T element) {
        var newNode = makeNode(element);
        if (head == null) {
            head = newNode;
        } else {
            tail.appendNode(newNode);
        }
        tail = newNode;
        size++;
        return true;
    }

    @Override
    public boolean add(int index, T element) {
        checkIfIndexIsInBounds(index);
        var newNode = makeNode(element);

        if (index == 0) {
            head.prependNode(newNode);
            head = newNode;
        }

        if (index > 0) {
            var nodeAtIndex = getNode(index);
            var previousNode = nodeAtIndex.previous;
            previousNode.appendNode(newNode);
            nodeAtIndex.prependNode(newNode);
        }

        size++;
        return true;
    }

    @Override
    public boolean remove(int index) {
        checkIfIndexIsInBounds(index);

        if (index == 0) {
            head = head.next;
            head.previous = null;
        }
        if (index == size - 1) {
            tail = tail.previous;
            tail.next = null;
        }
        if (index > 0 && index < size - 1) {
            var nodeToRemove = getNode(index);
            var previousNode = nodeToRemove.previous;
            var nextNode = nodeToRemove.next;
            previousNode.appendNode(nextNode);
        }
        size--;
        return true;
    }

    @Override
    public T get(int index) {
        return getNode(index).value;
    }

    @Override
    public void set(int index, T element) {
        checkIfIndexIsInBounds(index);
        getNode(index).value = element;
    }

    @Override
    public int indexOf(T element) {
        var currentNode = head;
        for (int i = 0; i < size; i++) {
            if (currentNode.value == element) {
                return i;
            } else {
                currentNode = currentNode.next;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(T element) {
        var currentNode = tail;
        for (int i = size - 1; i >= 0 ; i--) {
            if (currentNode.value == element) {
                return i;
            } else {
                currentNode = currentNode.previous;
            }
        }
        return -1;
    }

    @Override
    public boolean remove(T element) {
        var index = indexOf(element);
        if (index == -1) return false;
        remove(index);
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        for (T element : collection) {
            add(element);
        }
        return true;
    }

    @Override
    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    private Node getNode(int index) {
        var node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private Node makeNode(T element){
        var node = new Node();
        node.value = element;
        return node;
    }

    private void checkIfIndexIsInBounds(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    }

}
