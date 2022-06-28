/**

@Author: Madhu Madhavan

**/
import java.util.*;
import java.io.*;

public class Morse_Code_Translator {

	public static void main(String[] args) throws Exception {
	// 1.0 Load code translation file (morse.txt) into arrays
		// Input the code translation filename from user
			Scanner in = new Scanner(System.in);
			System.out.print ("Enter Morse Code translation table file path: ");
			String codePath = in.next();
		 	File morse = new File(codePath);	
		// Create a Scanner for the file
		    Scanner inFile = new Scanner(morse);
		// Declare and initialize arrays
		    char[] characterArray = new char[39];
		    String[] codeArray = new String[39];
		    String [] outputText = new String [30];
		    
		    String character = "";
		    int count = 0;
		// Read data from a file and input them as elements in two parallel arrays 
		    for (int i = 0; i < 39; i++) {
		    	if (inFile.hasNext()) {
		    		character = inFile.next();
		    		characterArray[i] = character.charAt(0);
		    		codeArray[i] = inFile.next();
		    		count++;
		    	}
		    }
		 // Close the file
		       inFile.close();
		    
		
		 // Output the number of codes loaded
		    System.out.println("Code translation file processed. " + count + " codes loaded.");
		    
	//Read and parse message input file (CodedMessage.txt)
		    String codeLine = "";
		    String [] items = null;
		// Input the message filename from user
		    System.out.print ("Enter coded message input file path: ");
		    String codedPath = in.next();
		    File CodedMsgfile = new File(codedPath);	
		// Create a Scanner for the file
		    Scanner codedFile = new Scanner(CodedMsgfile);
		// Read each line from the message file and Parse each separate morse code value in the line 
		    int numOfWords = 0;
		    while(codedFile.hasNext()) {
		    	codeLine = codedFile.nextLine();
		    	// Split line into string parts
	            items = codeLine.split(" ");
	            
	            /*For each morse code value search for matching value in the code array
		    	  Get corresponding English-language letter from the other array
	              parse the code array to get the matching code. take the index of that array 
	              and read the letter from the same index of the character array
	              Concatenate the character to the array*/
	            
	            String word = "";
	            for (String item: items) {
	            	boolean keepSearching = true;
	            	int i = 0;
	            	while (keepSearching) {
	            		if (item.contentEquals(codeArray[i])) {
	            			word = word + characterArray[i];
	            			keepSearching = false;
	            		}
	            		i++;
	            	}
	            }
	            outputText[numOfWords] = word;
	            numOfWords++;
	         
		    }
		 // Close the file
		 codedFile.close();
		
		
	// Output decoded message 
		// Input the decoded message filename from user
		    System.out.print ("Enter decoded message output file path: ");
		    String decodedPath = in.next();
		    File decodedMsgFile = new File(decodedPath);
//		    if (decodedMsgFile.exists()) {
//		       System.out.println("File already exists");
//		       System.exit(0);
//		    }
		// Create a file
		    in.close();
		    PrintWriter output = new PrintWriter(decodedMsgFile);
		 // Output the number of words (i.e. lines) processed
		    System.out.println("Message translation complete. " +  numOfWords +  " words processed.");
		 
		// Write decoded words to the output file
		    int j=0;
		    while (outputText[j] != null) {
		    	if (outputText[j].endsWith(".")) {
					 output.println(outputText[j]);
				 }
				 else {
					 output.print(outputText[j] + " ");
				 }
		    	j++;
		    }
		    output.close();

	
	}

}
