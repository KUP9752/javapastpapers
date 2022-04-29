package utils;

import java.util.concurrent.locks.Lock;

public class LockNode<E> extends Node<E> {
    private Lock lock;
    private LockNode<E> next;

    public LockNode(E item) {
        super(item);
    }

    public LockNode(E item, Node<E> next) {
        super(item, next);
    }

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }

    public LockNode<E> getNext() {
        return next;
    }


}
