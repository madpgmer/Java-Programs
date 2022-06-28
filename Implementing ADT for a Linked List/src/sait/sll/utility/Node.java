/**
 * This class represents a Node in the Linked List
 * 
 * @author Madhu Madhavan
 * @version 7/28/2021
 * 
 */

package sait.sll.utility;

import java.io.Serializable;

public class Node implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Object element;
	private Node successor;
	
	public Node(Object element, Node successor) {
		super();
		this.element = element;
		this.successor = successor;
	}
	
	public Node(Object element) {
		super();
		this.element = element;
		this.successor = null;
	}

	public Object getElement() {
		return element;
	}

	public void setElement(Object element) {
		this.element = element;
	}

	public Node getNext() {
		return successor;
	}

	public void setNext(Node successor) {
		this.successor = successor;
	}

	
}
