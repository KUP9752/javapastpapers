public class Queue<T> implements QueueInterface<T>{
	
	private Node<T> first;
	private Node<T> last;

	public Queue() {
		this.last = null;
	}

	public boolean isEmpty() {
		return last == null;
	}
	
	//post: Adds the given item to the queue
	public void enqueue(T item) {
		Node<T> newNode = new Node<>(item);
		if (isEmpty()) {
			first = newNode;
		} else {
			last.setNext(newNode);
		}
		last = newNode;
	}
	
	//post: Removes and returns the head of the queue. It throws an 
	//      exception if the queue is empty.
	public T dequeue() throws QueueException {
		if (isEmpty()) throw new QueueException("Queue is empty");
		//handle only 1 item in queue case:
		T result = first.getItem();
		first = first.getNext();

		if (first == null) { // if head is empty the queue should be empty
			last = null;
		}
		return result;

	}
	
}
