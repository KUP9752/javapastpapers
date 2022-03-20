package generalmatrices.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.function.BinaryOperator;

public class Matrix<T> {

    final Vector<Vector<T>>  matrix;
    final int order;

  public Matrix(List<T> input) throws IllegalArgumentException {
      if (input.isEmpty()) {
          throw new IllegalArgumentException();
      }
      this.order = (int) Math.sqrt(input.size());
      this.matrix = new Vector<Vector<T>>();
      for (int i = 0; i < order; i++) {
          List<T> subList = input.subList(i * order, (i * order) + order);
          Vector<T> row = new Vector<T>(subList);
          matrix.add(row);
          }
  }

  public T get(int row, int col) {
      return matrix.get(row).get(col);
  }

  public int getOrder() {
      return order;
  }

  public Matrix<T> sum(Matrix<T> other, BinaryOperator<T> elementSum) {
      assert other.getOrder() == getOrder();
      List<T> newMatrixList = new ArrayList<>();

      for (int i = 0; i < order; i++) {
          for (int j = 0; j < order; j++) {
              newMatrixList.add(elementSum.apply(get(i, j), other.get(i, j)));
          }
      }
      return new Matrix<>(newMatrixList);
  }

  public Matrix<T> product(Matrix<T> other, BinaryOperator<T> elementSum
          , BinaryOperator<T> elementProduct) {
      assert other.getOrder() == getOrder();

      List<T> newMatrixList = new ArrayList<>();
      List<T> tempRowList = new ArrayList<>();

      for (int i = 0; i < order; i++) {
          for (int j = 0; j < order; j++) {
              for (int c = 0; c < order; c++) {
                  tempRowList.add(elementProduct.apply(get(i, c),
                          other.get(c, j)));
              }
              //cannot be Optional empty
              newMatrixList.add(tempRowList.stream().reduce(elementSum).get());
              tempRowList.clear();
          }
      }
      return new Matrix<>(newMatrixList);
  }











  @Override
  public String toString() {
      final StringBuilder sb = new StringBuilder();
      sb.append('[');
      for (Vector<T> row : matrix) {
          sb.append('[');
          for (T entry : row) {
              sb.append(entry);
              sb.append(' ');
          }
          sb.deleteCharAt(sb.lastIndexOf(" "));
          sb.append(']');
//          sb.append(' ');
      }
//      sb.deleteCharAt(sb.lastIndexOf(" "));
      sb.append(']');
      return sb.toString();
  }

    public static void main(String[] args) {
        Matrix<Integer> twoby2 = new Matrix(List.of(1, 20, 300, 4000));
        System.out.println(twoby2.getOrder());
        System.out.println(twoby2.get(1, 1));
        System.out.println(twoby2);
    }
}
