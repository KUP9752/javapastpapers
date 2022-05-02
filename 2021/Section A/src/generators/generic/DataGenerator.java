package generators.generic;

public interface DataGenerator<T> {

    boolean hasNext();

    T next();
}
