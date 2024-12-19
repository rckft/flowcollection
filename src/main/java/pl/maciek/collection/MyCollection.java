package pl.maciek.collection;

import java.util.Collection;

public interface MyCollection<T> {

    boolean add(T element);

    boolean remove(T element);

    int size();

    boolean isEmpty();

    boolean addAll(Collection<? extends T> collection);

    boolean contains(T element);

    void clear();

}
