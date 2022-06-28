/**
 * Description: this is called Gnome sort or stupid sort because the comparison between elements goes up and down the array
 * 
 * Rationale for choosing: This is one of the reasons we feel it is different to all the other sorts, as all other sorts either go all the way up to n-1, 
 * or start at n-1 and go all the way to 0, or the other sorts totally splits up the entire array.
 * 
 *  Steps to sort
 *  1.If you are at element [0] then go to the right element [1]
 *  2.If elements at 0 is smaller then leave them as is, and go one step right
 *  3.Compare elements at 0 and 1, if element at 0 is larger than 1, then swap them and go one step back
 *  4.Repeat the above two steps until counter reaches end of array.
 *  5. If end is reached then array is sorted
 *  
 *  */
package Utilities;

import java.util.Comparator;

public final class GnomeSort
{
	/**
	 * 
	 * @param <T>
	 * @param arr
	 * @param comp
	 * @param n
	 * @author Madhu
	 */
    @SuppressWarnings("unchecked")
	public <T> void gnomeSort(Comparable<T>[] arr, Comparator<? super T> comp, int n)
    {
    	{
            int index = 0;
     
            while (index < n) { //going through each element in the array
                if (index == 0)
                    index++;   //step 1 (above) If you are at element [0] then go to the right element [1]
                if (comp.compare( (T) arr[index], (T) arr[index - 1]) <= 0)  // arr[index] >= arr[index - 1]
                    index++;   // step 2 (above) If elements at 0 is smaller then leave them as is, and go one step right
                else {
                	Comparable<T> temp = null; //otherwise its step 3, and the elements will be swapped
                    temp = arr[index];      // step 3 (above) 3.Compare elements at 0 and 1, if element at 0 is larger than 1, then swap them 
                    arr[index] = arr[index - 1];
                    arr[index - 1] = temp;
                    index--;    // and go one step back
                }
            } // when loop exits then the array is sorted
            return;
        }
    }
}