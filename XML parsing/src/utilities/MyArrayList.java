/**
 * 
 * @author Madhu
 *
 *         This Arraylist class is an implementation using the supplied 
 *         ListADT.java and Iterator.java interfaces supplied
 * 
 */

package utilities;

import java.util.NoSuchElementException;

import adt.Iterator;
import adt.ListADT;


public class MyArrayList<T> implements ListADT<T> {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_SIZE = 10; // default size
	private T[] internalArray;
	private int size;

	@SuppressWarnings("unchecked")
	public MyArrayList() {
		this.size = 0;
		this.internalArray = (T[]) new Object[DEFAULT_SIZE]; //cannot create generic array, so creating array of object and casting it to T
	}

	@Override
	public int size() {
		return this.size;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		this.size = 0;
		this.internalArray = (T[]) new Object[DEFAULT_SIZE]; 
	}

	
	@SuppressWarnings("unchecked")
	@Override 
	/**
	 * 
	 * @author Madhu
	 */
	public boolean add(int index, T toAdd) throws NullPointerException, IndexOutOfBoundsException {
		if (toAdd != null) {
			if (index <= size) {
				int newSize = size + 1; //array is one bigger than before
				T[] biggerArray = null;
				
				if (newSize >= this.internalArray.length) {
					// create a larger array 
					biggerArray = (T[])new Object[newSize + DEFAULT_SIZE];
				} else {
					// create an array of the new size (+1) 
					biggerArray = (T[])new Object[this.internalArray.length];
				}
			         
		         //copy elements over from old array to new array
		         for (int i = 0; i < newSize; ++i) {
		        	 if (i < index) {
			        	 biggerArray[i] = this.internalArray[i];
		        	 } else if (i > index) {
		        		 biggerArray[i] = this.internalArray[i-1]; // ie the 5th element of the old array to the 6th element of the new array
		        	 } else { // this means that i=index, here add the new element
		        		 biggerArray[i] = toAdd;
		        	 } 
		         }
			         
		        this.internalArray = biggerArray;				
				++size;
				return true;
			} else {
				throw new IndexOutOfBoundsException("The ArrayList is smaller than the index specified!");
			}
		} else {
			throw new NullPointerException("The element to add is null!");
		}
	}

	/**
	 * @author Madhu
	 */
	@Override
	public boolean add(T toAdd) throws NullPointerException {
		if (toAdd != null) {
			if (size >= this.internalArray.length) {
		         // create a larger array and copy elements over
		         @SuppressWarnings("unchecked")
				T[] biggerArray = (T[])new Object[size + DEFAULT_SIZE];
		         for (int i = 0; i < size; i++) {
		        	 biggerArray[i] = this.internalArray[i];
		         }
		         this.internalArray = biggerArray;
		     }
			this.internalArray[size] = toAdd;
			++size;
			return true;
		} else {
			throw new NullPointerException("The element to add is null!");
		}
	}

	/**
	 * @author Madhu
	 */
	@Override 
	public boolean addAll(ListADT<? extends T> toAdd) throws NullPointerException {
		if (!toAdd.equals(null)) {
			int totalSize = this.size + toAdd.size(); //add the toAdd size to current size
			
			if (totalSize >= this.internalArray.length) {
		         // create a larger array and copy elements over
		         @SuppressWarnings("unchecked")
				T[] biggerArray = (T[])new Object[totalSize];
		         for (int i = 0; i < totalSize; ++i) {
		        	 if (i < this.size) {
			        	 biggerArray[i] = this.internalArray[i];
		        	 } else { //(i >= this.size)
		        		 biggerArray[i] = toAdd.get(i - size); 
		        	 }		        	 
		         }
		         this.internalArray = biggerArray;
			} else {
				for (int i = 0; i < toAdd.size(); ++i) {
					this.internalArray[size + i] = toAdd.get(i);
		         }
			}
			this.size = totalSize;
		} else {
			throw new NullPointerException("Cannot addAll elements as one of the elements contains null!");
		}
		return true;
	}

	/**
	 * @author Madhu
	 */
	@Override
	public T get(int index) throws IndexOutOfBoundsException {
		if (index < size) {
			T item = this.internalArray[index];
			return item;
		} else {
			throw new IndexOutOfBoundsException("Index is larger than current arraylist size!");
		}
	}
	
	/**
	 * @author Madhu
	 */

