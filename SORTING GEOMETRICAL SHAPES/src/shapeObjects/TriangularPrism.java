/**
 * 
 * @author Madhu
 */

package shapeObjects;

public class TriangularPrism extends Prism {

	//constructor
	public TriangularPrism(double height, double side) {
		super(height, side);
	}

	@Override
	public double calcVolume() {
		return calcBaseArea() * height;
	}
/**
 *
 * @author Eze
 */
	
	@Override
	public double calcBaseArea() {
		//should we return a float value - it may be better when comparing - just a thought - @Madhu
		return (side * side * Math.sqrt(3)) / 4; 
	}
	
	

}
