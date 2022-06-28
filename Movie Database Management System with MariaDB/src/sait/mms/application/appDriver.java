package sait.mms.application;
/**
@author: Madhu Madhavan
**/

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import sait.mms.drivers.MariaDBDriver;
import sait.mms.managers.MovieManagementSystem;

public class appDriver {

	/**
	 * This is the starting point of the application
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
//		Scanner scan = new Scanner(System.in);
		int selection;
		
		//create object instance of MovieManagementSystem class, and pass scanner
		MovieManagementSystem manager = new MovieManagementSystem();
        selection = manager.displayMenu();
        
        while (selection != 5) {
            switch (selection) {
                case 1: { // Creates new record of Movie in DB 
                	manager.addMovie();
	                }
                break;
                case 2: { // Retrieves records with movies released in a specific year
                	manager.printMoviesInYear();
                }
                break;
                case 3: { // Retrieves records with a list of random movies
                	manager.printRandomMovies();
                }
                break;
                case 4: { // Delete a movie using its id
                	manager.deleteMovie();
                }
                break;
                
                default: {
                	System.out.println("Not a valid option");
                }
            }
            selection = manager.displayMenu();
        }
        manager.mariaDB.disconnect(); //disconnect the database on exit
        System.out.println("Goodbye!");
	}

}
