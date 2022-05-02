package datastructures;

import domain.Agent;
import domain.producttypes.ExchangeableGood;

import javax.swing.text.html.Option;
import java.util.Optional;

public class StockImpl<E extends ExchangeableGood> implements Stock<E> {

  // TODO Implement a BST based priority queue as described in the specs
  protected Node<E> head;
  private int size = 0;

  public StockImpl() {
    this.head = null;
  }

  protected void incrementSize() {
    size++;
  }

  protected void decrementSize(){
    size--;
  }

  public boolean isEmpty() {
    return size == 0;
  }
  @Override
  public void push(E item, Agent agent) {
    boolean needNewNode = true;

    Node<E> current = head;
    Node<E> pred = head;

    while (current != null) {
      if (current.compareToAgent(agent) == 0) {// same agent {
        current.addItem(item);
        needNewNode = false;
        break;
      } else if (current.compareToAgent(agent) > 0) {
        //move along the nodes
        pred = current;
        current = current.getNext();
      }
    }

    // Node insertion logic pred -> newNode -> current, however current can be head
    if (needNewNode) {
      Node<E> newNode = new Node(agent, item);
      if(current == head) {
        newNode.setNext(current);
        head = newNode;
      } else {
        pred.setNext(newNode);
        newNode.setNext(current);
      }
    }

    incrementSize();
  }

  @Override
  public Optional<E> pop() {
    // Hint: always returns a product from the highest priority node. If a node gets to zero
    // products, it should be removed. Because this structure is a BST with nodes sorted by
    // agent.id,
    // the highest priority node should be the rightmost node, which can only be either a leaf or a
    // node with a single child (the left one).
    Optional<E> item = Optional.empty();
    if (!isEmpty()) {
      item = head.getItem();
      if (head.noItems()) {
        head = head.getNext();
      }
      decrementSize();
    }

    return item;
  }

  @Override
  public int size() {
    // Hint: it is just an integer that needs incrementing/decrementing...
    return size;
  }
}
