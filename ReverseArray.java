/**

@Author: Madhu Madhavan
Code to reverse an Array

**/

import java.util.*;
public class Exec07_12ReverseArray {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		double[] a = new double[10];
		System.out.print("Enter 10 numbers : " );
		for(int i = 0; i < a.length; i++) {
			a[i] = in.nextDouble();
		}
		
		System.out.println("The reversal of the input is " );
		for (double j : reverse(a)) {
			System.out.print(j + " ");
		}
		
	}
	public static double[] reverse(double[] array) {
		for (int k = 0; k < array.length/2; k++) {
			double temp = array[array.length-1-k];
			array[array.length-1-k] = array[k];
			array[k] = temp;
			
		}
		return array;
		
	}

}
