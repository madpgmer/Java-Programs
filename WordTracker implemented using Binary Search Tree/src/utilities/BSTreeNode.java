package utilities;

public class BSTreeNode<T> implements Comparable<BSTreeNode<T>>{ 
	
	private T element;
	private BSTreeNode<T> left;
	private BSTreeNode<T> right;

	/**
	 * Constructs a BS Tree Node with an element, and left and right node references.
	 * 
	 * @param element of type E containing data to be stored
	 * @param left     BSTreeNode containing a reference to the left node in the list.
	 * @param right    BSTreeNode containing a reference to the right node in the list.
	 */
	
	public BSTreeNode() {
		this.element = null;
		this.left = null;
		this.right = null;
	}
	/**
	 * 
	 * @param element
	 * @param left
	 * @param right
	 */
	
	public BSTreeNode(T element, BSTreeNode<T> left, BSTreeNode<T> right) {
		this.element = element;
		this.left = left;
		this.right = right;
	}
	

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public BSTreeNode<T> getRight() {
		return right;
	}

	public void setRight(BSTreeNode<T> right) {
		this.right = right;
	}

	public BSTreeNode<T> getLeft() {
		return left;
	}

	public void setLeft(BSTreeNode<T> left) {
		this.left = left;
	}
	
	public boolean hasLeftChild() {
		if (this.getLeft() == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean hasRightChild() {
		if (this.getRight() == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean isLeaf() {
		if (this.right == null && this.left == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getNumberNodes() {
		int num = 0;  
			if (this.isLeaf()) {
				num++;
			} else {
				if (this.hasLeftChild()) {
					this.getLeft();
					int numOnLeft = getNumberNodes();
					num = num + numOnLeft;
				}				
				if (this.hasRightChild()) {
					this.getRight();
					int numOnRight = getNumberNodes();
					num = num + numOnRight;
				}
			}
		return num;
	}
	
	public int getheight() {
        int leftHeight = 0;
        int rightHeight = 0;
        
        if (this.hasLeftChild()) {
        	leftHeight = this.getLeft().getheight();
        }
		
		if (this.hasRightChild()) {
			rightHeight = this.getRight().getheight();
		}
		
    	int maxHeight = 1 + Math.max(leftHeight, rightHeight);
		return maxHeight;
    }

	@Override
	
	public int compareTo(BSTreeNode<T> o) {
		
		return 0;
	}

	public boolean contains(T entry) {
		if (this.getElement() == entry) {
	        return true;
	    }

	    boolean contains = false;
	    if (this.hasLeftChild()) {
	        contains = (this.getLeft()).contains(entry);
	    }
	    if (!contains && this.hasRightChild()) {
	        contains = (this.getRight()).contains(entry);
	    }

	    return contains;
	}

}
