package utils;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public class FineSafeQueue<E> implements Queue<E> {
  private LockNode<E> head;
  private LockNode<E> tail;
  private final AtomicInteger size = new AtomicInteger(0);

  public FineSafeQueue() {
    this.head = new LockNode<>(null);
    this.tail = head;
    head.setNext(tail);
  }

  public boolean isEmpty() {
      return size() == 0;
  }

  @Override
  public void push(E element) {
    LockNode<E> newNode = new LockNode<>(element);
    LockNode<E> oldTail = tail;
    try {
      oldTail.lock();
      oldTail.setNext(newNode);

      if (isEmpty()) { //must be head
        head = newNode;
      }
      tail = newNode;

      size.getAndIncrement();
    } finally {
      oldTail.unlock();
    }
  }

  @Override
  public Optional<E> pop() {
    if (isEmpty()) return Optional.empty();

    LockNode<E> toPop = head;
    try {
      toPop.lock();
      head = toPop.getNext();
      size.getAndDecrement();
      return Optional.of(toPop.getItem());
    } finally {
      toPop.unlock();
    }
  }

  @Override
  public int size() {
    return size.intValue();
  }
}
