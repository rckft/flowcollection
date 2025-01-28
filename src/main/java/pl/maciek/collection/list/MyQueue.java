package pl.maciek.collection.list;

import pl.maciek.collection.MyCollection;

public interface MyQueue<T> extends MyCollection<T> {

    boolean offer(T e);

    T poll();

    T element();

    T peek();

    T remove();


}
