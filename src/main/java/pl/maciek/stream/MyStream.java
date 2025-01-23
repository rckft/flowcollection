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

        for (int i = elements.size() - 1; i > 0; i--) {
            boolean noChange = true;
            for (int j = 0; j < i; j++) {
                var lhs = result.get(j);
                var rhs = result.get(j + 1);
                var compareResult = comparator.compare(lhs, rhs);
                if (compareResult > 0) {
                    noChange = false;
                    result.set(j + 1, lhs);
                    result.set(j, rhs);
                }
            }
            if (noChange) break;
        }

        return new MyStream<>(result);
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

    private void copyElements(MyList<T> target) {
        for (var element: elements) {
            target.add(element);
        }
    }


}
