-package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
//import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import exceptions.TreeException;

public class WordParser{
	
	// Attributes
	private BSTree<Word> wordTree;
	private String fileName;
	private Scanner read = null;
	private ArrayList<String> allLines;
	
	// Constructors
	public WordParser(String fileName)
	{
		this.fileName = fileName;
		wordTree = new BSTree<Word>();
	}
	
	
	/**
	 * This is the main process method of the word parser class, it sequentially
	 * 1. checks if the binary file exits and restores it to the BSTree
	 * 2. reads text file all the lines in that file
	 * 3. updates the BSTree with the word contents of the file
	 * 4. processes the arguments from the console and display the tree according to that
	 * 5. restored the word BS tree back to the serialized tree
	 *
	 * @author Madhu
	 * @param processingOption
	 * @param outputfileName
	 * @throws TreeException
	 * @throws IOException
	 */
	public void processFile(String processingOption, String outputfileName) throws TreeException, IOException {
		try {
			
			//if file exists then restore tree
			restoreTreeIfBinaryExists();
			
			//then read file
			readFile();
			
			//then update tree
			addToTree();	
			
			//process the arguments and display from tree
			processTree(processingOption, outputfileName);
			
			//restore file back to versions
			searializeAndSaveToFile();
			
		} catch (FileNotFoundException e) {
			System.out.println("Please provide the correct filename and the correct filepath!");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This method checks if the res/respository.ser binary file exists
	 * if so, then restores the binary tree from the binary file
	 * @author Madhu
	 */
	private void restoreTreeIfBinaryExists() {
		File f = new File("res/respository.ser");
		BSTree<Word> newWT = new BSTree<Word>();
		
		if(f.exists() && !f.isDirectory()) { 
			
			try
			{
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("res/respository.ser"));
			
				@SuppressWarnings("unchecked")
				ArrayList<Word> restored = (ArrayList<Word>) ois.readObject();
				ois.close();
			
				for(int i = 0; i < restored.size(); i++)
				{
					newWT.add(restored.get(i));
				}
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			
		this.wordTree = newWT;

		} 
	}
	
		
	/**
	 * This method attempts to read the file specified in the arguments
	 * The method creates the file if it doesn't exist
	 * and creates an arraylist of all the lines from the file
	 * @author Madhu
	 * @throws FileNotFoundException
	 */
	private void readFile() throws FileNotFoundException
	{
		File file = new File(this.fileName);
		read = new Scanner(file);	
		String data = "";
		allLines = new ArrayList<String>();
	
		while (read.hasNextLine()) {
			data = read.nextLine();
			allLines.add(data);
		}
	}
	
	
	/**
	 * parses all the lines read from the text file, to extract all the words, filename and lines
	 * creates word objects and puts them in an array called wordNodes
	 * gets the current words in the current BSTree into a flat list
	 * check the current flat list of any existing words and updates the filenames and lines
	 * add the new words to the flat list from the file read
	 * then restores the current flat list to the BSTree
	 * @author Madhu
	 * @throws TreeException
	 */
	private void addToTree() throws TreeException 
	{
		Iterator<String> it = allLines.iterator();
		String value = null;
		ArrayList<Word> wordNodes = new ArrayList<Word>();;
		
		int lineNumber = 1;
		while (it.hasNext()) {
			value = it.next();
			
			if (!value.contentEquals("")) { //if the line is blank then ignore it
			
				value = value.replaceAll("\t", ""); //cleaning up tags
				value = value.replaceAll("\\p{Punct}", ""); //removing all punctuation from the words
				
				String[] wordsFromLine = value.split(" ");
				
					for (int i = 0; i < wordsFromLine.length; i++) {
						
						String wordToAdd = wordsFromLine[i];
						
						boolean foundIt = false;
						int index = 0;
						for (Word wd: wordNodes) { //checks to see if the word has already been added to wordnodes
							if (wordToAdd.equalsIgnoreCase(wd.getWord())) {
								foundIt = true;
								break;
							}
							index++;
						}
						
						if (foundIt) { //then the word matches - check to see if the file name matches
							Word toUpdate = wordNodes.get(index);
							
							ArrayList<String> lfPairs = toUpdate.getLfPairs();
							ArrayList<String> newlfPairs = new ArrayList<String>();
									//toUpdate.getLfPairs();
							boolean fileMatches = false;
							
							for (String lf : lfPairs) {
								String[] lfp = lf.split("#");
								
								if (lfp[0].equalsIgnoreCase(fileName)) { //if the filename matches, then concatenate the linenumbers
									String addFileNameLineNumbers = lfp[0] + "#" + lfp[1] + "|" + lineNumber;
									newlfPairs.add(addFileNameLineNumbers);
									fileMatches = true;
								} else {
									newlfPairs.add(lf);
								}
							}
							
							if (fileMatches) { // first part is the filename, if it matches, add the linenumber and replace the word
								toUpdate.setLfPair(newlfPairs);							
							} else { //add the lfpair as a new string
								String newFileNameLinePair = fileName + "#" + lineNumber;
								lfPairs.add(newFileNameLinePair);
								toUpdate.setLfPair(lfPairs);
							}
							wordNodes.set(index, toUpdate); //here the replacement of the word with the new file name and line numbers
						} else {
							Word w = new Word(wordToAdd, fileName, lineNumber);
							wordNodes.add(w);
						}
					}
					lineNumber++;
				}
			}			
	
		//now get the old tree as a list, and update elements as needed, before restoring back to the tree
		adt.Iterator<Word> it2 = wordTree.inorderIterator();
		ArrayList<Word> existingList = new ArrayList<Word>();
		ArrayList<Word> listToAdd = new ArrayList<Word>();

		while (it2.hasNext()) {
			existingList.add(it2.next());
		}

		for (Word w : wordNodes) { //this updates the flat list collection of words
			
			boolean foundIt = false;
			int index = 0;
			for (Word wd: existingList) {
				if (w.getWord().equalsIgnoreCase(wd.getWord())) {
					foundIt = true;
					break;
				}
				index++;
			}
			
			if (foundIt) { //then update the existing list with the file name and lines of new file
				Word toUpdate = existingList.get(index);
				
				ArrayList<String> lfPairs = toUpdate.getLfPairs();
				lfPairs.addAll(w.getLfPairs());
				
				toUpdate.setLfPair(lfPairs);	
				
				existingList.set(index, toUpdate);
			} else {
				listToAdd.add(w);
			}
			
		}
		
		//add listtoadd to existing list before adding to tree
		existingList.addAll(listToAdd);
		
		wordTree.clear();  //start with a blank tree
		for (Word w : existingList) { //this creates the word tree (from existing list - updated, and then includes the new list)
			wordTree.add(w);
		}
	}							
	
	
	
	/**
	 * This method processes the BSTree depending on the options entered by user in arguments
	 * if output file was specified in arguments then output will go to file
	 * if not then output will go to console
	 * - if argument is pf then just print in alphabetic order all words along with the corresponding 
	 * list of files in which the words occur
	 * - if argument is pl then print in alphabetic order all words along with the corresponding list 
	 * of files and numbers of the lines in which the word occur.
	 * - if the argument is po then print in alphabetic order all words along with the corresponding 
	 * list of files, numbers of the lines in which the word occur and the frequency of occurrence of the words.
	 * 
	 * @author Madhu
	 * @param pOption
	 * @param outputfileName
	 * @throws IOException
	 */
	private void processTree(String pOption, String outputfileName) throws IOException {				
		adt.Iterator<Word> it = wordTree.inorderIterator();
		Word value = null;
		
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		FileWriter fWriter = null;
		
		if (outputfileName == null) {
			osw = new OutputStreamWriter(System.out);
			bw = new BufferedWriter(osw);
		} else {
			fWriter = new FileWriter(outputfileName);
			bw = new BufferedWriter(fWriter);
		}
		
		while (it.hasNext()) {
			value = it.next();
			
			String actualWord = String.format("%-30s", "Word : " + value.getWord());
			
			if (pOption.charAt(2) == 'f') {
				
				String displayText = actualWord + " -file names: ";
				
				for (String s : value.getLfPairs()) {
					String[] fl = s.split("#");
					displayText = displayText + fl[0] + ",";
				}
				
				displayText = displayText.substring(0, displayText.length()-1);
				bw.write(displayText + "\r\n");
				
			} else if (pOption.charAt(2) == 'l') {
				
				String displayText = actualWord + " -file : ";
				
				for (String s : value.getLfPairs()) {
					String[] fl = s.split("#");
					displayText = displayText + fl[0] + " -line : ";
				
					String[] lines = fl[1].split("\\|");
					String ln = "";
					for (int i=0; i<lines.length; i++) {
						
						if (!ln.contains(lines[i])) {
							ln = lines[i] + ",";							
						}
					}
					displayText = displayText + ln;
				}
			
				displayText = displayText.substring(0, displayText.length()-1);
				displayText = String.format("%-80s", displayText);
				bw.write(displayText + "\r\n");
				
			} else if (pOption.charAt(2) == 'o') {
				
				String displayText = actualWord + " -file : ";
				int freq = 0;
				
				for (String s : value.getLfPairs()) {
					String[] fl = s.split("#");
					displayText = displayText + fl[0] + " -line : ";
				
					String[] lines = fl[1].split("\\|");
					String ln = "";
					
					for (int i=0; i<lines.length; i++) {		
						if (!ln.contains(lines[i])) {
							ln = ln + lines[i] + ",";							
						}
						freq++;
					}
					displayText = displayText + ln;
				}
			
				displayText = displayText.substring(0, displayText.length()-1);
				displayText = String.format("%-90s", displayText);
				
				displayText = displayText + " -Total frequency : " + freq;
				
				bw.write(displayText + "\r\n");
				
				
			} else {
				System.out.println("There is something wrong with the process option provided");
			}
			
			bw.flush();
		}							
	}
					

	
	/**
	 * This method takes the current BSTree and puts the words in a linear list
	 * and serializes to a binary file
	 * @author Madhu
	 */
	private void searializeAndSaveToFile() {
		adt.Iterator<Word> it = wordTree.inorderIterator();
		ArrayList<Word> list = new ArrayList<Word>();

		while (it.hasNext()) {
			list.add(it.next());
		}

		//then we push it to the file
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("res/respository.ser"));

			oos.writeObject(list);
			oos.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
