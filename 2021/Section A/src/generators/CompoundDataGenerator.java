package generators;

import generators.generic.DataGenerator;

import javax.net.ssl.HostnameVerifier;
import java.util.*;

public class CompoundDataGenerator<T> implements DataGenerator<T> {

    private final List<DataGenerator<T>> gens;
    private DataGenerator<T> currentGen;
    private  int currentPointer = 0;

    public CompoundDataGenerator(List<DataGenerator<T>> gens) {
        this.gens = new ArrayList<>(gens);
        currentGen = gens.get(currentPointer);
    }

    private void nextGenerator() {
        if (!currentGen.hasNext() && currentPointer - 1 != gens.size()){
            currentPointer++;
            currentGen = gens.get(currentPointer);
        }
    }
    @Override
    public boolean hasNext() {
        nextGenerator();
        return !(!currentGen.hasNext() &&  currentPointer - 1 == gens.size());
    }

    @Override
    public T next() {
        if (!hasNext()) throw new UnsupportedOperationException();
        return currentGen.next();
    }

    public static void main(String[] args) {
        List<Integer> ints = new ArrayList(Arrays.asList(1, 2, 3, 4));
        System.out.println(ints.get(0));
        System.out.println(ints.remove(0));

    }
}
