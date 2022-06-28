/**
 * @author Madhu
 * 
 *  Implementing utility class MyQueue.java using the provided QueueADT.java and Iterator.java interfaces       
 * Queue implementation uses MyDLL.java implementation as the underlying data structure and the EmptyQueueException.      
 * 
 */

package utilities;

import adt.Iterator;
import adt.QueueADT;
import exceptions.EmptyQueueException;

public class MyQueue<T> implements QueueADT<T> {
	
	private static final long serialVersionUID = 1L;
	private MyDLL<T> queueList;

	public MyQueue() {
		queueList = new MyDLL<T>();
	}

	@Override
	public void enqueue(T toAdd) throws NullPointerException {
		queueList.add(toAdd);
	}

	@Override
	public T dequeue() throws EmptyQueueException {
		if (queueList.size() != 0) {
			T itemRemoved = queueList.remove(0);
			return itemRemoved;
		} else {
			throw new EmptyQueueException("The Queue is empty");
		}
	}

	@Override
	public T peek() throws EmptyQueueException {
		if (queueList.size() != 0) {
			T item = queueList.get(0);
			return item;
		} else {
			throw new EmptyQueueException("The Queue is empty");
		}
	}

	@Override
	public void dequeueAll() {
		queueList.clear();
	}

	@Override
	public boolean isEmpty() {
		return queueList.isEmpty();
	}

	@Override
	public Object[] toArray() {
		Object[] queueArray = queueList.toArray();
		return queueArray;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray(T[] holder) throws NullPointerException {
		Object[] testArray = queueList.toArray();
		
		if (!holder.equals(null)) {
			if (holder.length < this.queueList.size()) { //then create a new array of length size
				T[] biggerArray = (T[])new Object[this.queueList.size()];
		        for (int i = 0; i < this.queueList.size(); ++i) {
		        	biggerArray[i] = (T) testArray[i];
		        }
		        return biggerArray;
			} else { //otherwise copy the elements to the array
				for (int i = 0; i < this.queueList.size(); ++i) {
					holder[i] = (T) testArray[i];
				}
				return holder;
			}
		} else {
			throw new NullPointerException("Cannot toArray elements as one of the elements of the array contains null!");
		}
	}
	
	@Override
	public Iterator<T> iterator() {
		Iterator<T> it = queueList.iterator();
		return it;
	}

	@Override
	public boolean equals(QueueADT<T> that) {//check size first then check individual elements
		if (this.queueList.size() == that.size()) {
			for (int i = 0; i < this.queueList.size(); i++) {
				try {
					if (!queueList.get(i).equals(that.dequeue())) {
						return false;
					}
				} catch (IndexOutOfBoundsException | EmptyQueueException e) {
					e.printStackTrace();
				}
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isFull() { //dont understand the point of this method - maybe to check if there are any nulls
		int counter = 0;
		for (int i = 0; i < this.queueList.size(); i++) {
			if (!queueList.get(i).equals(null)) {
				counter++;
			}
		}
		if (counter == this.queueList.size()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int size() {
		return queueList.size();
	}
	

}