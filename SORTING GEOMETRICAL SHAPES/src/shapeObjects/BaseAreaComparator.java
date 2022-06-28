/**
 * 
 * @author Madhu
 */


package shapeObjects;

import java.util.Comparator;

/**
 * 
 * @author Madhu
 *
 */

public class BaseAreaComparator implements Comparator<Shape>{

	public int compare(Shape s1, Shape s2) {
		if (s1.calcBaseArea() > s2.calcBaseArea()) {
			return 1;
		} else if (s1.calcBaseArea() < s2.calcBaseArea()) {
			return -1;
		} else {
			return 0;
		}
	}
}
