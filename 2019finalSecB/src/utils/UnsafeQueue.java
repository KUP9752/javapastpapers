package utils;

import java.util.Optional;

public class UnsafeQueue<E> implements Queue<E> {
  private Node<E> head;
  private Node<E> tail;
  private int size;

  public UnsafeQueue() {
    this.head = new Node(null);
    this.tail = head;

    this.size = 0;
  }


  protected void incrementSize() {
    size++;
  }

  protected void decrementSize() {
    size--;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public void push(E element) {
    Node<E> oldTail = tail;
    tail = new Node<>(element); // points to empty node (doesn't point to null)

    if (isEmpty()) {
      head = tail;
    } else {
      oldTail.setNext(tail);
    }
    incrementSize();

  }

  @Override
  public Optional<E> pop() {
    if (isEmpty()) return Optional.empty();
    Node<E> toPop = head;
    head = head.getNext();
    decrementSize();
    return Optional.of(toPop.getItem());
  }

  @Override
  public int size() {
    return size;
  }
}
