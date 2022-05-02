package generators.generic;

import generators.Pair;

public class PairGenerator<S, T> implements DataGenerator<Pair<S, T>> {
    private DataGenerator<S> generatorS;
    private DataGenerator<T> generatorT;

    public PairGenerator(DataGenerator<S> generatorS, DataGenerator<T> generatorT) {
        this.generatorS = generatorS;
        this.generatorT = generatorT;
    }

    @Override
    public boolean hasNext() {
        return generatorS.hasNext() && generatorT.hasNext();
    }

    @Override
    public Pair<S, T> next() {
        if (!hasNext()) throw new UnsupportedOperationException();
        return new Pair(generatorS.next(), generatorT.next());
    }
}
