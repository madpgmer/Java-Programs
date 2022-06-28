/**
 * @author Madhu
 * 
 *  JUnit test functions using TDD
 * 
 */


package test;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import adt.Iterator;
import utilities.MyDLL;
import utilities.MyStack;

public class MyStackTests {

	// test objects
	private static MyStack<String> myStack;
	private String zero;
	private String one;
	private String two;
	private String three;
	private String four;
	private String five;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		myStack = new MyStack<String>();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		myStack = null;
	}
	
	@Before
	public void setUp() throws Exception {
		myStack = new MyStack<String>();
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
	public final void testMyStack() {
		boolean expected = true;
		boolean actual;
		if (myStack == null) {
			actual = false;
		} else {
			actual = true;
		}	
		assertEquals("The Stack is not created", expected, actual); 
	}

	@Test
	public final void testPush() {
		myStack.push(zero);
		myStack.push(one);
		myStack.push(two);
		
		String expectedString = "Two";
		String actualString = myStack.peek().toString();
		
		assertEquals("Item added was not correct", expectedString, actualString); 
		assertEquals("Size is incorrect", myStack.size(), 3); 
	}

	@Test
	public final void testPushNullPointerException() {
		myStack.push(zero);
		myStack.push(one);
		myStack.push(two);
		
		try {
			myStack.push(null);
			fail("Adding a null item did not throw an nullpointer exception");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public final void testPop() {
		myStack.push(zero);
		myStack.push(one);
		myStack.push(two);
		
		String expectedString = "Two";
		String actualString = myStack.peek();
		String expectedString2 = "Two";
		String actualString2 = myStack.pop();
		String expectedString3 = "One";
		String actualString3 = myStack.peek();

		assertEquals("Item added was not correct", expectedString, actualString); 
		assertEquals("Item added was not correct", expectedString2, actualString2); 
		assertEquals("Item added was not correct", expectedString3, actualString3); 
		assertEquals("Size is incorrect", myStack.size(), 2); 
	}
	
	@Test
	public final void testPopEmptyStackException() {
		myStack.push(zero);
		myStack.push(one);
		myStack.push(two);
		myStack.clear();
				
		try {
			myStack.pop();
			fail("Adding a null item did not throw an nullpointer exception");
		} catch (EmptyStackException e) {
			assertTrue(true);
		}
	}

	@Test
	public final void testPeek() {
		myStack.push(three);
		myStack.push(four);
		myStack.push(five);
		
		String expectedString = "Five";
		String actualString = myStack.peek();
		String expectedString2 = "Five";
		String actualString2 = myStack.pop();
		String expectedString3 = "Four";
		String actualString3 = myStack.peek();

		assertEquals("Item added was not correct", expectedString, actualString); 
		assertEquals("Item added was not correct", expectedString2, actualString2); 
		assertEquals("Item added was not correct", expectedString3, actualString3); 
		assertEquals("Size is incorrect", myStack.size(), 2); 
	}

	@Test
	public final void testPeekEmptyStackException() {
		myStack.push(zero);
		myStack.push(one);
		myStack.push(two);
		myStack.clear();
				
		try {
			myStack.peek();
			fail("Adding a null item did not throw an nullpointer exception");
		} catch (EmptyStackException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public final void testClear() {
		myStack.push(three);
		myStack.push(four);
		myStack.push(five);
		myStack.clear();
		
		int expectedSize = 0;
		int actualSize = myStack.size();
		
		assertEquals("List is not cleared", expectedSize, actualSize);
	}

	@Test
	public final void testIsEmpty() {
		myStack.push(three);
		myStack.push(four);
		myStack.push(five);
		myStack.clear();
		
		boolean expected = true;
		boolean actual = myStack.isEmpty();
		
		assertEquals("The list is not empty", expected, actual); 
		assertEquals("Size is incorrect", myStack.size(), 0);
	}

	@Test
	public final void testToArray() {
		myStack.push(zero);
		myStack.push(one);
		myStack.push(two);
		myStack.push(three);

		Object[] testArray = myStack.toArray();
		
		String expectedString = String.valueOf(testArray[0]);
		String actualString = myStack.peek();
		String expectedAddedString = "One";
		String actualAddedString = String.valueOf(testArray[2]);
		
		assertEquals("ArrayList converted to [] Array was not correct", expectedString, actualString); 
		assertEquals("ArrayList converted to [] Array was not correct", expectedAddedString, actualAddedString); 
		assertEquals("Size is incorrect", myStack.size(), 4); 
	}

	@Test
	public final void testToArrayTArray() {
		myStack.push(zero);
		myStack.push(one);
		myStack.push(two);
		myStack.push(three);

		String[] testArray = new String[myStack.size() - 2];//reducing the size of the array 
		Object[] testArray2 = myStack.toArray(testArray);
		
		String expectedString = testArray2[0].toString();
		String actualString = myStack.peek();
		String expectedAddedString = "Two";
		String actualAddedString = testArray2[1].toString();
		
		assertEquals("ArrayList converted to [] Array was not correct", expectedString, actualString); 
		assertEquals("ArrayList converted to [] Array was not correct", expectedAddedString, actualAddedString); 
		assertEquals("Size is incorrect", testArray2.length, 4); 	
	}

	@Test
	public final void testToArrayTArrayNullPointerException() {
		myStack.push(zero);
		myStack.push(one);
		myStack.push(two);
		String[] testArray = null;
				
		try {
			myStack.toArray(testArray);
			fail("Adding a null item did not throw an nullpointer exception");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public final void testContains() {
		myStack.push(zero);
		myStack.push(one);
		myStack.push(two);
		
		boolean expected = true;
		boolean actual = myStack.contains("One");
		
		assertEquals("The list does not contain the item already there", expected, actual); 
		assertEquals("Size is incorrect", myStack.size(), 3);
	}
	
	@Test
	public final void testContainsNullPointerException() {
		myStack.push(zero);
		myStack.push(one);
		myStack.push(two);

		try {
			myStack.contains(null);
			fail("Checking if the stack contains a null value in the stack did not throw an indexoutofbounds exception");
		} catch (NullPointerException e) {
			assertTrue(true);
		}		
	}

	@Test
	public final void testSearch() {
		myStack.push(zero);
		myStack.push(one);
		myStack.push(two);
		myStack.push(three);

		int expectedIndex = 1;
		int actualIndex = myStack.search("Three");
		int expectedIndex2 = 3;
		int actualIndex2 = myStack.search("One");
		
		assertEquals("ArrayList converted to [] Array was not correct", expectedIndex, actualIndex); 
		assertEquals("ArrayList converted to [] Array was not correct", expectedIndex2, actualIndex2); 
		assertEquals("Size is incorrect", myStack.size(), 4); 	
	}

	@Test
	public final void testIterator() {
		myStack.push(zero);
		myStack.push(one);
		myStack.push(two);
		myStack.push(three);
		myStack.push(four);
		myStack.push(five);
		
		Iterator<String> it = myStack.iterator();
		String value = null;
		String expected = "Five";
		
		while (it.hasNext()) {
			value = it.next();
		}
		assertEquals("Iterator returnd incorrect value", value, expected); 
		assertEquals("Size is incorrect", myStack.size(), 6); 	
	}

	@Test
	public final void testEqualsStackADTOfT() {
		myStack.push(zero);
		myStack.push(one);
		myStack.push(two);
		
		MyStack<String> that = new MyStack<String>();
		that.push(zero);
		that.push(one);
		that.push(two);
		
		boolean expected = true;
		boolean actual = myStack.equals(that);
		
		assertEquals("The list does not equal to the other list (stack)", expected, actual); 
		assertEquals("Size is incorrect", myStack.size(), 3);
	}

	@Test
	public final void testSize() {
		myStack.push(zero);
		myStack.push(one);
		myStack.push(two);
		myStack.push(three);

		int expectedSize = 4;
		int actualSize = myStack.size();
		
		assertEquals("My Stack size was not correct", expectedSize, actualSize); 
		assertEquals("Size is incorrect", myStack.size(), 4); 
	}

}
