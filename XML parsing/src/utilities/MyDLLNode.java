/**
 * @author Madhu
 * 
 *          
 **/


package utilities;

public class MyDLLNode<T> {
	
	private T element;
	private MyDLLNode<T> next;
	private MyDLLNode<T> prev;

	/**
	 * Constructs a SLL node with an element, and successor and previous node references.
	 * @author Madhu, Eze, Emmari
	 * @param element of type E containing data to be stored
	 * @param next    SLLNode containing a reference to the next node in the list.
	 * @param prev    SLLNode containing a reference to the previous node in the list.
	 */
	public MyDLLNode(T element, MyDLLNode<T> prev, MyDLLNode<T> next) {
		this.setElement(element);
		this.setPrev(prev);
		this.setNext(next);
	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public MyDLLNode<T> getNext() {
		return next;
	}

	public void setNext(MyDLLNode<T> next) {
		this.next = next;
	}

	public MyDLLNode<T> getPrev() {
		return prev;
	}

	public void setPrev(MyDLLNode<T> prev) {
		this.prev = prev;
	}
}
