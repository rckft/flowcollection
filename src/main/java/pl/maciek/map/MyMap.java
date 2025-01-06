package pl.maciek.map;

import pl.maciek.collection.MyCollection;
import pl.maciek.collection.list.MyList;

public interface MyMap<K, V> {

    interface Entry<K, V> {
        K getKey();
        V getValue();

    }

    int size();
    boolean isEmpty();
    boolean containsKey(K key);
    boolean containsValue(V value);
    V get(K key);
    V put(K key, V value);
    V remove(K key);
    void clear();

    MyList<K> keySet();
    MyCollection<V> values();
    MyList<MyMap.Entry<K, V>> entrySet();


}
