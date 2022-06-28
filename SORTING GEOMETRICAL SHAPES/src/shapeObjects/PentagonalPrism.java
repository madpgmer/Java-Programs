/**
 * 
 * @author Madhu
 */
package shapeObjects;

public class PentagonalPrism extends Prism {

	//constructor
	public PentagonalPrism(double height, double side) {
		super(height, side);
	}
	
	/**
	 * 
	 * @author Eze
	 */
	
	@Override
	public double calcVolume() {
		return calcBaseArea() * height;
	}

	public double calcBaseArea() {
			//should we return a float value - it may be better when comparing - just a thought - @Madhu
		return (5 * side * (Math.tan(Math.toRadians(54)))) / 4; 
	}

}
