
/**
 * @author Madhu
 * 
 *  Driver class to run the xml parser    
 * 
 */

package application;

import exceptions.EmptyQueueException;
import utilities.XMLParser;

public class AppDriver {
	
	public static void main(String[] args) throws EmptyQueueException {
		if (args != null) {
			String filenamePath = args[0];
			XMLParser xmlparser = new XMLParser(filenamePath);
			
			xmlparser.processFile();
			
			
			
			
		}
	}
}
