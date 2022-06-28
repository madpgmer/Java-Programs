/**
 * 
 * @author Madhu
 */

package shapeObjects;

import java.util.Comparator;

public class HeightComparator implements Comparator<Shape>
{

	public int compare(Shape s1, Shape s2)
	{
		return s1.compareTo(s2);
	}

}