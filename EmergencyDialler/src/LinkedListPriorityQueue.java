import java.util.Iterator;

public class LinkedListPriorityQueue<E> implements PriorityQueue<E> {
    private Node<E> first = null;

    public LinkedListPriorityQueue() {
    }

    public boolean isEmpty() {
        return this.first == null;
    }

    public void add(double var1, E var3) {
        Node var4 = this.find(var1);
        if (var4 == null) {
            this.first = new Node(var1, var3);
        } else {
            Node var5;
            if (var4.getPriority() > var1) {
                var5 = new Node(var1, var3);
                var5.setNext(this.first);
                this.first = var5;
            } else {
                var5 = new Node(var1, var3);
                var5.setNext(var4.getNext());
                var4.setNext(var5);
            }
        }

    }

    private Node<E> find(double var1) {
        Node var3 = this.first;
        if (var3 != null && var3.getPriority() <= var1) {
            for(Node var4 = var3.getNext(); var4 != null && var4.getPriority() <= var1; var4 = var4.getNext()) {
                var3 = var4;
            }
        }

        return var3;
    }

    public E peek() {
        return !this.isEmpty() ? this.first.getValue() : null;
    }

    public E dequeue() {
        if (!this.isEmpty()) {
            Object var1 = this.first.getValue();
            this.first = this.first.getNext();
            return (E) var1;
        } else {
            return null;
        }
    }

    public void clear() {
        this.first = null;
    }

    public Iterator<Node<E>> iterator() {
        return new LinkedListPriorityQueue.PQIterator(this.first);
    }

    private class PQIterator implements Iterator<Node<E>> {
        private Node<E> current;
        private Node<E> lastReturned;

        public PQIterator(Node<E> var2) {
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
}