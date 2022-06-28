/**
 *  
 * The ABC Book Company wants to implement a system to manage their books more efficiently. They want the 
 * system to allow both employees and patrons to checkout, find, and list books. We have received a data file containing a sample list of books. 
 * The data file contains a combination of four different types of books: children’s books, cookbooks, paperbacks, and periodicals. 
 * Each book is uniquely identified using an ISBN. We have written a program that retrieves the required information by using different methods and procedures
 * 
 * 
 * @author: Madhu Madhavan
 * @version 6/4/2021
 */

package sait.bms.application;

import java.io.IOException;

import java.util.Scanner;

import sait.bms.manager.BookManager;

public class Driver {

	public static void main(String[] args) throws IOException {

		Scanner scan = new Scanner(System.in);

		// create object instance of manager class, and pass filepath and scanner as it
		// will also read the file

		BookManager manager = new BookManager();
		manager.runBookCompany(scan);

	}

}
