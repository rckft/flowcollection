package pl.maciek.collection.list;

import java.util.Collection;

public class MyArrayList<T> implements MyList<T> {

    private T[] data;
    private int size = 0;

    // typy prymitywne mają swoje wartości domyślne, jesli są polem klasy

    public MyArrayList() {
        data = (T[]) new Object[10]; // 10 nulli
    }

    public MyArrayList(int capacity) {
        data = (T[]) new Object[capacity];
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

    }

    @Override
    public int indexOf(T element) {
        return 0;
    }

    @Override
    public int lastIndexOf(T element) {
        return 0;
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
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return false;
    }

    @Override
    public boolean contains(T element) {
        return false;
    }

    @Override
    public void clear() {

    }

    private void resize() {
        T[] newDataArray = (T[]) new Object[(int) (size * 1.5)];
        for (int i = 0; i < size; i++) {
            newDataArray[i] = data[i];
        }
        data = newDataArray;
    }
}
