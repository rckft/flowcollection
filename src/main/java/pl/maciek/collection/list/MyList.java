package pl.maciek.collection.list;

import pl.maciek.collection.MyCollection;

public interface MyList<T> extends MyCollection<T> {

    // operacje podstawowe
    // 3. of (modyfikowalna)

    boolean add(int index, T element);

    boolean remove(int index);

    T get(int index);

    void set(int index, T element);

    int indexOf(T element);

    int lastIndexOf(T element);

    static <T> MyList<T> of(T... elements) {
        return null;


    };
}
