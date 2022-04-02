import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a min-heap abstract data type (as described by the
 * generic interface IMinHeap<T extends Comparable<T>>) using a fixed array of
 * size MinHeap.MAXIMUM_HEAP_SIZE.
 */
public class MinHeap<T extends Comparable<T>> implements IMinHeap<T> {
    private final ArrayList<T> entries;
    public static final int MAXIMUM_HEAP_SIZE = 200 + 1; //as first index is skipped.
    private int size;
    private int nextFree;

    public MinHeap() {
        this.entries =  new ArrayList<T>(MAXIMUM_HEAP_SIZE);
        this.size = 0;
        this.nextFree = 1; // skipping index 0 for ease of use
    }

    private boolean isFull() {
        return size == MAXIMUM_HEAP_SIZE;
    }
    @Override
    public void add(T element) throws HeapException {
        if (isFull()) {
            throw new HeapException("Heap is full");
        } else {
            if (isEmpty()) {
                entries.add(nextFree, element);
            } else {
                entries.add(nextFree, element);
                int currentIndex = nextFree;
                int parentIndex = currentIndex / 2;
                T parent = entries.get(parentIndex);
                while (element.compareTo(parent) < 0 ) { // current is smaller than parent.
                    T temp = entries.set(parentIndex, element);
                    entries.set(currentIndex, temp);
                    currentIndex = parentIndex;
                    parentIndex = currentIndex / 2;
                    parent = entries.get(parentIndex);
                }
            }
            this.size++;
            this.nextFree++;
        }
    }

    @Override
    public T removeMin() {
        assert !isEmpty();
        T min = getMin();
        T current = entries.remove(nextFree - 1); // last element
        entries.set(1, current);
        int currentIndex = 1;
        T left;
        T right;
        int leftIndex = currentIndex * 2;
        int rightIndex = currentIndex * 2 + 1;

        while (currentIndex < nextFree) {
            left = null;
            right = null;
            if (leftIndex < nextFree) {
                left = entries.get(leftIndex);
            }
            if (rightIndex < nextFree) {
                right = entries.get(rightIndex);
            }
            if (left != null && current.compareTo(left) > 0) {
                entries.set(currentIndex, left);
                entries.set(leftIndex, current);
                currentIndex = leftIndex;
            } else if (right != null && current.compareTo(right) > 0) {
                entries.set(currentIndex, right);
                entries.set(rightIndex, current);
                currentIndex = rightIndex;
            }
            if (left == null && right == null) {
                // current is placed in the correct location, minHeap holds
                break;
            }
            leftIndex = currentIndex * 2;
            rightIndex = currentIndex * 2 + 1;
        }
        size--;
        return min;
    }

    @Override
    public T getMin() {
        assert !isEmpty();
        return entries.get(1);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

}