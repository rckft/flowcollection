package pl.maciek.stream;

@FunctionalInterface
public interface MyComparator<T> {
    int compare(T o1, T o2);
}
