package pl.maciek.stream;

@FunctionalInterface
public interface MyConsumer<T> {
    void accept (T t);
}
