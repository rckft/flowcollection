package pl.maciek.collection.list;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements MyList<T>, MyDeque<T> {

    private Node head;
    private Node tail;
    private int size;
    private int modCount;

    private class MyLinkedListIterator implements Iterator<T> {
        private Node pointer;
        private final int expectedModCount = modCount;

        MyLinkedListIterator() {
            this.pointer = head;
        }

        @Override
        public boolean hasNext() {
            return pointer != null;
        }

        @Override
        public T next() {
            if (expectedModCount != modCount) throw new ConcurrentModificationException();
            if (!hasNext()) throw new NoSuchElementException();
            T value = pointer.value;
            pointer = pointer.next;
            return value;
        }
    }

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
        modCount++;
        return true;
    }

    @Override
    public boolean add(int index, T element) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        var newNode = makeNode(element);

        if (index == size) {
            add(element);
            return true;
        }

        if (index == 0) {
            if (head == null) {
                head = newNode;
            } else {
                head.prependNode(newNode);
                head = newNode;
            }
        }

        if (index > 0) {
            var nodeAtIndex = getNode(index);
            var previousNode = nodeAtIndex.previous;
            previousNode.appendNode(newNode);
            nodeAtIndex.prependNode(newNode);
        }

        size++;
        modCount++;
        return true;
    }

    @Override
    public boolean remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

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
        modCount++;
        return true;
    }

    @Override
    public T get(int index) {
        return getNode(index).value;
    }

    @Override
    public void set(int index, T element) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        getNode(index).value = element;
        modCount++;
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
        modCount++;
    }

    @Override
    public boolean offer(T e) {
        addLast(e);
        return true;
    }

    @Override
    public T poll() {
        if (isEmpty()) return null;
        return pollFirst();
    }

    @Override
    public T element() {
        return getFirst();
    }

    @Override
    public T peek() {
        return peekFirst();
    }

    @Override
    public T remove() {
        if (isEmpty()) throw new NoSuchElementException();
        return null;
    }

    @Override
    public void addFirst(T e) {
        offerFirst(e);
    }

    @Override
    public void addLast(T e) {
        offerLast(e);
    }

    @Override
    public boolean offerFirst(T e) {
        add(0, e);
        return true;
    }

    @Override
    public boolean offerLast(T e) {
        add(size, e);
        return true;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        return pollFirst();
    }

    @Override
    public T removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        return pollLast();
    }

    @Override
    public T pollFirst() {
        if (isEmpty()) return null;
        var first = peekFirst();
        remove(0);
        return first;
    }

    @Override
    public T pollLast() {
        if (isEmpty()) return null;
        var last = peekLast();
        remove(size - 1);
        return last;
    }

    @Override
    public T getFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        return peekFirst();
    }

    @Override
    public T getLast() {
        if (isEmpty()) throw new NoSuchElementException();
        return peekLast();
    }

    @Override
    public T peekFirst() {
        return head == null ? null : head.value;
    }

    @Override
    public T peekLast() {
        return tail == null ? null : tail.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
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

}
