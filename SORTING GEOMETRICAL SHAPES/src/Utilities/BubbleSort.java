/**
 * 
 * @author Madhu
 */

package Utilities;

import java.util.Comparator;


public final class BubbleSort
{
	// generic bubble sort
    @SuppressWarnings("unchecked")
    /**
     * 
     * @param <T>
     * @param arr
     * @param comp
     * @author Madhu
     */
	public <T> void bubbleSort(Comparable<T>[] arr, Comparator<? super T> comp)
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (comp.compare((T) arr[j], (T) arr[j+1]) < 0)
                
                {
                   	Comparable<T> temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }
}