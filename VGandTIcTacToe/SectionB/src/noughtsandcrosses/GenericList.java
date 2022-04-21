package noughtsandcrosses;

import java.util.Iterator;

public class GenericList<T> implements GenericListInterface<T>{

	private ListNode<T> head;      //reference to the first element in the list
	private int numItems;	       // number of items in the list

	public GenericList(){
		numItems = 0;
		head = null;
	}// end default constructor;

	//post: Returns true if if the list is empty, false otherwise.
	public boolean isEmpty(){
		return (numItems == 0);
	}// end isEmpty

	//post: Returns the number of items in the list
	public int size() {
		return numItems;
	}// end size

	//post: Auxiliary method for finding a node at a given position in the list
	private ListNode<T> find(int pos){
		ListNode<T> curr = head;
		for (int skip = 1; skip < pos; skip++){
			curr = curr.getNext();
		}
		return curr;
	}//end find

	//post: Returns the item stored in the node at a given position in the list
	public T get(int pos) throws ListIndexOutOfBoundsException{
		if (pos >= 1 && pos <= numItems) {
			ListNode<T> curr = find(pos);
		  	return curr.getItem();
		}
		else {
			throw new ListIndexOutOfBoundsException("position out of range in get method of a list");
		}
	}// end get

	
	//YOU ARE ASKED TO IMPLEMENT THIS METHOD
	//post: Adds the given item at the given position in the list. It throws an exception if the
	//      position is out of bound.
	public void add(int pos, T item) throws ListIndexOutOfBoundsException{
		ListNode<T> newNode = new ListNode<>(item);
		if (isEmpty()){
			head = newNode;
			numItems++;
		} else {
			if (pos >= 1 && pos <= numItems) { // can't insert anywhere else
				ListNode<T> insertAfter = findNode(pos);
				ListNode<T> next = insertAfter.getNext();
				insertAfter.setNext(newNode); //our node gets in correct location
				newNode.setNext(next);  //readjust next to keep sequence
				numItems++; // new addition hence increment

			} else {
				throw new ListIndexOutOfBoundsException("position out of range in get method of a list");
			}
		}
	}// end add
	private ListNode<T> findNode(int n) {
		ListNode<T> node = head;
		int count = 1;
		while (count < n - 1) {
			count++;
			node = node.getNext();
		}
		return node;
	}

	//post: Removes the item at the given position in the list. It throws an exceptions if the
	//      position is out of bound.
	public void remove(int pos) throws ListIndexOutOfBoundsException{
			if (pos >=1 && pos <= numItems){
				if (pos == 1){ head = head.getNext(); }
				else
				{ ListNode<T> prev = find(pos-1);
				  ListNode<T> curr = prev.getNext();
  				  prev.setNext(curr.getNext());
			 	}
				numItems--;
			}
			else {throw new ListIndexOutOfBoundsException("position out of range in remove method of a list");}
	}// end remove
	
	//post: Prints the elements in the list.
	public void display(){
			System.out.println("The size at the list is " + numItems);
			for (int pos = 1; pos<= numItems; pos++){
					System.out.println("The value is " + get(pos));
			}
	}		

	//post:  Returns a list iterator object. 
	public ListIterator iterator() {
		return new ListIterator(head);
	}	
     
    
   	private class ListIterator implements Iterator<T> {

		private ListNode<T> current;
		private ListNode<T> lastReturned;

		public ListIterator(ListNode<T> node) {
			current = node;
			lastReturned = null;
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		public boolean hasNext() {
			return current != null;
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		public T next() {
			T result = current.getItem();
			lastReturned = current;
			current = current.getNext();
			return result;
		}
		
		public void remove() {
			return;
		}
		
	}
 
	public void clear(){
			numItems = 0;
			head = null;
	}//end clear
	
}//end GenericList<T>



