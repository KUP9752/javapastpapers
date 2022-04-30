package utils;

public class Node<E> {

    protected final E item;
    private Node<E> next;

    public Node(E item) {
        this.item = item;
        if (item != null) {
            this.next = new Node(null);
        } else {
            this.next = null;
        }
    }

    public Node(E item, Node<E> next) {
        this.item = item;
        this.next = next;
    }

    public E getItem() {
        return item;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
}
