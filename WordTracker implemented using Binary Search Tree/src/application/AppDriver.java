package application;

import java.io.IOException;

import exceptions.TreeException;
import utilities.WordParser;

public class AppDriver {

	
	/**
	 * This is the main entry point of the application.. Here the arguments are also checked for correct format before calling the Word Parser Class
	 * 
	 * If there are more arguments than 4, then there is an error message out to the console
	 * If the format of the first two parameters not correct then the program exits
	 * If there are less than 3 parameters, then that is assumed that there is no output file mentioned and all output will be to console
	 * @author Madhu
	 * @param args
	 * @throws TreeException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TreeException, IOException {
		if (args != null) {
			String filenamePath = args[0];
			WordParser wordParser = new WordParser(filenamePath);
			
			boolean incorrectParameters = false;
			if (args.length < 5) { // then we need to export the report to a file
				String processingOption = args[1];
				if (processingOption.charAt(0) == '-' && processingOption.charAt(1) == 'p') {
					if (processingOption.charAt(2) == 'f' || processingOption.charAt(2) == 'l' || processingOption.charAt(2) == 'o') {
						if (args.length < 3) { // then no output parameters, and result will be printed on console
							
							System.out.println("NO OUTPUT parameters provided; results printed on console as follows:");
							System.out.println();
							wordParser.processFile(processingOption, null);							
						
						} else {
							if (args[2].charAt(0) == '-' && args[2].charAt(1) == 'f') {
								
								wordParser.processFile(processingOption, args[3]);	
							
							} else {
							
								System.out.println("There is an INCORRECT OUTPUT parameters provided; results printed on console as follows:");
								System.out.println();
								wordParser.processFile(processingOption, null);
							
							}
						}
					} else {
						incorrectParameters = true;
					}
				} else {
					incorrectParameters = true;
				}
			} else { //means there are too many params
				incorrectParameters = true;
			}
			
			if (incorrectParameters) {
				System.out.println("Please provide the correct parameters for this program, there is something wrong with your parameters, fix that and try again!");
			}

		}
	}
}
