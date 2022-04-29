package utils;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class CoarseSafeQueue<E> extends UnsafeQueue<E>{
    private Node<E> head;
    private Node<E> tail;
    private final AtomicInteger size = new AtomicInteger(0);

    public CoarseSafeQueue() {
        this.head = new Node(null);
        this.tail = head;
        head.setNext(tail);
    }

    @Override
    protected void imcrementSize() {
        size.incrementAndGet();
    }

    @Override
    protected void decrementSize() {
        size.decrementAndGet();
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    synchronized public void push(E element) {
        super.push(element);
    }

    @Override
    synchronized public Optional<E> pop() {
        return super.pop();
    }

    @Override
    public int size() {
        return size.intValue();
    }
}
