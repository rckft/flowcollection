package pl.maciek.stream.sorters;

import pl.maciek.collection.list.MyList;
import pl.maciek.stream.MyComparator;

public class BubbleSorter<T> implements Sorter<T> {

    private final MyList<T> source;
    private final MyComparator<T> comparator;

    public BubbleSorter(MyList<T> source, MyComparator<T> comparator) {
        this.source = source;
        this.comparator = comparator;
    }

    @Override
    public MyList<T> sort() {
        for (int i = source.size() - 1; i > 0; i--) {
            boolean noChange = true;
            for (int j = 0; j < i; j++) {
                var lhs = source.get(j);
                var rhs = source.get(j + 1);
                var compareResult = comparator.compare(lhs, rhs);
                if (compareResult > 0) {
                    noChange = false;
                    source.set(j + 1, lhs);
                    source.set(j, rhs);
                }
            }
            if (noChange) break;
        }
        return source;
    }
    
}
