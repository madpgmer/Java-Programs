package utilities;



import java.util.ArrayList;

import adt.BSTreeADT;
import exceptions.TreeException;

/**
 * 
 * @author Madhu
 *
 * @param <T>
 */

public class BSTree<T extends Comparable<? super T>> implements BSTreeADT<T>{

	private static final long serialVersionUID = 1L;
	private BSTreeNode<T> root;
	
	// Constructs an empty BST.
	public BSTree() {
		this.root = null;
	}
	
	public BSTree(T element) {
		this.root = new BSTreeNode<T>(element, null, null);
	}
	
	@Override
	public BSTreeNode<T> getRoot() throws TreeException {
		if (root != null)
			return root;
		else 
			throw new TreeException("The tree is null!");
	}

	@Override
	public int getHeight() {
		if (root != null)
			return root.getheight();
		else 
			return 0;
	}
	
	@Override
	public int size() {
		return size(root);
	}

	public int size(BSTreeNode<T> that) {
		if (that == null)
            return 0;
        else
            return(size(that.getLeft()) + 1 + size(that.getRight()));
	}
	
	@Override
	public boolean isEmpty() {
		if (root == null) return true;
		else return false;
	}

	@Override
	public void clear() {
		root = null;
	}
	
	@Override
	public boolean contains(T entry) throws TreeException {
		BSTreeNode<T> current = this.root;
		while(current != null) {
			if(entry.compareTo(current.getElement())==0) {
				return true;
			}else {
				if(entry.compareTo(current.getElement())>0) {
					current=current.getRight();
				}
				else {
					current=current.getLeft();
				}
			}
		}
		return false;
	}

	@Override
	public BSTreeNode<T> search(T entry) throws TreeException {
		BSTreeNode<T> current = this.root;
		while(current != null) {
			if(entry.compareTo(current.getElement())==0) {
				return current;
			}else {
				if(entry.compareTo(current.getElement())>0) {
					current=current.getRight();
				}
				else {
					current=current.getLeft();
				}
			}
		}
		return null;
	}

	@Override
	public boolean add(T newEntry) throws NullPointerException {
		if (newEntry != null) {
			root = insert(root, newEntry);
			return true;
		} else {
			throw new NullPointerException(" Item to add is Null!");
		}
	}

	public BSTreeNode<T> insert(BSTreeNode<T> root, T newItem) { 
      if (root == null) { //If tree is empty
          root = new BSTreeNode<T>(newItem, null, null);
          return root; 
      } 
      //otherwise find the correct spot to insert
      if (newItem.compareTo(root.getElement()) <= 0)     //insert in the left subtree
          root.setLeft(insert(root.getLeft(), newItem)); 
      else if (newItem.compareTo(root.getElement()) > 0) //insert in the right subtree
          root.setRight(insert(root.getRight(), newItem)); 
       
      return root; 
  } 
	
	@Override
	public Iterator<T> inorderIterator() {
		return new Iterator<T>("inorder", this.root);
	}

	@Override
	public Iterator<T> preorderIterator() {
		return new Iterator<T>("preorder", this.root);
	}

	@Override
	public Iterator<T> postorderIterator() {
		return new Iterator<T>("postorder", this.root);
	}

	@SuppressWarnings("hiding")
	private class Iterator<T> implements adt.Iterator<T>{
		private ArrayList<T> lOfElements = new ArrayList<T>();
		//private Stack<E> stack = new Stack<E>();
		@SuppressWarnings("rawtypes")
		private BSTreeNode itRoot;

		@SuppressWarnings("rawtypes")
		public Iterator(String order, BSTreeNode root){
			this.itRoot = root;
			
			if(order.equals("inorder")) {
				this.inOrder(this.itRoot);
			}
			else if(order.equals("preorder")) {
				this.preOrder(this.itRoot);
			}
			else {
				this.postOrder(this.itRoot);
			}
		}

		//pre-order traversal
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private void preOrder(BSTreeNode current) {
			if(current !=null) {
				lOfElements.add((T) current.getElement());
				
				if(current.getLeft()!=null) {
					preOrder(current.getLeft());
				}
				
				if(current.getRight()!=null) {
					preOrder(current.getRight());
				}
			}
		}

		//post-order traversal
		@SuppressWarnings({ "rawtypes", "unchecked" })
		private void postOrder(BSTreeNode current) {
			if(current !=null) {
				if(current.getLeft()!=null) {
					postOrder(current.getLeft());
				}
				
				if(current.getRight()!=null) {
					postOrder(current.getRight());
				}
				
				lOfElements.add((T) current.getElement());
			}
		}
		
		//In-order traversal
		@SuppressWarnings({ "rawtypes", "unchecked" })
		private void inOrder(BSTreeNode current) {
			if(current !=null) {
				if(current.getLeft()!=null) {
					inOrder(current.getLeft());
				}
				
				lOfElements.add((T) current.getElement());
				
				if(current.getRight()!=null) {
					inOrder(current.getRight());
				}
			}
		}
		
		@Override
		public boolean hasNext(){
			if (lOfElements.isEmpty()) {
				return false;
			}
			else {
				return true;
			}
		}

		@Override
		public T next(){
			T item = lOfElements.get(0);
			lOfElements.remove(0);
			return item;
		}
	}
	
}
