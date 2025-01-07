package pl.maciek.collection.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

public class MyImmutableArrayList<T> implements MyList<T> {

    private T[] data;

    public MyImmutableArrayList(T... elements) {
        data = elements;
    }

    @Override
    public boolean add(T element) {
        return false;
    }

    @Override
    public boolean remove(T element) {
        return false;
    }

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public boolean isEmpty() {
        return data.length == 0;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return false;
    }

    @Override
    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(int index, T element) {
        return false;
    }

    @Override
    public boolean remove(int index) {
        return false;
    }

    @Override
    public T get(int index) {
        return data[index];
    }

    @Override
    public void set(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T element) {
        for (int i = data.length - 1; i >= 0; i--) {
            if (data[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
