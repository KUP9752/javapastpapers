package generalmatrices.examples;

import generalmatrices.matrix.Matrix;
import generalmatrices.pair.PairWithOperators;
import java.util.List;

public class Example {

  public static Matrix<PairWithOperators> multiplyPairMatrices(
        List<Matrix<PairWithOperators>> matrices) {

    //cannot be an empty optional as long as the given list is not empty
    return matrices.stream().reduce( (m1, m2) ->
            m1.product(m2, PairWithOperators::sum, PairWithOperators::product)).get();
  }

}
