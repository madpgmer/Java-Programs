
/**
 * 
 * @author Madhu
 * 
 */

package Utilities;

import java.util.Comparator;

public final class QuickSort
{
	
	/**
	 * @author Madhu
	 * @param <T>
	 * @param arr
	 * @param comp
	 * @param low
	 * @param high
	 */
	// The main function that implements QuickSort; arr[] --> Array to be sorted, low --> Starting index, high --> Ending index
	public <T> void quickSort(Comparable<T>[] arr, Comparator<? super T> comp, int low, int high)
	{
		if (low < high)
		{
			// split is spliting index, arr[p]
			int split = partition(arr, comp, low, high);
	
			// Separately sort elements before split and after split
			quickSort(arr, comp,  low, split - 1);
			quickSort(arr, comp, split + 1, high);
		}
	}
		 
	/* This function takes last element as middle, places the middle element at its correct position in sorted array, 
	 * and places all smaller (smaller than middle) to left of middle and all greater elements to right of middle */
	@SuppressWarnings("unchecked")
	/**
	 * 
	 * @param <T>
	 * @param arr
	 * @param comp
	 * @param low
	 * @param high
	 * @return
	 */
	public <T> int partition(Comparable<T>[] arr, Comparator<? super T> comp, int low, int high)
	{
	    // middle
		Comparable<T> middle = arr[high];
	     
	    // Index of smaller element and indicates the right position of middle found so far
	    int i = (low - 1);
	 
	    for(int j = low; j <= high - 1; j++)
	    {
	        // If current element is smaller than the middle
	        if (comp.compare((T) arr[j], (T) middle) > 0     )   //arr[j] < middle
	        {            
	            // Increment index of smaller element
	            i++;
	            swap(arr, i, j);
	        }
	    }
	    swap(arr, i + 1, high);
	    return (i + 1);
	}
	
	// A utility function to swap two elements
	/**
	 * 
	 * @param <T>
	 * @param arr
	 * @param i
	 * @param j
	 */
	public <T> void swap(Comparable<T>[] arr, int i, int j)
		{
			Comparable<T> temp = arr[i];
		    arr[i] = arr[j];
		    arr[j] = temp;
		}
}