package test;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import adt.Iterator;
import exceptions.TreeException;
import utilities.BSTree;

/**
 * 
 * @author Madhu
 *
 */
public class BSTreeTests {

	// test objects
	private BSTree<Integer> myTree;
	private Integer zero;
	private Integer one;
	private Integer two;
	private Integer three;
	private Integer four;
	private Integer five;
	private Integer six;
	private Integer seven;
	private Integer eight;
	private Integer nine;
	private Integer ten;
	
	@Before
	public void setUp() throws Exception {
		myTree = new BSTree<Integer>();
		zero = 0;
		one = 1;
		two = 2;
		three = 3;
		four = 4;
		five = 5;
		six = 6;
		seven = 7;
		eight = 8;
		nine = 9;
		ten = 10;
	}

	@After
	public void tearDown() throws Exception {
		myTree = null;
		zero = null;
		one = null;
		two = null;
		three = null;
		four = null;
		five = null;
		six = null;
		seven = null;
		eight = null;
		nine = null;
		ten = null;
	}

	@Test
	public final void testBSTree() {
		boolean expected = true;
		boolean actual;
		if (myTree == null) {
			actual = false;
		} else {
			actual = true;
		}	
		assertEquals("The BSTree is not created", expected, actual); 
	}

	@Test
	public final void testBSTreeGetRoot() throws TreeException{		
		myTree.add(five);
		myTree.add(three);
		myTree.add(seven);
		myTree.add(one);
		myTree.add(four);
		myTree.add(six);
		myTree.add(eight);
		
		int expectedHeight = 3;
		int actualHeight = myTree.getHeight();
		int expectedSize = 7;
		int actualSize = myTree.size();
		int expectedRoot = 5;
		int actualRoot = myTree.getRoot().getElement();
		
		assertEquals("Tree root is not correct", expectedRoot, actualRoot);
		assertEquals("Tree height is not correct", expectedHeight, actualHeight);
		assertEquals("Tree size is not correct", expectedSize, actualSize);
	}
	
	@Test
	public final void testBSTreeGetRootTreeException(){		
		//starting a blank tree that has no nodes
		try {
			myTree.getRoot().getElement();
			fail("The tree is still null, so getting root did not throw a TreeException exception");
		} catch (TreeException e) {
			assertTrue(true);
		}	
	}
	
	@Test
	public final void testBSTreeHeightAndSizeOnSide() {		
		myTree.add(one);
		myTree.add(two);
		myTree.add(three);
		
		int expectedHeight = 3;
		int actualHeight = myTree.getHeight();
		int expectedSize = 3;
		int actualSize = myTree.size();
		
		assertEquals("Tree height is not correct", expectedHeight, actualHeight);
		assertEquals("Tree size is not correct", expectedSize, actualSize);
	}
	
	@Test
	public final void testBSTreeHeightAndSizeOnBothSides() {		
		myTree.add(five);
		myTree.add(three);
		myTree.add(seven);
		myTree.add(one);
		myTree.add(four);
		myTree.add(six);
		myTree.add(eight);
		
		int expectedHeight = 3;
		int actualHeight = myTree.getHeight();
		int expectedSize = 7;
		int actualSize = myTree.size();
		
		assertEquals("Tree height is not correct", expectedHeight, actualHeight);
		assertEquals("Tree size is not correct", expectedSize, actualSize);
	}

	@Test
	public final void testBSTreeClear() {		
		myTree.add(five);
		myTree.add(three);
		myTree.add(seven);
		myTree.add(one);
		myTree.clear();
		
		int expectedHeight = 0;
		int actualHeight = myTree.getHeight();
		int expectedSize = 0;
		int actualSize = myTree.size();
		
		assertEquals("Tree height is not correct", expectedHeight, actualHeight);
		assertEquals("Tree size is not correct", expectedSize, actualSize);
	}
	
	@Test
	public final void testBSTreeIsEmpty() {		
		myTree.add(five);
		myTree.add(three);
		myTree.add(seven);
		myTree.add(one);
		myTree.clear();
		
		boolean expected = true;
		boolean actual = myTree.isEmpty();
		
		assertEquals("The tree is not empty", expected, actual); 
		assertEquals("Size is incorrect", myTree.size(), 0); 	
		assertEquals("Height is incorrect", myTree.getHeight(), 0); 	
	}
	
