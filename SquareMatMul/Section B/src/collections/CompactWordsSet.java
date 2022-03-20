package collections;

import collections.exceptions.InvalidWordException;
import java.util.List;

public interface CompactWordsSet {

  static void checkIfWordIsValid(String word) throws InvalidWordException {
    if (word == null || word.isEmpty()) {
      throw new InvalidWordException("Invalid word");
    }
    boolean charsWithinBound = true;
    int counter = 0;
    while (charsWithinBound && counter < word.length()) {
      char c = word.charAt(counter);
      charsWithinBound = (c >= 'a' && c <= 'z');
      counter++;
    }
    if (!charsWithinBound) {
      throw new InvalidWordException("Invalid word");
    }
  }

  boolean add(String word) throws InvalidWordException;

  boolean remove(String word) throws InvalidWordException;

  boolean contains(String word) throws InvalidWordException;

  int size();

  List<String> uniqueWordsInAlphabeticOrder();

}
