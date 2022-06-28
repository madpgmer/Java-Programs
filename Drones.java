/*Write a program to read in a list of consumer drones from a file and print out the drone model having the lowest price.
The format of data stored in the input file is as follows:
price<space>weight<space>model info<newline>

Data File: drones.txt

1499 907 DJI Mavic 2 Pro

1889 1380 DJI Phantom 4 Pro

765 500 Parrot Bebop 2

1399 863 Autel Robotics EVO

1079 430 DJI Mavic Air 

The following is a sample run, user input is shown in bold underline: 

Enter the filename: c:\temp\drones.txt

Drone with the lowest price:  Parrot Bebop 2

Price: $765.00 Weight: 500 g

@Author: Madhu Madhavan

**/

import java.io.*;
import java.util.*;


public class Drones {

	public static void main(String[] args) throws IOException  {
		 Scanner in = new Scanner (System.in);
	        System.out.print ("Enter the filename: ");
	        String filename = in.nextLine();
	        File myFile = new File (filename);
	        Scanner inFile = new Scanner(myFile);
	        
	        String info = "";
	        String [] items = null;
	        ArrayList<String> lines = new ArrayList<>();
	        
	        double price = 0;
	        int weight = 0;
	        String modelInfo = "";
	        
	        while (inFile.hasNextLine()) {
	        	info = inFile.nextLine();
	        	lines.add(info);
	        }
	        items = lines.get(0).split(" ");
	        
	        double lowestPrice = Double.parseDouble(items[0]);
	        weight = Integer.parseInt(items[1]);
        	for (int i = 2; i < items.length; i++) {
    		modelInfo = modelInfo + items[i]+ " ";
        	}
	        for (String Ln: lines) {
	        	
	        	items = Ln.split(" ");
        	
	        	price = Double.parseDouble(items[0]);
	        	
	        	if (price < lowestPrice) {
	        		modelInfo = "";
	        		lowestPrice = price;
	        		weight = Integer.parseInt(items[1]);
		        	for (int i = 2; i < items.length; i++) {
	        		modelInfo = modelInfo + items[i] + " ";
		        	}
	        	}
	        	
	        	
	        }
	        System.out.printf("%s%s%n", "Drone with the lowest price: ", modelInfo);
	         System.out.printf("%s$%5.2f%s%d%s%n", "Price: ", lowestPrice, " Weight: ", weight, "g");
	         
		in.close();
		inFile.close();
	}

}
