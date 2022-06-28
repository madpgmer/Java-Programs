/**
 * This Singly Linked List class implements all the abstract methods defined in the LinkedlistADT 
 * 
 * @author Madhu Madhavan
 * @version 7/28/2021
 * 
 */


package sait.sll.utility;

import java.io.Serializable;

public class SLL implements LinkedListADT, Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Node head;
	private Node tail;
	
	private int size;
	
	public SLL() {
		this.head = null;
		this.size = 0;
	}
	/**
	 * Checks if the linked list is empty or not
	 * @author Madhu
	 */
	@Override
	public boolean isEmpty() { //checked
		if (this.size > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * clears all the items in the linked list
	 * @author Madhu
	 */

	@Override
	public void clear() { //checked
		this.size = 0;
		this.head = null;
		this.tail = null;
	}
	
	/**
	 * Appends an item to the end of the linked list
	 * @author Madhu
	 * @param data
	 */

	@Override
	public void append(Object data) { //checked
		Node newNode = new Node(data); //create new node
		if (size == 0) { //this is the first node
 			this.head = newNode;
 			this.tail = newNode;
		} else if (size == 1) {
			this.head.setNext(newNode); // point head to the new node
			this.tail = newNode; //set tail to the new node also
		} else {
			this.tail.setNext(newNode); // point tail to the new node
			this.tail = newNode; //set tail to the last (new) node
		}
		size++; //increment size
	}
	/**
	 * Prepend an item to the beginning of the linked list
	 * @author Madhu
	 * @param data
	 */

	@Override
	public void prepend(Object data) { //checked
		Node newNode = new Node(data);
		if (size == 0) { //this is the first node
 			this.head = newNode;
 			this.tail = newNode;
		} else if (size > 0) {
			newNode.setNext(head);
			this.head = newNode; // point head to the new node
		} 
		size++;
	}

	//addFirst (needed for insert)
	public void addFirst(Object data) { //checked
		Node newNode = new Node(data);
		newNode.setNext(this.head);
		this.head = newNode;
		this.size++;
		
		if (this.tail == null) {
			this.tail = this.head;
		}
	}

	//addLast (needed for insert)
	public void addLast(Object data) { //checked
		Node newNode = new Node(data);
		if (this.tail == null) {
			this.head = this.tail = newNode;
		}
		else {
			this.tail.setNext(newNode);
			this.tail = newNode;
		}
		this.size++;
	}
	/**
	 * Insert an item at a specific index in the linked list
	 * If the index is 0, then it calls the Addfirst method
	 * If the index is equal to size then it calls the Addlast method
	 * otherwise it traverses down the linkedlist and inserts at the index specified
	 * @author Madhu
	 * @param data 
	 * @param index
	 */
	
	@Override
	public void insert(Object data, int index) throws IndexOutOfBoundsException { //checked
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			addFirst(data);
		} else if (index == this.size) {
			addLast(data);
		} else {
			Node current = head;
			for (int i=1; i<index; i++) {
				current = current.getNext();
			}
			Node temp = current.getNext();
			current.setNext(new Node(data));
			current.getNext().setNext(temp);
			this.size++;
		}
	}
	/**
	 * Replace an item in the linked list
	 * If the index is 0, then it calls the RemoveFirst method and then the Addfirst method
	 * If the index is equal to size-1 then it calls the RemoveLast method and then the AddLast method
	 * otherwise it traverses down the linkedlist and then replaces at the index specified
	 * @author Madhu
	 * @param data 
	 * @param index
	 */

	@Override
	public void replace(Object data, int index) throws IndexOutOfBoundsException { //checked
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			removeFirst();
			addFirst(data);
		} else if (index == size-1) {
			removeLast();
			addLast(data);
		} else {
			Node previous = this.head;
			
			for (int i=1; i<index; i++) {
				previous = previous.getNext();
			}
			Node current = previous.getNext().getNext();
			Node newNode = new Node(data);
			
			previous.setNext(newNode);
			newNode.setNext(current);
		}
		
	}

	@Override
	public int size() { //checked
		return this.size;
	}

	
	//removeLast (needed for delete)
	public void removeLast() { //checked
		if (size == 0) {
			// do nothing
		} else if (size == 1) {
			head = tail = null;
			size = 0;
		} else {
			Node current = head;
			for (int i=0; i<size-2; i++) {
				current = current.getNext();
			}
			tail = current;
			tail.setNext(null);
			size--;
		}
	}

	//removeLast (needed for delete)
	public void removeFirst() { //checked
		if (size == 0) {
			// do nothing
		} else {
			this.head = head.getNext();
			size--;
			if (head == null) {
				tail = null; //which means the list is empty
			}
		}
	}
	/**
	 * Remove an item at an index in the linked list
	 * If the index is 0, then it calls the RemoveFirst method
	 * If the index is equal to size-1 then it calls the RemoveLast method
	 * otherwise it traverses down the linkedlist and then deletes at the index specified
	 * @author Madhu
	 * @param index
	 */
	
	@Override
	public void delete(int index) throws IndexOutOfBoundsException { //checked
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			removeFirst();
		} else if (index == size-1) {
			removeLast();
		} else {
			Node previous = this.head;
			
			for (int i=1; i<index; i++) {
				previous = previous.getNext();
			}
			Node current = previous.getNext();
			previous.setNext(current.getNext());
			size--;
		}
	}
	/**
	 * Retrieve an item at a certain index in the linked list
	 * If the index is 0, then it return the head's element
	 * If the index is equal to size-1 then it returns the tail's element
	 * otherwise it traverses down the linkedlist and returns (retreives) the element at the index specified
	 * @author Madhu
	 * @param index
	 */

	@Override
	public Object retrieve(int index) throws IndexOutOfBoundsException { //checked
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			return this.head.getElement();
		} else if (index == size-1) {
			return this.tail.getElement();
		} else {
			Node previous = this.head;
			
			for (int i=0; i<index; i++) {
				previous = previous.getNext();
			}
			return previous.getElement();
		}
	}
	/**
	 * Get the index of the node where 'data' exists in the linked list
	 * @author Madhu
	 * @param data
	 */

	@Override
	public int indexOf(Object data) { //checked
		Node previous = this.head;
		int index = -1;
		
		for (int i=0; i<this.size; i++) {
			if (previous.getElement() == data) {
				index = i;
				break;
			} else {
				previous = previous.getNext();
			}
		}
		return index;
	}
	/**
	 * Check if the linked list contains a specific item
	 * @author Madhu
	 * @param data
	 */

	@Override
	public boolean contains(Object data) { //checked
		int Index = indexOf(data);
		if (Index == -1) {
			return false;
		} else {
			return true;
		}
	}

}
