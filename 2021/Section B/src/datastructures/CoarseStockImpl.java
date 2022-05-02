package datastructures;

import domain.Agent;
import domain.producttypes.ExchangeableGood;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class CoarseStockImpl<E extends ExchangeableGood> extends StockImpl<E>{
    private final AtomicInteger size = new AtomicInteger(0);

    @Override
    protected void incrementSize() {
        size.getAndIncrement();
    }

    @Override
    protected void decrementSize() {
        size.getAndDecrement();
    }

    public CoarseStockImpl() {
        super();
    }

    @Override
    public boolean isEmpty() {
        return size.intValue() == 0;
    }

    @Override
    public synchronized void push(E item, Agent agent) {
        super.push(item, agent);
    }

    @Override
    public synchronized  Optional<E> pop() {
        return super.pop();
    }

    @Override
    public synchronized int size() {
        return size.intValue();
    }
}
