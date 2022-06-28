/**
 * 
 * @author Madhu
 */


package shapeObjects;

public class Pyramid extends Shape {

	private double side; 
	
	public Pyramid(double height, double side) {
		super(height);
		this.side = side;
	}
	
	public double getSide() {
		return side;
	}

	public void setSide(double side) {
		this.side = side;
	}

	@Override
	public double calcVolume() {
		return side * side * height * ((double)1 / 3); 
	}

	@Override
	public double calcBaseArea() {
		return side * side;
	}

}


