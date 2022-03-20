package collections.exceptions;

import java.util.HashMap;
import java.util.List;

public class CharNode {
    final String chr;
    boolean isWord;
    final HashMap<Character, CharNode> children;

    public CharNode(String c, boolean isWord, HashMap<Character, CharNode> cs) {
        this.chr = c;
        this.isWord = isWord;
        this.children = cs;
    }

    public String getChar() {
        return chr;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setFlag(boolean newState) {
        isWord = newState;
    }

    public List<CharNode> getChildren() {
        return children.values().stream().toList();
    }

    public void addChild(char chr) {
        children.put(chr, new CharNode(String.valueOf(chr),false, new HashMap<>()));
    }

    public boolean hasChild(char chr) {
        return children.containsKey(chr);
    }

    public CharNode getChild(char chr) {
        return children.get(chr);
    }

    public static void main(String[] args) {
        System.out.println(String.valueOf('a'));
    }
}
