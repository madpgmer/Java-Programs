/**
 * 
 * @author Madhu
 */
package shapeObjects;

public class OctagonalPrism extends Prism {

	//constructor
	public OctagonalPrism(double height, double side) {
		super(height, side);
	}
	/**
	 * 
	 * @author Eze
	 */
	
	@Override
	public double calcBaseArea() {
	//should we return a float value - it may be better when comparing - just a thought
	return 2 * (1 + Math.sqrt(2)) * side * side;
	}

	@Override
	public double calcVolume() {
		return calcBaseArea() * height;
	}
}
