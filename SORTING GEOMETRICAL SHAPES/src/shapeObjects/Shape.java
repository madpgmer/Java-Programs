/**
 * 
 * 
 * @author Madhu
 */



package shapeObjects;

//none of the shapes classes should implement Comparator as it should be implemented as separate class with compare() implemented on their own - @Madhu
//Shape being grandparent (super) class CAN implement Comparable and implement CompareTo(which compares height) - @Madhu
public abstract class Shape implements Comparable<Shape> {  

	final double PI = 3.142; //since PI is a constant used in almost all child and grand children classes, put that in grandparent class - @Madhu
	public double height;
//	public double radius;  -- dont need this in superclass @Madhu
//	public double side; --dont need this in superclass @Madhu
 
	public Shape(double height) {
		this.height = height;
		//this.radius = radius;
		//this.side = side;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	public abstract double calcVolume(); // implemented by subclasses - @Madhu
	
	public abstract double calcBaseArea(); // implemented by subclasses - @Madhu

	public int compareTo (Shape s) {
		if (this.getHeight() > s.getHeight() ) {
			return 1;
		}
		else if (this.getHeight() < s.getHeight() ) {
			return -1;
		}
		else {
			return 0;
		}
	}
	


}
