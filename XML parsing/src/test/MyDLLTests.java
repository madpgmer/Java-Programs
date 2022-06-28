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
import utilities.MyArrayList;
import utilities.MyDLL;

public class MyDLLTests {

	// test objects
	private static MyDLL<String> myList;
	private String zero;
	private String one;
	private String two;
	private String three;
	private String four;
	private String five;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		myList = new MyDLL<String>();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		myList = null;
	}
	
	@Before
	public void setUp() throws Exception {
		myList = new MyDLL<String>();
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
	public final void testArrayList() {
		boolean expected = true;
		boolean actual;
		if (myList == null) {
			actual = false;
		} else {
			actual = true;
		}	
		assertEquals("The ArrayList is not created", expected, actual); 
	}

	@Test
	public final void testAddStringTToIndex() {
		myList.add(0, zero);
		myList.add(1, one);
		myList.add(2, two);
		myList.add(3, three);
		
		boolean expected = true;
		boolean actual = myList.add(1, four);
		String expectedString = "Four";
		String actualString = (myList.get(1)).toString();
		String expectedString2 = "Two";
		String actualString2 = (myList.get(3)).toString();
		
		assertEquals("Item not added to the correct place", expected, actual); 
		assertEquals("Item added was not correct", expectedString, actualString); 
		assertEquals("Item added was not correct", expectedString2, actualString2); 
		assertEquals("Size is incorrect", myList.size(), 5); 
	}
	
	@Test
	public final void testAddStringTToIndexIndexOutOfBoundsException() {
		myList.add(0, zero);
		myList.add(1, one);
		myList.add(2, two);
		
		try {
			myList.add(5, five);
			fail("Adding another item did not throw an indexOutOfBounds exception");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public final void testAddStringTToIndexNullPointerException() {
		try {
			myList.add(0, zero);
			myList.add(1, one);
			myList.add(2, two);
			
			myList.add(1, null);
			fail("Adding a null item did not throw a nullpointer exception");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	@Test
	public final void testAddStringT() {
		myList.add(0, zero);
		myList.add(1, one);
		myList.add(2, two);
		
		boolean expected = true;
		boolean actual = myList.add(three);
		String expectedString = "Three";
		String actualString = (myList.get(3)).toString();
		
		assertEquals("Item not added to the correct place", expected, actual); 
		assertEquals("Item added was not correct", expectedString, actualString); 
		assertEquals("Size is incorrect", myList.size(), 4); 
	}
	
	@Test
	public final void testAddStringTNullPointerException() {
		try {
			myList.add(0, one);
			myList.add(1, null);
			fail("Adding a null item did not throw a nullpointer exception");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public final void testSize() {
		myList.add(0, zero);
		myList.add(1, one);
		myList.add(2, two);
		
		int expectedSize = 3;
		int actualSize = myList.size();
		
		assertEquals("Item size is not correct", expectedSize, actualSize);
	}

	@Test
	public final void testClear() {
		myList.add(0, zero);
		myList.add(1, one);
		myList.add(2, two);
		myList.clear();
		
		int expectedSize = 0;
		int actualSize = myList.size();
		
		assertEquals("List is not cleared", expectedSize, actualSize);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test 
	public final void testAddAll() {
		myList.add(0, zero);
		myList.add(1, one);
		myList.add(2, two);
		
		MyDLL myOtherList = new MyDLL<String>();
		myOtherList.add(0, three);
		myOtherList.add(1, four);
		
		myList.addAll(myOtherList);

		String expectedString = "Three";
		String actualString = (myList.get(3)).toString();
		
		assertEquals("Item added was not correct", expectedString, actualString); 
		assertEquals("Size is incorrect", myList.size(), 5); 		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test 
	public final void testAddAllNullPointerException() {
		myList.add(0, zero);
		myList.add(1, one);
		myList.add(2, two);
		
		MyDLL myOtherList = null;
		
		try {
			myList.addAll(myOtherList);
			fail("Adding a null item did not throw a nullpointer exception");
		} catch (NullPointerException e) {
			assertTrue(true);
		}		
	}
	
	@Test
	public final void testGet() {
		myList.add(0, zero);
		myList.add(1, one);
		myList.add(2, two);

		String expectedString = "Two";
		String actualString = (myList.get(2)).toString();
		
		assertEquals("Item added was not correct", expectedString, actualString); 
		assertEquals("Size is incorrect", myList.size(), 3); 	
	}

	@Test
	public final void testGetIndexOutOfBounds() {
		myList.add(0, zero);
		myList.add(1, one);
		myList.add(2, two);

		try {
			myList.get(4);
			fail("Getting a value from outside the arraylist did not throw an indexoutofbounds exception");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}		
	}
	
	@Test
	public final void testRemoveItemfromIndex() {
		myList.add(0, zero);
		myList.add(1, one);
		myList.add(2, two);
		myList.add(3, three);

		String expectedString = "One";
		String actualString = myList.remove(1).toString();
		
		assertEquals("Item removed was not correct", expectedString, actualString); 
		assertEquals("Size is incorrect", myList.size(), 3); 			
	}

	@Test
	public final void testRemoveItemfromIndexIndexOutOfBounds() {
		myList.add(0, zero);
		myList.add(1, one);

		try {
			myList.remove(2).toString();
			fail("Removing an item from arraylist did not throw an indexoutofbounds exception");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}		
	}
	
	@Test
	public final void testRemoveT() {
		myList.add(0, zero);
		myList.add(1, one);
		myList.add(2, two);
		myList.add(3, three);

		String expectedString = "Two";
		String actualString = myList.remove("Two").toString();
		
		assertEquals("Item removed was not correct", expectedString, actualString); 
		assertEquals("Size is incorrect", myList.size(), 3); 			
	}

	@Test
	public final void testRemoveTNullPointerException() {
		myList.add(0, zero);
		myList.add(1, one);

		try {
			myList.remove("Two").toString();
			fail("Removing an item from arraylist did not throw an indexoutofbounds exception");
		} catch (NullPointerException e) {
			assertTrue(true);
		}		
	}
	
	@Test
	public final void testSet() {
		myList.add(0, zero);
		myList.add(1, one);
		myList.add(2, two);
		myList.add(3, three);

		String expectedString = "Three";
		String actualString = myList.set(3, "Five");
		String expectedAddedString = "Five";
		String actualAddedString = myList.get(3);
		String expectedAddedString2 = "Zero";
		String actualAddedString2 = myList.get(0);
		String expectedAddedString3 = "Two";
		String actualAddedString3 = myList.get(2);
		
		assertEquals("Item swapped out was not correct", expectedString, actualString); 
		assertEquals("Item swapped out was not correct", expectedAddedString, actualAddedString); 
		assertEquals("Item swapped out was not correct", expectedAddedString2, actualAddedString2);
		assertEquals("Item swapped out was not correct", expectedAddedString3, actualAddedString3);
		assertEquals("Size is incorrect", myList.size(), 4); 	
	}

	@Test
	public final void testSetIndexIndexOutOfBounds() {
		myList.add(0, zero);
		myList.add(1, one);
		myList.add(2, two);
		myList.add(3, three);

		try {
			myList.set(4, "Four");
			fail("Setting an item (swapping) it out in the arraylist did not throw an indexoutofbounds exception");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}		
	}
	
	@Test
	public final void testSetNullPointerException() {
		myList.add(0, zero);
		myList.add(1, one);
		myList.add(2, two);
		myList.add(3, three);

		try {
			myList.set(2, null);
			fail("Setting an item (swapping) it with a null value in the arraylist did not throw an indexoutofbounds exception");
		} catch (NullPointerException e) {
			assertTrue(true);
		}		
	}
	
	@Test
	public final void testIsEmpty() {
		myList.add(0, zero);
		myList.add(1, one);
		myList.add(2, two);
		myList.clear();
		
		boolean expected = true;
		boolean actual = myList.isEmpty();
		
		assertEquals("The list is not empty", expected, actual); 
		assertEquals("Size is incorrect", myList.size(), 0); 		
	}

	@Test
	public final void testContains() {
		myList.add(0, zero);
		myList.add(1, one);
		myList.add(2, two);
		
		boolean expected = true;
		boolean actual = myList.contains("One");
		
		assertEquals("The list does not contain the item already there", expected, actual); 
		assertEquals("Size is incorrect", myList.size(), 3); 		
	}

	@Test
	public final void testContainsNullPointerException() {
		myList.add(0, zero);
		myList.add(1, one);
		myList.add(2, two);

		try {
			myList.contains(null);
			fail("Checking if the arraylist contains a null value in the arraylist did not throw an indexoutofbounds exception");
		} catch (NullPointerException e) {
			assertTrue(true);
		}		
	}
	
	@Test
	public final void testToArrayTArray() {
		myList.add(0, zero);
		myList.add(1, one);
		myList.add(2, two);
		myList.add(3, three);

		String[] testArray = new String[myList.size() - 2];//reducing the size of the array 
		testArray[0] = four;
		testArray[1] = five;
		Object[] testArray2 = myList.toArray(testArray);
		
		String expectedString = testArray2[0].toString();
		String actualString = myList.get(0);
		String expectedAddedString = "Two";
		String actualAddedString = testArray2[2].toString();
		
		assertEquals("ArrayList converted to [] Array was not correct", expectedString, actualString); 
		assertEquals("ArrayList converted to [] Array was not correct", expectedAddedString, actualAddedString); 
		assertEquals("Size is incorrect", testArray2.length, 4); 	
	}
	
	@Test
	public final void testToArrayTArrayNullPointerException() {
		myList.add(0, zero);
		myList.add(1, one);
		myList.add(2, two);
		myList.add(3, three);

		try {
			String[] testArray = null;
			testArray = myList.toArray(testArray);
			fail("DLL converted to NULL [] Array was not correct");
		} catch (NullPointerException e) {
			assertTrue(true);
		}		
	}

	@Test
	public final void testToArray() {
		myList.add(0, zero);
		myList.add(1, one);
		myList.add(2, two);
		myList.add(3, three);

		Object[] testArray = myList.toArray();
		
		String expectedString = String.valueOf(testArray[0]);
		String actualString = myList.get(0);
		String expectedAddedString = "Two";
		String actualAddedString = String.valueOf(testArray[2]);
		
		assertEquals("ArrayList converted to [] Array was not correct", expectedString, actualString); 
		assertEquals("ArrayList converted to [] Array was not correct", expectedAddedString, actualAddedString); 
		assertEquals("Size is incorrect", myList.size(), 4); 	
	}	
	
	@Test
	public final void testIteratorHasNextAndNext() {
		myList.add(0, zero);
		myList.add(1, one);
		myList.add(2, two);
		myList.add(3, three);
		myList.add(4, four);
		myList.add(5, five);
		
		Iterator<String> it = myList.iterator();
		String value = null;
		String expected = "Five";
		
		while (it.hasNext()) {
			value = it.next();
		}
		assertEquals("Iterator returnd incorrect value", value, expected); 
		assertEquals("Size is incorrect", myList.size(), 6); 	
	}


}
