/**
 * @author Madhu
 * 
 * Using the data structures created we implemented XML document parser to 
 * parse for errors in the XML construction. The XMLParser.java prints all 
 * the lines that are not properly constructed in the order in which they 
 * occur along with their line numbers.
 *         
 * 
 */

package utilities;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.EmptyStackException;
import java.util.Scanner;

import adt.Iterator;
import exceptions.EmptyQueueException;

import utilities.MyArrayList;
import utilities.MyQueue;
import utilities.MyStack;

public class XMLParser {
	
	// Constants
	private static final char OPEN_TAG = '<';
	private static final char CLOSE_TAG = '>';
	private static final String END_TAG = "/";
	private static final String XML_OPEN = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
	
	// Attributes
	private MyQueue<XMLTag> errorQueue;
	private MyStack<XMLTag> tagStack;
	private MyQueue<XMLTag> extrasQueue;
	private MyArrayList<String> xmlLines;
	private String fileName;
	private Scanner read = null;
	
	// Constructors
	public XMLParser(String fileName)
	{
		this.fileName = fileName;
		tagStack = new MyStack<XMLTag>();
		errorQueue = new MyQueue<XMLTag>(); 
		extrasQueue = new MyQueue<XMLTag>(); 
	}
	
	public void processFile() throws EmptyQueueException {
		try {
			inputXML();
			parseXML();	
			displayErrors();
				
		} catch (FileNotFoundException e) {
			System.out.println("Please provide the correct filename and the correct filepath!");
			e.printStackTrace();
		}

	}
	
/**
 * @author Madhu
 * @throws EmptyQueueException
 */
	
	private void displayErrors() throws EmptyQueueException
	{
		if (errorQueue.size() == 0 && extrasQueue.size() == 0) {
			System.out.println("There are no errors in this file");
		} else {
			
			while (!errorQueue.isEmpty() || !extrasQueue.isEmpty()) {
					if (errorQueue.size() == 0 && extrasQueue.size() > 0) { //then report items in extrasQueue as error
						Iterator<XMLTag> it = extrasQueue.iterator();
						XMLTag value;
						
						while (it.hasNext()) {
							value = it.next();
							System.out.println(value.getTagName() + "  - Tag is incorrect on line number " + value.getLine() );
							extrasQueue.dequeue();
						}			
					} else if (errorQueue.size() > 0 && extrasQueue.size() == 0) { //then report items in errorQueue as error
						Iterator<XMLTag> it = errorQueue.iterator();
						XMLTag value;
						
						while (it.hasNext()) {
							value = it.next();
							System.out.println(value.getTagName() + "  - Tag is incorrect on line number " + value.getLine() );	
							errorQueue.dequeue();
						}			
					} else { //both queues are not empty, peek both queues and compare
						while (!errorQueue.isEmpty()) {
							if (!extrasQueue.isEmpty()) {
								XMLTag extra = extrasQueue.peek();
								XMLTag error = errorQueue.peek();
								
//								if (extra.equals(error)) {
								if (extra.getTagName().contentEquals(error.getTagName())) { // If they match dequeue from both
									extrasQueue.dequeue();
//									errorQueue.dequeue();
								} else { // If they don’t match, dequeue from errorQ and report as error
									XMLTag errorReport = errorQueue.peek();
									System.out.println(errorReport.getTagName() + "  - Tag is incorrect on line number " + errorReport.getLine() );	
									errorQueue.dequeue();
								}
							} else {
								XMLTag errorReport = errorQueue.peek();
								System.out.println(errorReport.getTagName() + "  - Tag is incorrect on line number " + errorReport.getLine() );	
								errorQueue.dequeue();
							}
						}
					}
			}	
					
		}		
	}
	
	/**
	 * Method to read in the XML file to a MyArrayList of String objects, one
	 * String per line of XML.
	 * @author Madhu
	 * @throws FileNotFoundException 
	 */
	private void inputXML() throws FileNotFoundException
	{
		File file = new File(this.fileName);
		read = new Scanner(file);	
		String data = "";
		xmlLines = new MyArrayList<String>();
	
		while (read.hasNextLine()) {

			data = read.nextLine();
			xmlLines.add(data);
		}
	}
	
	
	/**
	 * Method to parse lines of XML from MyArrayList to a queue of XMLTag
	 * Objects. Some error checking occurs here (unnecessary close tags)
	 * @author Madhu
	 * @throws EmptyQueueException 
	 */
	private void parseXML() throws EmptyQueueException
	{
		Iterator<String> it = xmlLines.iterator();
		String value = null;
		MyStack<Character> openClose;
		
		int lineNumber = 1;
		while (it.hasNext()) {
			value = it.next();
			value = value.replaceAll("\t", ""); //cleaning up tags
			openClose = new MyStack<Character>();
			
			if (!value.contentEquals(XML_OPEN)) {
				String tagName = "";	
				
				for (int i = 0; i < value.length(); i++) {
										
					if (value.charAt(i) == OPEN_TAG) {
						openClose.push(value.charAt(0));
						tagName = "";
					} 
					else if (value.charAt(i) != CLOSE_TAG) {
						tagName = tagName + (value.charAt(i));
				
					} else {
						try {
							//first check that there is a open tag BRACKET <> for this close tag
							if (openClose.peek().equals(OPEN_TAG)) {
								openClose.pop();
								
								if (tagName.contains(END_TAG)) { 
									
									if (tagName.startsWith(END_TAG)) { //then its a closing tag (END_TAG)
										tagName = tagName.substring(1);
										XMLTag tag = new XMLTag(tagName, lineNumber, true);
										

										if (tagStack.isEmpty()) { //if tags stack empty, then add to error queue
											errorQueue.enqueue(tag);
										} else if (!tagStack.isEmpty() && tagStack.peek().getTagName().contentEquals(tag.getTagName())) { //if matched top of stack then pop stack 
											tagStack.pop();
										} else if (!errorQueue.isEmpty() && errorQueue.peek().getTagName().contentEquals(tag.getTagName())) { //else if matches head of errorQ, dequeue and ignore
											errorQueue.dequeue();
										} else { //search stack for matching start tag
											Iterator<XMLTag> itS = tagStack.iterator();
											XMLTag search;
											int count = 0;
											boolean foundIt = false;
											while (itS.hasNext()) {
												search = itS.next();
												if (search.getTagName().contentEquals(tag.getTagName())) {
													foundIt = true;
													break;
												}
												count++;
											}
											int fromTop = tagStack.size() - count;
													
											if (foundIt) {
												for (int j = 1; j < fromTop; j++) {
													errorQueue.enqueue(tagStack.pop()); //then report this as error
												}
												tagStack.pop(); //this is to pop off the matching open tag;
											} else {
												extrasQueue.enqueue(tag);
											}
										}
									} else { //its a self closing tag
										//ignore
									}
								
								} else { //its an open tag
									if (tagName.contains(" ")) {
										String[] elements = tagName.split(" ");
										tagName = elements[0]; //ignore the name value pairs (as per assignment rules)and take the name of the tag only								
									}
									XMLTag tag = new XMLTag(tagName, lineNumber, false);
									tagStack.push(tag);
								}
							}
						} catch (EmptyStackException e) { //mean there is no open tag for this close tag
							errorQueue.enqueue(new XMLTag(value, lineNumber, false));							
						}							
					}	
				}
			}
		lineNumber++;
		}
		
		if (!tagStack.isEmpty()) {
			errorQueue.enqueue(tagStack.pop()); //then report this as error
		}
		
		
		
		
	}	
			
				
				

}
