/**
 * The Movie Management System uses the MariaDB driver methods to
 * Create a new record representing a movie, retrieves records with movies released in a specific year,
 * retrieves a record with a list of random movies, deletes a movie using its ID 
 */
package sait.mms.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import sait.mms.drivers.MariaDBDriver;

/**
 * 
 * @author Madhu
 * @version 8/10/2021
 *
 */
public class MovieManagementSystem {
	public MariaDBDriver mariaDB =null;
	Scanner scan = null;
	
	public MovieManagementSystem(){
		try {
			scan = new Scanner(System.in);
			mariaDB = new MariaDBDriver();
			mariaDB.connect();
		} catch (SQLException e) {
			System.out.println("There was an error connecting the database" + e);
		}
	}
	
	/**
	 * displays the menu for selecting the option
	 * @return selection integer 
	 */
	public int displayMenu() {
		System.out.println();
		System.out.printf ("%s%n", "Welcome to Madjen's Movie Management System: Please select from the menu below!");
        System.out.printf ("%s%n","1     Creates a new record representing a movie");
        System.out.printf ("%s%n","2     Retrieves records with movies released in a specific year");
        System.out.printf ("%s%n","3     Retrieves records with a list of random movies");
        System.out.printf ("%s%n","4     Delete a movie using its id");
        System.out.printf ("%s%n","5     Exit");
        System.out.println();
        System.out.printf ("%s","Enter an option:");
        int selection = scan.nextInt();
        scan.nextLine(); // discard \n
        System.out.println();
        
        return selection;
	}
	
	/**
	 * this method prompts the user for details and adds a new movie to the mariaDB database
	 */
	public void addMovie(){
		try {
			System.out.print("Enter movie title: ");
	        String movieName = scan.nextLine();
			System.out.print("Enter duration: ");
	        int movieDuration = scan.nextInt();
	        scan.nextLine(); // discard \n
	        System.out.print("Enter year: ");
	        int movieYear = scan.nextInt();
	        scan.nextLine(); // discard \n
			
			String insertQuery = "INSERT INTO Movies (duration, title, year) VALUES (" + movieDuration + ",'" + movieName + "'," + movieYear + ");";
			int rows = mariaDB.update(insertQuery);
			if (rows !=0) {
				System.out.println("Added movie to database");
			}
		} catch (SQLException e) {
			System.out.println("There was an error inserting the movie" + e);
		}
	}
	
	/**
	 * this method prints all the movies for a specific year
	 */
	public void printMoviesInYear(){
		try {
			int totalDuration = 0;
			System.out.print("Enter in year: ");
	        int Qyear = scan.nextInt();
	        scan.nextLine(); // discard \n
			
			String sqlQuery = "SELECT * FROM Movies WHERE year='" + Qyear + "';";
			ResultSet results = mariaDB.get(sqlQuery);
			
			System.out.println();
			System.out.printf("Movie List \n");
			System.out.printf("Duration\tYear\tTitle\n");
			while(results.next()) {
			//	int id = results.getInt(1);   // ID not needed for display as per sample run txt
				int duration = results.getInt(2);
				totalDuration += duration;
				String title = results.getString(3);
				int year = results.getInt(4);
				
				System.out.printf(duration + "\t\t" + year + "\t" + title + "\n");
			}
			System.out.println();
			System.out.println("Total duration: " + totalDuration + " minutes");
			System.out.println();
		} catch (SQLException e) {
			System.out.println("There was an error in printing the movie by year" + e);
		}
	}
	
	/**
	 * this method prints x number of random movies from the database
	 */
	public void printRandomMovies() {
		try {
			int totalDuration = 0;
			System.out.print("Enter number of movies: ");
	        int randomNum = scan.nextInt();
	        scan.nextLine(); // discard \n
			
			String sqlQuery = "SELECT * FROM movies ORDER BY RAND() LIMIT " + randomNum + ";";
			ResultSet results = mariaDB.get(sqlQuery);
			
			System.out.println();
			System.out.printf("Movie List \n");
			System.out.printf("Duration\tYear\tTitle\n");
			while(results.next()) {
//				int id = results.getInt(1);  //we dont need to display the ID
				int duration = results.getInt(2);
				totalDuration += duration;
				String title = results.getString(3);
				int year = results.getInt(4);
				
				System.out.printf(duration + "\t\t" + year + "\t" + title + "\n");
			}
			System.out.println();
			System.out.println("Total duration: " + totalDuration + " minutes");
			System.out.println();
		} catch (SQLException e) {
			System.out.println("There was an error in printing random movies" + e);
		}
	}	
	
	/**
	 * this method deletes the movie with the ID specifies (NOTE: you need to checkup the ID from the database)
	 */
	public void deleteMovie() {
		try {
			System.out.print("Please type the movie ID that you want to delete: ");
	        int movieID = scan.nextInt();
	        scan.nextLine(); // discard \n
			
			String deleteQuery = "DELETE FROM movies WHERE id = " + movieID + ";";
			int rows;
			rows = mariaDB.update(deleteQuery);
			if (rows !=0) {
				System.out.println("Movie " + movieID + " is deleted.");
			}
		} catch (SQLException e) {
			System.out.println("There was an error in deleting the movie" + e);
		}		
	}
}
