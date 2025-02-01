package pl.maciek.stream;

import pl.maciek.collection.list.MyArrayList;
import pl.maciek.collection.list.MyList;

import java.util.Optional;

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
        copyElements(result);
        return new MyStream<>(mergeSort(result, comparator));
    }

    public Optional<T> findFirst() {
        if (!elements.isEmpty()) {
            return Optional.of(elements.get(0));
        } else {
            return Optional.empty();
        }
    }

    public boolean allMatch(MyPredicate<T> predicate) {
        for (var element: elements) {
            if (!predicate.test(element)) return false;
        }
        return true;
    }

    public boolean anyMatch(MyPredicate<T> predicate) {
        for (var element: elements) {
            if (predicate.test(element)) return true;
        }
        return false;
    }

    public boolean noneMatch(MyPredicate<T> predicate) {
        for (var element: elements) {
            if (predicate.test(element)) return false;
        }
        return true;
    }

    public T reduce(T identity, MyBinaryOperator<T> accumulator) {
        for (var element: elements) {
            identity = accumulator.apply(identity, element);
        }
        return identity;
    }

    public Optional<T> min(MyComparator<T> comparator) {
       return findExtremum((a, b) -> comparator.compare(a, b) > 0);
    }

    public Optional<T> max(MyComparator<T> comparator) {
        return findExtremum((a, b) -> comparator.compare(a, b) < 0);
    }

    public MyStream<T> peek(MyConsumer<T> action) {
        var result = new MyArrayList<T>(elements.size());
        copyElements(result);
        for (var element: result) {
            action.accept(element);
        }
        return new MyStream<>(result);
    }

    public <R> MyStream<R> flatMap(MyFunction<T, MyStream<R>> mapper) {
        var result = new MyArrayList<R>();
        for (var element: elements) {
            mapper.apply(element).forEach(result::add);
        }
        return new MyStream<>(result);
    }

    public String join(CharSequence delimiter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elements.size(); i++) {
            sb.append(elements.get(i));
            if (i < elements.size() - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    private Optional<T> findExtremum(MyBiFunction<T, T, Boolean> extremumComparator) {
        if (elements.isEmpty()) return Optional.empty();
        var extremum = elements.get(0);
        for (int i = 1; i < elements.size(); i++) {
            var currentElement = elements.get(i);
            if (extremumComparator.apply(extremum, currentElement)) {
                extremum = currentElement;
            }
        }
        return Optional.of(extremum);
    }

    private void copyElements(MyList<T> target) {
        for (var element: elements) {
            target.add(element);
        }
    }

    private MyList<T> bubbleSort(MyList<T> list, MyComparator<T> comparator) {
        for (int i = elements.size() - 1; i > 0; i--) {
            boolean noChange = true;
            for (int j = 0; j < i; j++) {
                var lhs = list.get(j);
                var rhs = list.get(j + 1);
                var compareResult = comparator.compare(lhs, rhs);
                if (compareResult > 0) {
                    noChange = false;
                    list.set(j + 1, lhs);
                    list.set(j, rhs);
                }
            }
            if (noChange) break;
        }
        return list;
    }

    private MyList<T> mergeSort(MyList<T> list, MyComparator<T> comparator) {
        if (list.size() < 2) {
            return list;
        }

        int middleIndex = list.size() / 2;
        MyList<T> leftPartSorted = mergeSort(split(list, 0, middleIndex), comparator);
        MyList<T> rightPartSorted = mergeSort(split(list, middleIndex, list.size()), comparator);

        var result = new MyArrayList<T>();

        var i = 0;
        var j = 0;

        while (result.size() < list.size()) {

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

    private MyList<T> split(MyList<T> target, int start, int end) {
        var result = new MyArrayList<>();
        for (int i = start; i < end; i++) {
            result.add(target.get(i));
        }
        return (MyList<T>) result;
    }


}
