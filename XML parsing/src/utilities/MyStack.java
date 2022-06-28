/**
 * @author Madhu
 * 
 * Implementing utility class MyStack.java using the provided StackADT.java and Iterator.java interfaces       
 * Stack implementation uses MyArrayList.java implementation as the underlying data structure and the EmptyStackException from java.util.
 */



package utilities;

import java.util.EmptyStackException;

import adt.Iterator;
import adt.StackADT;

public class MyStack<T> implements StackADT<T> {

	private static final long serialVersionUID = 1L;
	private MyArrayList<T> stackList;

	public MyStack() {
		stackList = new MyArrayList<T>();
	}

	@Override
	public void push(T toAdd) throws NullPointerException {
		stackList.add(toAdd);
	}

	@Override
	public T pop() throws EmptyStackException {
		if (stackList.size() != 0) {
			T item = stackList.remove(stackList.size()-1);
			return item;
		} else {
			throw new EmptyStackException();
		}
	}

	@Override
	public T peek() throws EmptyStackException {
		if (stackList.size() != 0) {
			T item = stackList.get(stackList.size()-1);
			return item;
		} else {
			throw new EmptyStackException();
		}
	}

	@Override
	public void clear() {
		stackList.clear();
	}

	@Override
	public boolean isEmpty() {
		return stackList.isEmpty();
	}

	@Override
	public Object[] toArray() {
		Object[] testArray = stackList.toArray();
		Object[] tArray = new Object[testArray.length];
		
		tArray = switchOrderOfArray(testArray);
		return tArray;		
	}
	
	public Object[] switchOrderOfArray(Object[] testArray) {
		Object[] tArray = new Object[testArray.length];
		//switch the order because stack is LIFO
		for (int i = 0; i < testArray.length; i++) {
			tArray[testArray.length-1-i] = testArray[i];
		}
		return tArray;		

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray(T[] holder) throws NullPointerException {
		Object[] testArray = stackList.toArray();
		testArray = switchOrderOfArray(testArray);
		
		if (!holder.equals(null)) {
			if (holder.length < this.stackList.size()) { //then create a new array of length size
				T[] biggerArray = (T[])new Object[this.stackList.size()];
		        for (int i = 0; i < this.stackList.size(); ++i) {
		        	biggerArray[i] = (T) testArray[i];
		        }
		        return biggerArray;
			} else { //otherwise copy the elements to the array
				for (int i = 0; i < this.stackList.size(); ++i) {
					holder[i] = (T) testArray[i];
				}
				return holder;
			}
		} else {
			throw new NullPointerException("Cannot toArray elements as one of the elements of the array contains null!");
		}
	}

	@Override
	public boolean contains(T toFind) throws NullPointerException {
		Iterator<T> it = stackList.iterator();
		boolean foundIt = false;
		
		while (it.hasNext()) {
			if (toFind.equals(it.next())) {
				foundIt = true;
				break;
			}
		}
		return foundIt;
	}

	@Override
	public int search(T toFind) {
		int foundIndex = -1;
		
		for (int i = 0; i < this.stackList.size(); ++i) {
			if (stackList.get(i).equals(toFind)) {
				foundIndex = stackList.size() - i; //because we are giving the number from the top
				break;
			}
		}
		return foundIndex;
	}

	@Override
	public Iterator<T> iterator() {
		Iterator<T> it = stackList.iterator();
		return it;
	}

	@Override
	public boolean equals(StackADT<T> that) { //check size first then check individual elements
		if (this.stackList.size() == that.size()) {
			for (int i = 0; i < this.stackList.size(); ++i) {
				if (!stackList.get(stackList.size() -1 - i).equals(that.pop())) {
					//stackList.size() - i - 1; //because we are giving the number from the top
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int size() {
		return stackList.size();
	}
}
	
