/**
 * 
 * @author Madhu
 */

package shapeObjects;

public class Cone extends Shape {

	public double radius;

	public Cone(double height, double radius) {
		super(height);
		this.radius = radius;
	}
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public double calcVolume() {
		return PI * radius * radius * height * ((double)1 / 3); 
	}

	@Override
	public double calcBaseArea() {
		return PI * radius * radius;
	}
	

}
