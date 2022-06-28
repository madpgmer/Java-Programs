/**
 * 
 * @author Madhu
 */


package shapeObjects;

public class SquarePrism extends Prism {

	//constructor
	public SquarePrism(double height, double side) {
		super(height, side);
	}
	/**
	 * @author Madhu, Eze, 
	 */
	
	@Override
	public double calcVolume() {
		return calcBaseArea() * height; // height is from Grand Parent @Madhu
	}
	
	/**
	 * @author Eze
	 */
	@Override
	public double calcBaseArea() {
		return side * side; // Side is from Parent  @Madhu
	}

}
