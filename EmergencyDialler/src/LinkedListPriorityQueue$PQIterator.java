import java.util.Iterator;

class LinkedListPriorityQueue$PQIterator implements Iterator<Node<E>> {
    private Node<E> current;
    private Node<E> lastReturned;

    public LinkedListPriorityQueue$PQIterator(LinkedListPriorityQueue var1, Node var2) {
        this.this$0 = var1;
        this.current = var2;
        this.lastReturned = null;
    }

    public boolean hasNext() {
        return this.current != null;
    }

    public Node<E> next() {
        Node var1 = this.current;
        this.lastReturned = this.current;
        this.current = this.current.getNext();
        return var1;
    }

    public void remove() {
        throw new IllegalStateException();
    }
}