	@Override 
	public T remove(int index) throws IndexOutOfBoundsException {
		if (size >= this.internalArray.length) {
	         // create a larger array and copy elements over
	         @SuppressWarnings("unchecked")
			T[] biggerArray = (T[])new Object[size + DEFAULT_SIZE];
	         for (int i = 0; i < size; i++) {
	        	 biggerArray[i] = this.internalArray[i];
	         }
	         this.internalArray = biggerArray;
	     }
		if (index < size) {
			T item = this.internalArray[index];
			
			//copy elements over from rest of the array
	         for (int i = index; i < size; ++i) {
	        	 this.internalArray[i] = this.internalArray[i+1];
	         }
			size--;
			
			return item;
		} else {
			throw new IndexOutOfBoundsException("Index is larger than current arraylist size!");
		}
	}
	
	/**
	 * @author Madhu
	 */

	@Override 
	public T remove(T toRemove) throws NullPointerException {
		int indexOfItem = size; //this means its not in the array
		for (int i = 0; i < size; ++i) {
			T item = this.internalArray[i];
			if (item.equals(toRemove)) {
				indexOfItem = i; //that means the item toTemove is there within the array
				break;
			}
		}
		if (indexOfItem < size) { //found it
			size--;
			return this.internalArray[indexOfItem];
		} else { //not found it
			throw new NullPointerException("The arraylist does not contain the item!");
		}
	}
	
	/**
	 * @author Madhu
	 */

	@Override 
	public T set(int index, T toChange) throws NullPointerException, IndexOutOfBoundsException {
		if (toChange != null) {
			if (index < size) {
				T item = this.internalArray[index];
				this.internalArray[index] = toChange;
				return item;
			} else {
				throw new IndexOutOfBoundsException("The ArrayList is smaller than the index specified!");
			}
		} else {
			throw new NullPointerException("The element to add is null!");
		}
	}

	/**
	 * @author Madhu
	 */
	
	@Override  
	public boolean isEmpty() {
		if (this.size == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @author Madhu
	 */

	@Override
	public boolean contains(T toFind) throws NullPointerException {
		boolean foundIt = false;
		
		for (int i = 0; i < size; ++i) {
			T item = this.internalArray[i];
			if (item.equals(toFind)) {
				foundIt = true; //that means the item toFind is there within the array
				break;
			}
		}
		
		if (foundIt) {
			return foundIt;
		} else {
			throw new NullPointerException("The element to find is null!");
		}
	}

	/**
	 * @author Madhu
	 */
	
	@SuppressWarnings("unchecked")
	@Override 
	public T[] toArray(T[] toHold) throws NullPointerException {
		if (!toHold.equals(null)) {
			if (toHold.length < this.size) { //then create a new array of length size
				T[] biggerArray = (T[])new Object[this.size];
		        for (int i = 0; i < size; ++i) {
		        	biggerArray[i] = this.internalArray[i];
		        }
		        return biggerArray;
			} else { //otherwise copy the elements to the array
				for (int i = 0; i < size; ++i) {
					toHold[i] = this.internalArray[i];
				}
				return toHold;
			}
		} else {
			throw new NullPointerException("Cannot toArray elements as one of the elements of the array contains null!");
		}
	}

	/**
	 * @author Madhu
	 */
	
	@Override 
	public Object[] toArray() {
		Object[] biggerArray = new Object[this.size];
        for (int i = 0; i < size; ++i) {
        	biggerArray[i] = (Object)this.internalArray[i];
        }
        return biggerArray;
	}
	
//	//implement iterator methods
	@Override
	public Iterator<T> iterator() {
		MyIterator<T> iterator = new MyIterator<T>(this.internalArray);
		return iterator;
	}
	
	@SuppressWarnings("hiding")
	private class MyIterator<T> implements Iterator<T> {

		private T[] list;
		int iteratorIndex;
		
		public MyIterator(T[] input) {
			this.list = input;
			this.iteratorIndex = 0;
		}
		
		@Override
		public boolean hasNext() {
			if (iteratorIndex < this.list.length) {
				if (this.list.length > 0) {
					return (list[iteratorIndex] != null);
				} else return false;
			} else return false;		
		}

		@Override
		public T next() throws NoSuchElementException {
			if (list[iteratorIndex] != null) 
				return (list[iteratorIndex++]);
			else 
				throw new NoSuchElementException("No more elements available");
		}
	}

}
