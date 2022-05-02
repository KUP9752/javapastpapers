package generators.integer;

import generators.generic.DataGenerator;


public interface IntegerGenerator extends DataGenerator<Integer> {

    boolean hasNext();

    Integer next();
}
