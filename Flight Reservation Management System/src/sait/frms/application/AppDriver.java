package sait.frms.application;

import sait.frms.gui.MainWindow;

import java.io.IOException;

import sait.frms.exception.InvalidCitizenshipException;
import sait.frms.exception.InvalidFlightCodeException;
import sait.frms.exception.InvalidNameException;
import sait.frms.gui.*;


/**
 * This program runs the Flight management system created of a travel agency to do airline bookings for their client in Canada
 * @author Madhu Madhavan
 * @version July 9th 2021
 * 
 *
 */
public class AppDriver {

	/**
	 * Entry point to Flight management application.
	 * @param args
	 * 
	 * @throws NumberFormatException 
	 * @throws InvalidNameException 
	 * @throws InvalidCitizenshipException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException, InvalidCitizenshipException, InvalidNameException {
		MainWindow mainWindow = new MainWindow();
		mainWindow.display();
	}

}