	@Test
	public final void testBSTreeContains() throws TreeException {		
		myTree.add(five);
		myTree.add(three);
		myTree.add(seven);
		myTree.add(one);
		
		boolean expected = true;
		boolean actual = myTree.contains(seven);
		
		assertEquals("The tree contians the item", expected, actual); 
		assertEquals("Size is incorrect", myTree.size(), 4); 	
		assertEquals("Height is incorrect", myTree.getHeight(), 3); 	
	}
	
	@Test
	public final void testBSTreeSearch() throws TreeException {		
		myTree.add(five);
		myTree.add(three);
		myTree.add(seven);
		myTree.add(one);
		myTree.add(four);
		myTree.add(six);
		myTree.add(eight);
		myTree.add(nine);
		myTree.add(ten);
		myTree.add(zero);
		
		int expectedHeight = 5;
		int actualHeight = myTree.getHeight();
		int expectedSize = 10;
		int actualSize = myTree.size();
		int expectedElement = 9;
		int actualElement = myTree.search(nine).getElement();
		
		boolean expected = true;
		boolean actual = myTree.contains(nine);
		
		assertEquals("The tree search does not have the item", expected, actual); 
		assertEquals("Tree root is not correct", expectedElement, actualElement);
		assertEquals("Tree height is not correct", expectedHeight, actualHeight);
		assertEquals("Tree size is not correct", expectedSize, actualSize);
	}
	
	@Test
	public final void testBSTreeAddOnBothSides() {		
		myTree.add(five);
		myTree.add(three);
		myTree.add(seven);
		myTree.add(one);
		myTree.add(four);
		myTree.add(six);
		myTree.add(eight);
		myTree.add(nine);
		myTree.add(ten);
		myTree.add(zero);
		
		int expectedHeight = 5;
		int actualHeight = myTree.getHeight();
		int expectedSize = 10;
		int actualSize = myTree.size();
		
		assertEquals("Tree height is not correct", expectedHeight, actualHeight);
		assertEquals("Tree size is not correct", expectedSize, actualSize);
	}
	
	@Test
	public final void testIteratorPreOrder() {		
		myTree.add(five);
		myTree.add(three);
		myTree.add(seven);
		myTree.add(one);
		myTree.add(four);
		myTree.add(six);
		myTree.add(eight);
		myTree.add(nine);
		myTree.add(ten);
		myTree.add(zero);
		myTree.add(two);
		
		Iterator<Integer> it = myTree.preorderIterator();
		Integer value = null;
		Integer expected = 10;
		
		while (it.hasNext()) {
			value = it.next();
		}
		assertEquals("Iterator returned incorrect value", value, expected); 
		assertEquals("Size is incorrect", myTree.size(), 11); 	
	}
	
	@Test
	public final void testIteratorPostOrder() {		
		myTree.add(five);
		myTree.add(three);
		myTree.add(seven);
		myTree.add(one);
		myTree.add(four);
		myTree.add(six);
		myTree.add(eight);
		myTree.add(nine);
		myTree.add(ten);
		myTree.add(zero);
		myTree.add(two);
		
		Iterator<Integer> it = myTree.postorderIterator();
		Integer value = null;
		Integer expected = 5;
		
		while (it.hasNext()) {
			value = it.next();
		}
		assertEquals("Iterator returned incorrect value", value, expected); 
		assertEquals("Size is incorrect", myTree.size(), 11); 	
	}
	
	@Test
	public final void testIteratorInOrder() {		
		myTree.add(five);
		myTree.add(three);
		myTree.add(seven);
		myTree.add(one);
		myTree.add(four);
		myTree.add(six);
		myTree.add(eight);
		myTree.add(nine);
		myTree.add(ten);
		myTree.add(zero);
		myTree.add(two);
		
		Iterator<Integer> it = myTree.inorderIterator();
		Integer value = null;
		Integer expected = 10;
		
		while (it.hasNext()) {
			value = it.next();
			System.out.print(value + " ");
		}
		assertEquals("Iterator returned incorrect value", value, expected); 
		assertEquals("Size is incorrect", myTree.size(), 11); 	
	}
}
