/**
 * 
 * @author Madhu
 */

package shapeObjects;

public class Cylinder extends Shape {

	public Cylinder(double height, double radius) {
		super(height);
		this.radius = radius;
	}

	public double radius;
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	@Override
	public double calcVolume() {
		return height * radius * radius * height;
	}

	@Override
	public double calcBaseArea() {
		return PI * radius * radius;
	}

}
