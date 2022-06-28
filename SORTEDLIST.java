/**

@Author: Madhu Madhavan

**/
import java.util.Scanner;

public class SORTEDLIST {

	public static void main(String[] args) {
		
		Scanner in = new Scanner (System.in);
		System.out.print("Enter the list size of the list: ");
		int[] list = new int[in.nextInt()];
		System.out.print("Enter the contents of the list:  ");
		for(int i = 0; i < list.length; i++) {
			list[i] = in.nextInt();
		}
		System.out.print("The list has " + list.length + " integers ");
		for (int p : list) {
			System.out.println(p);
		}
		
		System.out.print("The list is " + (isSorted(list)? "sorted": "not sorted"));
		
	}
	public static boolean isSorted(int[] list) {
		boolean isSorted = true;
		
		for (int i = 0; i < list.length -2; i++) {
			if(list[i] > list[i + 1]) {
				isSorted = false;
			}
		}
		return isSorted;
	}
}
