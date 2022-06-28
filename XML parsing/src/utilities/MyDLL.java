/**
 * @author Madhu with credit to Reeta Suman.
 * 
 *         This Doubly-linkedlist class is an implementation using the 
 *         supplied ListADT.java and Iterator.java interfaces supplied
 * 
 */

package utilities;

import java.util.NoSuchElementException;

import adt.Iterator;
import adt.ListADT;


public class MyDLL<T> implements ListADT<T> {
	private static final long serialVersionUID = 1L;
	
	private MyDLLNode<T> head;
	private MyDLLNode<T> tail;
	private int size;
	
	public MyDLL() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	@Override
	public int size() {
		return this.size;
	}
	
	@Override
	public void clear() {
		this.size = 0;
		this.head = this.tail = null; 
	}
	
	@Override
	public boolean add(int index, T toAdd) throws NullPointerException, IndexOutOfBoundsException {
		if (toAdd != null) {
			if (index <= size) {
				MyDLLNode<T> newNode = new MyDLLNode<T>(toAdd, null, null);  //Create a new node 
		        if (index == 0 || index == size) {
		        	add(toAdd);
		        } else {
		        	MyDLLNode<T> temp = head;
		        	for (int i = 0; i < index-1; i++) {
		                temp = temp.getNext();
		            }
		        	newNode.setPrev(temp);
		        	newNode.setNext(temp.getNext());
		        	temp.setNext(newNode);
		        	size++;
		        }
				return true;
			} else {
				throw new IndexOutOfBoundsException("The DLL is smaller than the index specified!");
			}
		} else {
			throw new NullPointerException("The element to add is null!");
		}
	}
	
	@Override
	public boolean add(T toAdd) throws NullPointerException {
		if (toAdd != null) {
			MyDLLNode<T> newNode = new MyDLLNode<T>(toAdd, null, null);   //Create a new node 
	          
	        if(size == 0) {   //if the list size is 0 head and tail points to newNode
	            head = tail = newNode;  
	            head.setPrev(null); //head's previous will be null 
	            tail.setNext(null); //tail's next will also be null 
	        }  
	        else { //add newNode to the end of the list. tail->next set to newNode  
	            tail.setNext(newNode);  
	            newNode.setPrev(tail);  //newNode->previous set to tail
	            tail = newNode;  //newNode becomes new tail  
	            tail.setNext(null);  //tail's next point to null  
	        }  	
	        size++;
			return true;
		} else {
			throw new NullPointerException("The element to add is null!");
		}
	}
	
	@Override
	public boolean addAll(ListADT<? extends T> toAdd) throws NullPointerException {
		if (!toAdd.equals(null)) {
			int totalSize = this.size + toAdd.size(); //add the toAdd size to current size
			
			for (int i = 0; i < toAdd.size(); ++i) {
				MyDLLNode<T> toAddNode = new MyDLLNode<T>(toAdd.get(i), null, null);
				tail.setNext(toAddNode);
				toAddNode.setPrev(tail);
				tail = toAddNode;
				tail.setNext(null);
		    }
			size = totalSize;
			return true;
		} else {
			throw new NullPointerException("Cannot addAll elements as one of the elements contains null!");
		}
	}
	
	@Override
	public T get(int index) throws IndexOutOfBoundsException {
		if (index < size) {
			MyDLLNode<T> temp = head;
	    	for (int i = 0; i < index; i++) {
	            temp = temp.getNext();
	        }
			
	    	T item = temp.getElement();
	    	return item;
		} else {
			throw new IndexOutOfBoundsException("Index is larger than current DLL size!");
		}
	}
	
	@Override
	public T remove(int index) throws IndexOutOfBoundsException {
		if (index < size) {
			T toReturn = null;
			
			if (index == 0) { // then its the first node
		        toReturn = head.getElement();
		        if (size > 1) {
		        	head.getNext().setPrev(null);
		        	head = head.getNext();
		        } else {
		        	head = tail = null;
		        }
			} else if (index == size-1) { //then its the last node    
		        toReturn = tail.getElement();		        
		        tail.getPrev().setNext(null);
		        tail = tail.getPrev();
			} else {
				MyDLLNode<T> temp = head;
				
	        	for (int i = 0; i < index; i++) {
	        		temp = temp.getNext(); //.getNext();
	            }
	        	toReturn = temp.getElement();
	        	temp.getPrev().setNext(temp.getNext());
	        	temp.getNext().setPrev(temp.getPrev());
			}
			size--;
        	return toReturn;
		} else {
			throw new IndexOutOfBoundsException("Index is larger than current arraylist size!");
		}
	}
	@Override
	public T remove(T toRemove) throws NullPointerException {
		int indexOfItem = size;
		
		MyDLLNode<T> temp = head;
    	for (int i = 0; i < size; i++) {
    		if (temp.getElement().equals(toRemove)) {
				indexOfItem = i; //that means the item toTemove is found in the DLL
				break;
			}
    		temp = temp.getNext(); //.getNext();
        }
    	if (indexOfItem < size) { //found it
    		remove(indexOfItem);
			return temp.getElement();
		} else { //not found it
			throw new NullPointerException("The arraylist does not contain the item!");
		}
	}
	
