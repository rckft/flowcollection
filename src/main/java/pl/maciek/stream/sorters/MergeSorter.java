package pl.maciek.stream.sorters;

import pl.maciek.collection.list.MyArrayList;
import pl.maciek.collection.list.MyList;
import pl.maciek.stream.MyComparator;

public class MergeSorter<T> implements Sorter<T> {

    private final MyList<T> source;
    private final MyComparator<T> comparator;

    public MergeSorter(MyList<T> source, MyComparator<T> comparator) {
        this.source = source;
        this.comparator = comparator;
    }

    @Override
    public MyList<T> sort() {
        return mergeSort(source, comparator);
    }

    private MyList<T> mergeSort(MyList<T> source, MyComparator<T> comparator) {
        if (source.size() < 2) {
            return source;
        }

        int middleIndex = source.size() / 2;
        MyList<T> leftPartSorted = mergeSort(split(source, 0, middleIndex), comparator);
        MyList<T> rightPartSorted = mergeSort(split(source, middleIndex, source.size()), comparator);

        var result = new MyArrayList<T>();

        var i = 0;
        var j = 0;

        while (result.size() < source.size()) {

            if (i == leftPartSorted.size()) {
                while (j < rightPartSorted.size()) {
                    result.add(rightPartSorted.get(j));
                    j++;
                }
                break;
            }

            if (j == rightPartSorted.size()) {
                while (i < leftPartSorted.size()) {
                    result.add(leftPartSorted.get(i));
                    i++;
                }
                break;
            }

            if (comparator.compare(leftPartSorted.get(i), rightPartSorted.get(j)) > 0) {
                result.add(rightPartSorted.get(j));
                j++;
            } else {
                result.add(leftPartSorted.get(i));
                i++;
            }
        }

        return result;
    }


    private MyList<T> split(MyList<T> source, int start, int end) {
        var result = new MyArrayList<>();
        for (int i = start; i < end; i++) {
            result.add(source.get(i));
        }
        return (MyList<T>) result;
    }
}
