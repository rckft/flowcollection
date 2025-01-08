package pl.maciek.stream;

import pl.maciek.collection.list.MyArrayList;
import pl.maciek.collection.list.MyList;

import java.util.ArrayList;
import java.util.function.Function;

public class MyStream<T> {

    private final MyList<T> elements;

    public MyStream(MyList<T> elements) {
        this.elements = elements;
    }

    public MyList<T> toList() {
        var list = new MyArrayList<T>();
        for (var element: elements){
            list.add(element);
        }
        return list;
    }

    public MyStream<T> filter(MyPredicate<T> predicate) {
        var result = new MyArrayList<T>(elements.size());
        for (var element : elements) {
            if (predicate.test(element)) {
                result.add(element);
            }
        }
        return new MyStream<>(result);
    }

    public <R> MyStream<R> map(MyFunction<T, R> mapper) {
        var result = new MyArrayList<R>(elements.size());
        for (var element: elements) {
            result.add(mapper.apply(element));
        }
        return new MyStream<>(result);
    }

    public void forEach(MyConsumer<T> action) {
        for (var element: elements) {
            action.accept(element);
        }
    }

    public long count() {
        return elements.size();
    }

    public MyStream<T> distinct() {
        var result = new MyArrayList<T>(elements.size());
        for (var element: elements) {
            if (!result.contains(element)) {
                result.add(element);
            }
        }
        return new MyStream<>(result);
    }

    public MyStream<T> limit(int maxSize) {
        var result = new MyArrayList<T>(maxSize);
        for (int i = 0; i < maxSize; i++) {
            result.add(elements.get(i));
        }
        return new MyStream<>(result);
    }

    public MyStream<T> skip(int n) {
        var result = new MyArrayList<T>(elements.size() - n);
        for (int i = n; i < elements.size(); i++) {
            result.add(elements.get(i));
        }
        return new MyStream<>(result);
    }

    public MyStream<T> sorted(MyComparator<T> comparator) {
        var result = new MyArrayList<T>();

        for (var element : elements) {
            result.add(element);
        }

        for (int j = elements.size() - 1; j > 0; j--) {
            for (int i = 0; i < j; i++) {
                var lhs = result.get(i);
                var rhs = result.get(i + 1);
                var compareResult = comparator.compare(lhs, rhs);
                if (compareResult > 0) {
                    result.set(i + 1, lhs);
                    result.set(i, rhs);
                }
            }
        }

        return new MyStream<>(result);
    }

}
