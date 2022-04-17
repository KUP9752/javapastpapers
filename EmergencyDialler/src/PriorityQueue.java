public interface PriorityQueue<E> extends Iterable<Node<E>> {
    void add(double var1, E var3);

    E peek();

    E dequeue();

    boolean isEmpty();

    void clear();
}