/**
 * 
 * @author Madhu
 */
package shapeObjects;

public abstract class Prism extends Shape{

	public double side;

	public Prism(double height, double side) {
		super(height);
		this.side = side;
	}

	public abstract double calcVolume(); // implemented by subclasses - @Madhu
	
	public abstract double calcBaseArea(); // implemented by subclasses - @Madhu
	
	public double getSide() {
		return side;
	}

	public void setSide(double side) {
		this.side = side;
	}

}
