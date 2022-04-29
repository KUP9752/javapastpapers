package utils;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

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
      return head == null;
  }

  @Override
  public void push(E element) {
    LockNode<E> newTail = new LockNode(element);

    if (isEmpty()) {
      head = newTail;
      tail = newTail;
    } else {
        LockNode<E> oldTail = tail;
        try {
          oldTail.lock();
          oldTail.setNext(newTail);
        } finally {
          oldTail.unlock();
        }
    }
    size.getAndIncrement();
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
