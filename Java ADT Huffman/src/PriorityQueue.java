public class PriorityQueue<E extends Comparable<E>> implements PriorityQueueInterface<E>{

	private E[] items;    //a heap of HuffmanTrees
	private final static int max_size = 256;
	private int size;    //number of HuffManTrees in the heap.
	
	@SuppressWarnings("unchecked")
	public PriorityQueue( ) {
        // constructor which creates an empty heap
		items = (E[]) new Comparable[max_size];
		size = 0;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int getSize(){
		return size;
	}

	public E getMin(){
		E root = null;
		if (!isEmpty()) root = items[0];
		return root;
	}
	
	public void add(E newEntry) throws PriorityQueueException {
	// post: Adds a new entry to the priority queue according to 
        // the priority value.

		if (size < max_size) {
			items[size] = newEntry;
			percolateUp(size);
			size++;
		} else throw new PriorityQueueException("Queue Full!");

	}

	private void percolateUp(int c) {
		if (c > 1) {
			int parent = ( c - 1) / 2;
			if (items[c].compareTo(items[parent]) < 0) {
				swap(c, parent);
				percolateUp(parent);
			}

		}
	}

	private void swap(int i1, int i2) {
		E temp = items[i1];
		items[i1] = items[i2];
		items[i2] = temp;
	}
 				
 	public E removeMin(){
	// post: Removes the minimum valued item from the PriorityQueue
		E root = null;
		if (!isEmpty()){
			root = items[0];
			items[0] = items[size-1];
			size--;
			heapRebuild(0);
		}
		return root;
	}
	
	private void heapRebuild(int root) {
		// Rebuild heap to keep it ordered
		int left = root * 2 + 1; //index of left child in array
		int right = root * 2 + 2; //index of right child in array
		int min;
		if (left <= size) {
			if (left == size) { // no right child exists
				min = left;
			} else {
				min = (items[left].compareTo(items[right]) < 0)
						? left
						: right;
			}
			// if root is larger than smaller child then we swap
			if (items[root].compareTo(items[min]) > 0) {
				swap(root, min);
				//if swap happens we have to keep fixing from there
				heapRebuild(min);
			}

		}
	}
}
