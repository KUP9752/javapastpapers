package datastructures;

import domain.Agent;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Node<T> implements Comparable<Node<T>> {

    private final Agent key;
    private List<T> items;
    private Node<T> next;

    public Node(Agent key) {
        this.key = key;
        items = new ArrayList<>();
    }
    public Node(Agent key, T item) {
        this.key = key;
        items = new ArrayList<>();
        addItem(item);
    }


    public void addItem(T item) {
        items.add(item);
    }

    public boolean noItems() {
        return items.isEmpty();
    }
    //will always exist but i found it elegeant to call like this
    public Optional<T> getItem(){
        return (noItems())
                ? Optional.empty()
                : Optional.of(items.remove(0));
    }


    public Agent getKey() {
        return key;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public int compareToAgent(Agent agent) {
        return this.key.id - agent.id;
    }

    @Override
    public int compareTo(Node<T> other) {
        return key.id - other.key.id;
    }
}