	@Override
	public T set(int index, T toChange) throws NullPointerException, IndexOutOfBoundsException {
		if (toChange != null) {
			if (index < size) {
				T toReturn = null;
				MyDLLNode<T> toAddNode = new MyDLLNode<T>(toChange, null, null);
				if (index == 0) { // then its the first node
			        toReturn = head.getElement();
			        
			        toAddNode.setNext(head.getNext());
			        head.getNext().setPrev(toAddNode);
			        toAddNode.setPrev(null);
			        head = toAddNode;
				} else if (index == size-1) { //then its the last node    
			        toReturn = tail.getElement();		        
			        
			        tail.getPrev().setNext(toAddNode);
			        toAddNode.setPrev(tail.getPrev());
			        toAddNode.setNext(null);
			        tail = toAddNode;
				} else {
					MyDLLNode<T> temp = head;
					
		        	for (int i = 0; i < index; i++) {
		        		temp = temp.getNext(); //.getNext();
		            }
		        	toReturn = temp.getElement();
		        	
		        	temp.getPrev().setNext(toAddNode);
		        	temp.getNext().setPrev(toAddNode);
		        	toAddNode.setPrev(temp.getPrev());
		        	toAddNode.setNext(temp.getNext());
				}	
				return toReturn;
			} else {
				throw new IndexOutOfBoundsException("The DLL is smaller than the index specified!");
			}
		} else {
			throw new NullPointerException("The element to add is null!");
		}
	}
	
	@Override
	public boolean isEmpty() {
		if (this.size == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean contains(T toFind) throws NullPointerException {
		boolean foundIt = false;
		
		MyDLLNode<T> temp = head;
    	for (int i = 0; i < size; i++) {
    		if (temp.getElement().equals(toFind)) {
    			foundIt = true; //that means the item toFind is found in the DLL
				break;
			}
    		temp = temp.getNext(); //.getNext();
        }
		
		if (foundIt) {
			return foundIt;
		} else {
			throw new NullPointerException("The element to find is null!");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray(T[] toHold) throws NullPointerException {
		if (!toHold.equals(null)) {
			if (toHold.length < this.size) { //then create a new array of length size
				T[] biggerArray = (T[])new Object[this.size];
				
				MyDLLNode<T> temp = head;
		        for (int i = 0; i < size; ++i) {
		        	biggerArray[i] = temp.getElement();
		        	temp = temp.getNext();
		        }
		        return biggerArray;
			} else { //otherwise copy the elements to the array
				
				MyDLLNode<T> temp = head;
				for (int i = 0; i < size; ++i) {
					toHold[i] = temp.getElement();
					temp = temp.getNext();
				}
				return toHold;
			}
		} else {
			throw new NullPointerException("Cannot toArray elements as one of the elements of the array contains null!");
		}
	}
	
	@Override
	public Object[] toArray() {
		Object[] biggerArray = new Object[this.size];
        
		MyDLLNode<T> temp = head;
		for (int i = 0; i < size; ++i) {
        	biggerArray[i] = temp.getElement();
        	temp = temp.getNext();
        }
        return biggerArray;
	}
	
	//implement iterator methods
	@Override
	public Iterator<T> iterator() {
		MyIterator<T> iterator = new MyIterator<T>(this.head);
		return iterator;
	}

	//implement the iterator inner class
	@SuppressWarnings("hiding")
	private class MyIterator<T> implements Iterator<T> {

		private MyDLLNode<T> head;
		int iteratorIndex;
		
		public MyIterator(MyDLLNode<T> head) {
			this.head = head;
			this.iteratorIndex = 0;
		}
		
		@Override
		public boolean hasNext() {
			MyDLLNode<T> temp = head;
			if (iteratorIndex < size) {
				for (int i = 0; i < iteratorIndex; ++i) {
		        	temp = temp.getNext();
		        }
				return (temp != null);
			} 
			else return false;
		}

		@Override
		public T next() throws NoSuchElementException {
			MyDLLNode<T> temp = head;
			for (int i = 0; i < iteratorIndex; ++i) {
	        	temp = temp.getNext();
	        }
			if (temp != null){ 
				iteratorIndex++;
				return (temp.getElement());
			}
			else 
				throw new NoSuchElementException("No more elements available");
		}
	}
	

}
