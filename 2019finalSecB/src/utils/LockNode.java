package utils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockNode<E>  {

    private final E item;
    private final Lock lock =  new ReentrantLock();
    private LockNode<E> next;

    public LockNode(E item) {
        this.item = item;
        if (item != null) {
            this.next = new LockNode<>(null);
        } else {
            this.next = null;
        }
    }



    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }

    public E getItem() {
        return item;
    }

    public LockNode<E> getNext() {
        return next;
    }

    public void setNext(LockNode<E> next) {
        this.next = next;
    }


}
