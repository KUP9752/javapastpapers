package datastructures;

import domain.Agent;

import java.util.Optional;

public class FineStockImpl<E> implements Stock<E>{

    @Override
    public void push(E item, Agent agent) {

    }

    @Override
    public Optional<E> pop() {
        return Optional.empty();
    }

    @Override
    public int size() {
        return 0;
    }
}
