package pl.maciek.stream;

@FunctionalInterface
public interface MyPredicate<T> {
    boolean test (T t);
}
