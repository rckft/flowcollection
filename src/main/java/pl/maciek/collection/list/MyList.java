package pl.maciek.collection.list;

import pl.maciek.collection.MyCollection;

import java.util.Arrays;

public interface MyList<T> extends MyCollection<T> {

    boolean add(int index, T element);

    boolean remove(int index);

    T get(int index);

    void set(int index, T element);

    int indexOf(T element);

    int lastIndexOf(T element);

    static <T> MyList<T> of(T... elements) {
        var list = new MyArrayList<>();
        list.addAll(Arrays.asList(elements));
        return (MyList<T>) list;
    };
}
