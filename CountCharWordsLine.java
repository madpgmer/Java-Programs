import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/*********************************************************************************
* (Count characters, words, and lines in a file) Write a program that will count *
* the number of characters, words, and lines in a file. Words are separated by   *
* whitespace characters. The file name should be passed as a command-line        *
* argument, as shown in Figure 12.13.  
@Author: Madhu Madhavan                                                          *
*********************************************************************************/
public class CountCharWordsLine {

	public static void main(String[] args) throws IOException{
//		if (args.length != 1) {
//			System.out.println("Usage: java filename");
//			System.exit(1);
//		}
//		File file = new File(args[0]);
//		
//		 if (!file.exists()) {
//			 System.out.println("File" + args[0] + " does not exist");
//			 System.exit(2);
//		 }
		System.out.print("Enter the file path: ");
		Scanner in = new Scanner(System.in);
		
		String newFile = in.nextLine();
		
		File file = new File(newFile);
		
		ArrayList<String> data = new ArrayList<>();
		Scanner inFile = new Scanner(System.in);
		System.out.print("Enter text: ");
		String lin = inFile.nextLine();
		
		while (!lin.equals("0")){
			
			data.add(lin);
			lin = inFile.nextLine();
			
			
		}
				
		PrintWriter file1 = new PrintWriter(file);
		file1.println(data.get(0));
		file1.println(data.get(1));
			
		file1.close();
		
			 
		 int characters = 0;
		 int words = 0;
		 int  lines = 0;
		 Scanner in1 = new Scanner(file); 
		 
		 while(in1.hasNext()) {
			 lines++;
			 String line = in1.nextLine();
			 
			 characters = characters + line.length();
			 String[] words1 = line.split(" ");
			 words = words1.length;
			 		 
		 }
		 				 
		System.out.println("File " + file.getName() + " has");
		System.out.println(characters + " characters");
		System.out.println(words + " words");
		System.out.println(lines + " lines");
		 
	}

}
