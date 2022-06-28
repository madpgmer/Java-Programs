/**
 * 
 * @author Madhu
 */
package Utilities;

import java.util.Comparator;

public final class SelectionSort
{

	@SuppressWarnings("unchecked")
	/**
	 * @author Madhu
	 * @param <T>
	 * @param arr
	 * @param comp
	 */
    public <T> void selectionSort(Comparable<T>[] arr, Comparator<? super T> comp)
    {
        int n = arr.length;
 
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (comp.compare((T) arr[j], (T) arr[min_idx]) > 0)
                    min_idx = j;
 
            // Swap the found minimum element with the first element
            Comparable<T> temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }
}