package pl.maciek.stream.sorters;

import pl.maciek.collection.list.MyList;
import pl.maciek.stream.MyComparator;

public interface Sorter<T> {

    MyList<T> sort();

}
