/**
 * 
 * @author Madhu
 */

package Utilities;

import java.util.Comparator;

public final class InsertionSort 
{
    /*Function to sort array using insertion sort*/
	@SuppressWarnings("unchecked")
	/**
	 * 
	 * @param <T>
	 * @param arr
	 * @param comp
	 * 
	 * @author Madhu
	 */
	public <T> void insertionSort(Comparable<T>[] arr, Comparator<? super T> comp)
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
        	Comparable<T> key = arr[i];
            int j = i - 1;
 
            /* Move elements of arr[0..i-1], that are greater than key, to one position ahead of their current position */
            while (j >= 0 && comp.compare((T) arr[j], (T) key) < 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
}
