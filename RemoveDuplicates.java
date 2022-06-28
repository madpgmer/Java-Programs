/**

@Author: Madhu Madhavan

**/
import java.util.Scanner;
import java.util.ArrayList;
public class RemoveDuplicates {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        System.out.print("Enter ten integers: ");
        for(int i = 0; i < 10;i++){
            numbers.add(in.nextInt());
        }
        removeDuplicate(numbers);
        int count = 0;
        for (int i=0; i<numbers.size(); i++) {
        	 count++;
        }
        System.out.println("The number of distinct integers is " + count);
        System.out.print("The distinct integers are ");
        //for (int i=0; i<numbers.size(); i++) {
        	//System.out.print(numbers.get(i) + " ");
        //}
        for (Integer num : numbers) {
        	System.out.print(num + " ");
        }in.close();
    }
    public static void removeDuplicate(ArrayList<Integer> numbers) {
        // Write your code
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {

            if (!list.contains(numbers.get(i))) {
                list.add(numbers.get(i));
            }
        }
        numbers.clear();
        numbers.addAll(list);
    }
}