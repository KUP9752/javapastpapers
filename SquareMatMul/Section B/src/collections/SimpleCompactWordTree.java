package collections;

import collections.exceptions.CharNode;
import collections.exceptions.InvalidWordException;

import java.util.*;

public class SimpleCompactWordTree implements CompactWordsSet {

  protected CharNode root = new CharNode("", false,new HashMap<>());
  private int size = 0;


  @Override
  public boolean add(String word) throws InvalidWordException {
    CompactWordsSet.checkIfWordIsValid(word);
    int c = 0;
    CharNode current = root;

    while (c < word.length()) {
      char chr = word.charAt(c);
      if (!current.hasChild(chr)) {
        current.addChild(chr);
        size++;
      }
      current = current.getChild(chr);
      c ++;
    }
    // already a word in the set
    if (current.isWord()) {
      return false;
    } else {
      current.setFlag(true);
      return true;
    }

  }

  @Override
  public boolean remove(String word) throws InvalidWordException {
    CompactWordsSet.checkIfWordIsValid(word);
    if (contains(word)) {
      int c = 0;
      CharNode current = root;

      while (c < word.length()) {
        char chr = word.charAt(c);
        current = current.getChild(chr);
        c++;
      }
      current.setFlag(false);
      size--;
      return true;
    }
    return false;
  }

  @Override
  public boolean contains(String word) throws InvalidWordException {
    CompactWordsSet.checkIfWordIsValid(word);
    int c = 0;
//    boolean found = false;
    CharNode current = root;

    while (c < word.length()) {
      char chr = word.charAt(c);
      if (!current.hasChild(chr)){
        return false;
      }
      current = current.getChild(chr);
      c++;
    }
    return current.isWord();
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public List<String> uniqueWordsInAlphabeticOrder() {
    return dfs(root,"", new ArrayList<>());
  }

  private List<String> dfs(CharNode start, String currentString, List<String> foundWords) {
    StringBuilder sb = new StringBuilder();
    Set<CharNode> visited = new HashSet<>();
    List<CharNode> toVisit = start.getChildren().stream().toList();
    visited.add(start);
    currentString = currentString.concat(start.getChar());
    for (CharNode node : toVisit) {
      if (!visited.contains(node) && node.getChildren().isEmpty() && node.isWord()) {
        foundWords.add(currentString.concat(node.getChar()));
      }
      dfs(node, currentString, foundWords);
    }
    return foundWords;
  }
}
