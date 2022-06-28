/**
 * 
 * @author Madhu
 */

package shapeObjects;

import java.util.Comparator;

public class VolumeComparator implements Comparator<Shape>{

	public int compare(Shape s1, Shape s2) {
		if (s1.calcVolume() > s2.calcVolume()) {
			return 1;
		} else if (s1.calcVolume() < s2.calcVolume()) {
			return -1;
		} else {
			return 0;
		}
	}
}
