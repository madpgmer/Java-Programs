/**

@Author: Madhu Madhavan

**/
import java.util.Scanner;
//import java.util.ArrayList;
public class EliminateDuplicates {
  public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      int[] numbers = new int[10];
      System.out.print("Enter ten integers: ");
      for(int i = 0; i < 10;i++){
          numbers[i] = in.nextInt();
      }
      
      int[] newList = eliminateDuplicates(numbers);
      int count = 0;
      for (int i=0; i< newList.length; i++) {
    	  if (newList[i] != 0) {
    		  count++;
    	  }
          
      }
      System.out.println("The number of distinct integers is " + count);
      System.out.print("The distinct integers are ");

      for (int num : newList) {
    	  if (num != 0) {
    		  System.out.print(num + " ");
    	  }
          
      }
  }
 
  public static int[] eliminateDuplicates(int[] list){
      int [] newList = new int[list.length];
      int position = 0;
      
      for (int i = 0; i < list.length; i++ ) {
    	  boolean isThere = false;  
    	  // loop to check in the new list if the value if list[i]
    	  for (int j=0; j< newList.length; j++) {
    		  if (newList[j] == list[i]) {
    			  isThere = true;
    			  break;
    		  }
    	  }
    	  
    	   if (!isThere) {
   		  
    		  newList[position] = list[i];
    		  position++;
    	  }
    	  
    	 
      }
      return newList;
      
  }
}
//for (int k=0; k==newList.length; k++) {
//if (newList[k] == 0) {
//	  newList[k] = list[i];
//	  break;
//}
//}
