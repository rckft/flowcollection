package pl.maciek.collection.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements MyList<T> {

    private final int DEFAULT_INIT_SIZE = 10;
    private final double RESIZE_FACTOR = 1.5;

    private T[] data;
    private int size = 0;

    public MyArrayList() {
        data = (T[]) new Object[DEFAULT_INIT_SIZE];
    }

    public MyArrayList(int capacity) {
        data = (T[]) new Object[capacity];
    }

    private class MyArrayListIterator implements Iterator<T> {
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            return data[index++];
        }
    }

    @Override
    public boolean add(int index, T element) {
        checkIfIndexIsInBounds(index);
        if (size == data.length) {
            resize();
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = element;
        size++;
        return true;
    }

    @Override
    public boolean remove(int index) {
        checkIfIndexIsInBounds(index);
        if (index > size) return false;
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size] = null;
        size--;
        return true;
    }

    @Override
    public T get(int index) {
        checkIfIndexIsInBounds(index);
        return data[index];
    }

    @Override
    public void set(int index, T element) {
        checkIfIndexIsInBounds(index);
        data[index] = element;
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (element == data[i]) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T element) {
        for (int i = size - 1; i >= 0; i--) {
            if (element == data[i]) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean add(T element) {
        if(size == data.length) {
            resize();
        }
        data[size++] = element;
        return true;
    }

    @Override
    public boolean remove(T element) {
        var elementToRemoveIndex = indexOf(element);
        if (elementToRemoveIndex == -1) return false;
        return remove(elementToRemoveIndex);
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
        if (collection.isEmpty()) return false;
        var combinedArraySize = size + collection.size();
        if (combinedArraySize > data.length) {
            T[] newDataArray = (T[]) new Object[combinedArraySize];
            for (int i = 0; i < size; i++) {
                newDataArray[i] = data[i];
            }
            data = newDataArray;
        }
        for (var element: collection) {
            data[size++] = element;
        }
        return true;
    }

    @Override
    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    @Override
    public void clear() {
        T[] newDataArray = (T[]) new Object[DEFAULT_INIT_SIZE];
        data = newDataArray;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

    private void resize() {
        T[] newDataArray = (T[]) new Object[(int) (size * RESIZE_FACTOR)];
        for (int i = 0; i < size; i++) {
            newDataArray[i] = data[i];
        }
        data = newDataArray;
    }

    private void checkIfIndexIsInBounds(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    }
}
