/**
 * @author Madhu
 * 
 *  JUnit test functions using TDD
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import adt.Iterator;
import exceptions.EmptyQueueException;
import utilities.MyDLL;
import utilities.MyQueue;

public class MyQueueTests {

	// test objects
	private static MyQueue<String> myQueue;
	private String zero;
	private String one;
	private String two;
	private String three;
	private String four;
	private String five;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		myQueue = new MyQueue<String>();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		myQueue = null;
	}
	
	@Before
	public void setUp() throws Exception {
		myQueue = new MyQueue<String>();
		zero = "Zero";
		one = "One";
		two = "Two";
		three = "Three";
		four = "Four";
		five = "Five";
	}

	@After
	public void tearDown() throws Exception {
		zero = null;
		one = null;
		two = null;
		three = null;
		four = null;
		five = null;
	}

	@Test
	public final void testMyQueue() {
		boolean expected = true;
		boolean actual;
		if (myQueue == null) {
			actual = false;
		} else {
			actual = true;
		}	
		assertEquals("The Queue is not created", expected, actual); 
	}

	@Test
	public final void testEnqueue() throws EmptyQueueException {
		myQueue.enqueue(zero);
		myQueue.enqueue(one);
		myQueue.enqueue(two);
		
		String expectedString = "Zero";
		String actualString = myQueue.peek().toString();
		
		assertEquals("Item added was not correct", expectedString, actualString); 
		assertEquals("Size is incorrect", myQueue.size(), 3); 
	}

	@Test
	public final void testEnqueueNullPointerException() {
		try {
			myQueue.enqueue(zero);
			myQueue.enqueue(one);
			myQueue.enqueue(two);
			
			myQueue.enqueue(null);
			fail("Adding a null item did not throw a nullpointer exception");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public final void testDequeue() throws EmptyQueueException {
		myQueue.enqueue(zero);
		myQueue.enqueue(one);
		myQueue.enqueue(two);
		
		String expectedString = "Zero";
		String actualString = myQueue.dequeue().toString();
		String expectedString1 = "One";
		String actualString1 = myQueue.dequeue().toString();
		
		assertEquals("Item dequeued was not correct", expectedString, actualString); 
		assertEquals("Item dequeued was not correct", expectedString1, actualString1); 
		assertEquals("Size is incorrect", myQueue.size(), 1); 
	}

	public final void testDequeueEmptyQueueException() {
		try {
			myQueue.enqueue(zero);
			myQueue.enqueue(one);
			myQueue.enqueue(two);
			myQueue.dequeueAll();
			
			myQueue.dequeue();
			fail("Dequeueing an empty queue did not throw a nullpointer exception");
		} catch (EmptyQueueException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public final void testPeek() throws EmptyQueueException {
		myQueue.enqueue(three);
		myQueue.enqueue(four);
		myQueue.enqueue(five);
		
		String expectedString = "Three";
		String actualString = myQueue.peek();
		String expectedString2 = "Three";
		String actualString2 = myQueue.dequeue();
		String expectedString3 = "Four";
		String actualString3 = myQueue.peek();

		assertEquals("Item added was not correct", expectedString, actualString); 
		assertEquals("Item added was not correct", expectedString2, actualString2); 
		assertEquals("Item added was not correct", expectedString3, actualString3); 
		assertEquals("Size is incorrect", myQueue.size(), 2); 
	}

	public final void testPeekEmptyQueueException() {
		try {
			myQueue.enqueue(zero);
			myQueue.enqueue(one);
			myQueue.enqueue(two);
			myQueue.dequeueAll();
			
			myQueue.peek();
			fail("Peeking an empty did not throw a nullpointer exception");
		} catch (EmptyQueueException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public final void testDequeueAll() {
		myQueue.enqueue(three);
		myQueue.enqueue(four);
		myQueue.enqueue(five);
		myQueue.dequeueAll();
		
		int expectedSize = 0;
		int actualSize = myQueue.size();
		
		assertEquals("List is not cleared", expectedSize, actualSize);
	}

	@Test
	public final void testIsEmpty() {
		myQueue.enqueue(three);
		myQueue.enqueue(four);
		myQueue.enqueue(five);
		myQueue.dequeueAll();
		
		boolean expected = true;
		boolean actual = myQueue.isEmpty();
		
		assertEquals("The list is not empty", expected, actual); 
		assertEquals("Size is incorrect", myQueue.size(), 0);
	}

	@Test
	public final void testToArray() throws EmptyQueueException {
		myQueue.enqueue(zero);
		myQueue.enqueue(one);
		myQueue.enqueue(two);
		myQueue.enqueue(three);

		Object[] testArray = myQueue.toArray();
		
		String expectedString = String.valueOf(testArray[0]);
		String actualString = myQueue.peek();
		String expectedAddedString = "Two";
		String actualAddedString = String.valueOf(testArray[2]);
		
		assertEquals("DLL converted to [] Array was not correct", expectedString, actualString); 
		assertEquals("DLL converted to [] Array was not correct", expectedAddedString, actualAddedString); 
		assertEquals("Size is incorrect", myQueue.size(), 4); 
	}

	@Test
	public final void testToArrayTArray() throws EmptyQueueException {
		myQueue.enqueue(zero);
		myQueue.enqueue(one);
		myQueue.enqueue(two);
		myQueue.enqueue(three);

		String[] testArray = new String[myQueue.size() - 2];//reducing the size of the array 
		Object[] testArray2 = myQueue.toArray(testArray);
		
		String expectedString = testArray2[0].toString();
		String actualString = myQueue.peek();
		String expectedAddedString = "One";
		String actualAddedString = testArray2[1].toString();
		
		assertEquals("ArrayList converted to [] Array was not correct", expectedString, actualString); 
		assertEquals("ArrayList converted to [] Array was not correct", expectedAddedString, actualAddedString); 
		assertEquals("Size is incorrect", testArray2.length, 4); 	
	}
	
	@Test
	public final void testToArrayTArrayNullPointerException() {
		myQueue.enqueue(zero);
		myQueue.enqueue(one);
		myQueue.enqueue(two);
		myQueue.enqueue(three);

		try {
			String[] testArray = null;
			testArray = myQueue.toArray(testArray);
			fail("Queue converted to NULL [] Array was not correct");
		} catch (NullPointerException e) {
			assertTrue(true);
		}		
	}
	
	@Test
	public final void testIterator() {
		myQueue.enqueue(zero);
		myQueue.enqueue(one);
		myQueue.enqueue(two);
		myQueue.enqueue(three);
		myQueue.enqueue(four);
		myQueue.enqueue(five);
		
		Iterator<String> it = myQueue.iterator();
		String value = null;
		String expected = "Five";
		
		while (it.hasNext()) {
			value = it.next();
		}
		assertEquals("Iterator returnd incorrect value", value, expected); 
		assertEquals("Size is incorrect", myQueue.size(), 6); 	
	}

	@Test
	public final void testEqualsQueueADTOfT() {
		myQueue.enqueue(zero.toString());
		myQueue.enqueue(one.toString());
		myQueue.enqueue(two.toString());
		
		MyQueue<String> that = new MyQueue<String>();
		that.enqueue(zero.toString());
		that.enqueue(one.toString());
		that.enqueue(two.toString());
		
		boolean expected = true;
		boolean actual = myQueue.equals(that);
		
		assertEquals("The list does not equal to the other list (stack)", expected, actual); 
		assertEquals("Size is incorrect", myQueue.size(), 3);
	}

	@Test
	public final void testIsFull() {
		myQueue.enqueue(zero);
		myQueue.enqueue(one);
		myQueue.enqueue(two);
		myQueue.enqueue(three);
		myQueue.enqueue(four);
		myQueue.enqueue(five);
		
		boolean expected = true;
		boolean actual = myQueue.isFull();
		
		assertEquals("The list does not equal to the other list (stack)", expected, actual); 
		assertEquals("Size is incorrect", myQueue.size(), 6);
	}

	@Test
	public final void testSize() {
		myQueue.enqueue(zero);
		myQueue.enqueue(one);
		myQueue.enqueue(two);
		myQueue.enqueue(three);
		
		int expectedSize = 4;
		int actualSize = myQueue.size();
		
		assertEquals("My Queue size was not correct", expectedSize, actualSize); 
		assertEquals("Size is incorrect", myQueue.size(), 4); 
	}

}
