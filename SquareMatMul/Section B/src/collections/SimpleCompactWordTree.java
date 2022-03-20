package collections;

import collections.exceptions.CharNode;
import collections.exceptions.InvalidWordException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class SimpleCompactWordTree implements CompactWordsSet {

  CharNode root = new CharNode("", false,new HashMap<>());


  @Override
  public boolean add(String word) throws InvalidWordException {
    CompactWordsSet.checkIfWordIsValid(word);
    // TO BE IMPLEMENTED
    return false;
  }

  @Override
  public boolean remove(String word) throws InvalidWordException {
    CompactWordsSet.checkIfWordIsValid(word);
    // TO BE IMPLEMENTED
    return false;
  }

  @Override
  public boolean contains(String word) throws InvalidWordException {
    CompactWordsSet.checkIfWordIsValid(word);
    int c = 0;
    boolean found = false;
    CharNode current = root;

    while (c < word.length()) {
      char chr = word.charAt(c);
      if (!current.hasChild(word.charAt(c))){
        return false;
      }
      current = current.getChild(chr);
      c++;
    }
    return current.getFlag();
  }

  @Override
  public int size() {
    // TO BE IMPLEMENTED
    return -1;
  }

  @Override
  public List<String> uniqueWordsInAlphabeticOrder() {
    // TO BE IMPLEMENTED
    return null;
  }
}
