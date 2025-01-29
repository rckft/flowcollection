package pl.maciek.stream;

@FunctionalInterface
public interface MyFunction<T, R> {
    R apply (T t);
}
