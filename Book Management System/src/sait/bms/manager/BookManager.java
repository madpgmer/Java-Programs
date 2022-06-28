/**
 * 
 * This is the main class where the major methods are coded. This class has methods that conducts 
 * and generates activities like Checking out books, displaying a book by its title and by its type
 * and a random selection from the data file. These methods also validate all input fields
 *
 * @author: Madhu Madhavan
 * @version 6/10/2021
 */

package sait.bms.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

import sait.bms.problemdomain.ChildrensBook;
import sait.bms.problemdomain.CookBook;
import sait.bms.problemdomain.Paperback;
import sait.bms.problemdomain.Periodicals;

public class BookManager {

	public final String FILE_PATH = "res/books.txt";

	Scanner read = new Scanner(FILE_PATH);

	ArrayList<Object> allBooks = new ArrayList<>();

	public BookManager() throws IOException {
		super();
		readBookFile(this.FILE_PATH, this.read);

	}

	/**
	 * this method reads the text file and creates child object classes and adds
	 * them to an array
	 * 
	 * @param FILE_PATH we read the file from the res folder
	 * @param inFile    Scanner object
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public void readBookFile(String FILE_PATH, Scanner inFile) throws IOException {
		File file = new File(FILE_PATH);

		if (!file.exists()) {
			System.out.printf("Cant find the file %s%n ", FILE_PATH);
			System.exit(1);
		}

		inFile = new Scanner(file);
		String data = "";
		String[] items = null;

		while (inFile.hasNextLine()) {

			data = inFile.nextLine();
			items = data.split(";");

			String ISBN = items[0];

			// using the ISBN number's last char each child book type is created using the
			// following code
			if (ISBN.charAt(12) == '0' || ISBN.charAt(12) == '1') { // its a children's book
				ChildrensBook childB = new ChildrensBook(Long.parseLong(items[0]), items[1], Integer.parseInt(items[2]),
						Integer.parseInt(items[3]), items[4], items[5], items[6]);
				allBooks.add(childB);

			} else if (ISBN.charAt(12) == '2' || ISBN.charAt(12) == '3') { // its a Cookbook
				CookBook cookB = new CookBook(Long.parseLong(items[0]), items[1], Integer.parseInt(items[2]),
						Integer.parseInt(items[3]), items[4], items[5], items[6]);
				allBooks.add(cookB);

			} else if (ISBN.charAt(12) == '4' || ISBN.charAt(12) == '5' || ISBN.charAt(12) == '6'
					|| ISBN.charAt(12) == '7') {// its a paperback
				Paperback paperB = new Paperback(Long.parseLong(items[0]), items[1], Integer.parseInt(items[2]),
						Integer.parseInt(items[3]), items[4], items[5], items[6], items[7]);
				allBooks.add(paperB);

			} else if (ISBN.charAt(12) == '8' || ISBN.charAt(12) == '9') {// its a Periodical
				Periodicals periodB = new Periodicals(Long.parseLong(items[0]), items[1], Integer.parseInt(items[2]),
						Integer.parseInt(items[3]), items[4], items[5]);
				allBooks.add(periodB);

			} else { // not a book
				System.out.println("Invalid ISBN : input line ignored");
			}
		}

	}

	/**
	 * This method runs the book management company (checking out books, find books,
	 * display book and produce random list based on patron input)
	 * 
	 * @param in Scanner
	 * @throws FileNotFoundException
	 */
	public void runBookCompany(Scanner in) throws InputMismatchException, FileNotFoundException {
		int choice;
		choice = navMenu(in);

		while (choice != 5) { // if choice is 5 the program exists and reload the data in book.txt
			switch (choice) {
			case 1: { // Checkout Book
				checkoutBook(in);
				System.out.println();
			}
				break;
			case 2: { // find books by title
				findBookTitle(in);
				System.out.println();
			}
				break;
			case 3: { // display book by type
				displayBooksType(in);
				System.out.println();
			}
				break;
			case 4: { // produce random book list
				randomBooks(in);
				System.out.println();
			}
				break;

			default:
				System.out.println("Error: Invalid choice, please enter number 1, 2,3, 4, 5 from menu!");

			}
			System.out.println();
			choice = navMenu(in);
		}
		// Exit and persist list back to books.txt
		exitAndPersist(this.FILE_PATH, this.read);

	}

	/**
	 * This method displays menu for the end user static class : because this
	 * doesn't change for any instance of BookManager objects
	 * 
	 * @return choice display navMenu
	 */

	public static int navMenu(Scanner k) throws InputMismatchException {

		System.out.printf("%s%n", "Welcome to ABC Book Company: How may we assist you?");
		System.out.printf("%s%n", "1     Checkout Book");
		System.out.printf("%s%n", "2     Find Books by Title");
		System.out.printf("%s%n", "3     Display Books by Type");
		System.out.printf("%s%n", "4     Produce Random Book List");
		System.out.printf("%s%n", "5     Save and Exit");
		System.out.println();
		System.out.print("Enter option: ");
		int choice = 0;
		try { // taking the input from scanner, but if it is a string, then an exception is
				// thrown and program returns to main menu
			choice = k.nextInt();
			k.nextLine(); // discard \n
			// System.out.print ("\f");
		} catch (InputMismatchException e) {
			k.next(); // this is to capture the extra LN when the wrong string was entered
			System.out.println("Error: Invalid choice, please enter number 1, 2, 3, 4, 5 from menu!");
			return 5; // return exit int for main menu
		}
		return choice;
	}

	/**
	 * this method checks if book is available, if so, then checks it out, otherwise
	 * displays error message. If ISBN is invalid an Error message will be
	 * displayed.
	 * 
	 * @param in
	 */
	public void checkoutBook(Scanner in) throws InputMismatchException {
		System.out.print("Enter ISBN of book (must be a 13 digit number): ");
		Long inputISBN = null;

		// taking the input from scanner, but if it is a string, then an
		// exception is thrown and program returns to main menu
		try {
			inputISBN = in.nextLong();
			in.nextLine();
		} catch (InputMismatchException e) {
			in.next(); // this is to capture the extra LN when the errornous
						// string was entered
			System.out.println("Error: Invalid ISBN - the number must be 13 digits long!");
			return;
		}

		if (inputISBN.toString().length() != 13) {
			/*
			 * here the input is a number, if however the number of digits is incorrect
			 * (they must be 13) then throw error and return to main menu
			 */
			System.out.println("Error: Invalid ISBN - the number must be 13 digits long!");
			return;
		}

		boolean foundit = false;

		// searching book Arraylist for ISBN

		if (inputISBN.toString().charAt(12) == '0' || inputISBN.toString().charAt(12) == '1') { // its a children's book
			ChildrensBook chiB = null;
			for (Object book : this.allBooks) {
				if (book instanceof ChildrensBook) {
					chiB = (ChildrensBook) book;
					if (chiB.getIsbn().compareTo(inputISBN) == 0) {
						foundit = true;
						break;
					}
				}
			}
			if (foundit) {
				if (chiB.getAvailable() > 0) {
					chiB.decrementAvailability();
					System.out.println("The book " + chiB.getTitle() + " has been checked out.");
					System.out.println("It can be located using a call number: " + chiB.getCallNumber());
				} else {
					System.out.println("There is no availability of this book " + chiB.getTitle() + " yet");
				}
			} else {
				System.out.println("Error: This book does not exist in our system!");
			}
		} else if (inputISBN.toString().charAt(12) == '2' || inputISBN.toString().charAt(12) == '3') { // its a Cookbook
			CookBook cookB = null;
			for (Object book : this.allBooks) {
				if (book instanceof CookBook) {
					cookB = (CookBook) book;
					if (cookB.getIsbn().compareTo(inputISBN) == 0) {

						foundit = true;
						break;
					}
				}
			}
			if (foundit) {
				if (cookB.getAvailable() > 0) {
					cookB.decrementAvailability();
					System.out.println("The book " + cookB.getTitle() + " has been checked out.");
					System.out.println("It can be located using a call number: " + cookB.getCallNumber());
				} else {
					System.out.println("There is no availability of this book " + cookB.getTitle() + " yet");
				}
			} else {
				System.out.println("Error: This book does not exist in our system!");
			}
		} else if (inputISBN.toString().charAt(12) == '4' || inputISBN.toString().charAt(12) == '5'
				|| inputISBN.toString().charAt(12) == '6' || inputISBN.toString().charAt(12) == '7') {// its a paperback
			Paperback paperB = null;
			for (Object book : this.allBooks) {
				if (book instanceof Paperback) {
					paperB = (Paperback) book;
					if (paperB.getIsbn().compareTo(inputISBN) == 0) {
						foundit = true;
						break;
					}
				}
			}
			if (foundit) {
				if (paperB.getAvailable() > 0) {
					paperB.decrementAvailability();
					System.out.println("The book " + paperB.getTitle() + " has been checked out.");
					System.out.println("It can be located using a call number: " + paperB.getCallNumber());
				} else {
					System.out.println("There is no availability of this book " + paperB.getTitle() + " yet");
				}
			} else {
				System.out.println("Error: This book does not exist in our system!");
			}
		} else if (inputISBN.toString().charAt(12) == '8' || inputISBN.toString().charAt(12) == '9') {// its a
																										// Periodical
			Periodicals periodB = null;
			for (Object book : this.allBooks) {
				if (book instanceof Periodicals) {
					periodB = (Periodicals) book;
					if (periodB.getIsbn().compareTo(inputISBN) == 0) {
						foundit = true;
						break;
					}
				}
			}
			if (foundit) {
				if (periodB.getAvailable() > 0) {
					periodB.decrementAvailability(); // available count will be decremented when we call this method

					System.out.println("The book " + periodB.getTitle() + " has been checked out.");
					System.out.println("It can be located using a call number: " + periodB.getCallNumber());
				} else {
					System.out.println("There is no availability of this book " + periodB.getTitle() + " yet");
				}
			} else {
				System.out.println("Error: This book does not exist in our system!");
			}
		}
	}

	/**
	 * This method finds book by title and displays the book details of their
	 * respective child class. If the input is invalid an error message will be
	 * displayed accordingly.
	 * 
	 * @param in
	 */
	public void findBookTitle(Scanner in) {
		System.out.print("Enter title to search for: ");
		String inputTitle = in.next();
		inputTitle = inputTitle.toLowerCase();
		boolean foundAtLeastOneBook = true; // this will be used to check if at least one book is found, if not then
											// print message

		System.out.println("Matching books: ");
		for (Object book : this.allBooks) {
			if (book instanceof ChildrensBook) {
				ChildrensBook chiB = (ChildrensBook) book;
				if (chiB.getTitle().toLowerCase().contains(inputTitle)) {
					System.out.println(chiB);
				}
			} else if (book instanceof CookBook) {
				CookBook cookB = (CookBook) book;
				if (cookB.getTitle().toLowerCase().contains(inputTitle)) {
					System.out.println(cookB);
				}
			} else if (book instanceof Paperback) {
				Paperback paperB = (Paperback) book;
				if (paperB.getTitle().toLowerCase().contains(inputTitle)) {
					System.out.println(paperB);
				}
			} else if (book instanceof Periodicals) {
				Periodicals periodB = (Periodicals) book;
				if (periodB.getTitle().toLowerCase().contains(inputTitle)) {
					System.out.println(periodB);
				}
			}
		}

		if (!foundAtLeastOneBook) {
			System.out.println("No book found with your input string: " + inputTitle);
		}
	}

	/**
	 * This method displays book types by providing the user to enter the type of
	 * book they want and selecting one of the options related to their specific
	 * types. Since the selection is just a character, char type variable is being
	 * used. Invalid case will throw an error message.
	 * 
	 * @param in Scanner
	 */
	public void displayBooksType(Scanner in) {
		int typeSelect = DisplayTypeMenu(in);

		switch (typeSelect) {
		case 1: { // Children's Book
			System.out.print("Enter a Format (P for Picture Book, E for Early readers or C for Chapter Book: ");
			String format = in.nextLine().toUpperCase();
			// format = Character.toUpperCase(format);
			for (Object book : this.allBooks) {
				if (book instanceof ChildrensBook) {
					ChildrensBook chiB = (ChildrensBook) book; // creating an instance of ChildrensBook and casting them
					if (chiB.getFormat().contentEquals(format)) {
						System.out.println(chiB);
					}
				}
			}
			break;
		}
		case 2: { // Cookbook
			System.out.print(
					"Enter a Diet (D for Diebetic, V for Vegetarian, G for Gluten-free, I for International or N for None: ");
			String diet = in.nextLine().toUpperCase();
			for (Object book : this.allBooks) {
				if (book instanceof CookBook) {
					CookBook cookB = (CookBook) book;
					if (cookB.getDiet().contentEquals(diet)) {
						System.out.println(cookB);
					}
				}
			}
			break;
		}
		case 3: { // Paperback
			System.out.print(
					"Enter a Genre (A for Adventure, D for Drama, E for Education, C for Classic, F for Fantasy or S for Science Fiction: ");
			String genre = in.nextLine().toUpperCase();
			for (Object book : this.allBooks) {
				if (book instanceof Paperback) {
					Paperback paperB = (Paperback) book;
					if (paperB.getGenre().contentEquals(genre)) {
						System.out.println(paperB);
					}
				}
			}
			break;
		}
		case 4: { // Periodical
			System.out.print(
					"Enter a Frequency (D for Daily, W for Weekly, M for Monthly, B for Bi-monthly or Q for Quarterly: ");
			String frequency = in.nextLine().toUpperCase();
			for (Object book : this.allBooks) {
				if (book instanceof Periodicals) {
					Periodicals periodB = (Periodicals) book;
					if (periodB.getFrequency().contentEquals(frequency)) {
						System.out.println(periodB);
					}
				}
			}
			break;
		}
		default:
			System.out.println(
					"Error: Invalid type choice, please enter number 1, 2, 3, 4, for type selection: returning back to main menu!");
		}
	}

	/**
	 * This method is called when the user types no 3 from the navMenu. It displays
	 * the type of books and throws an error for invalid choice
	 * 
	 * @param in scanner
	 * @return selection
	 * @throws InputMismatchException
	 */

	public static int DisplayTypeMenu(Scanner in) throws InputMismatchException {
		System.out.printf("%s%n", "#   Type");
		System.out.printf("%s%n", "1   Children's Books");
		System.out.printf("%s%n", "2   Cookbooks");
		System.out.printf("%s%n", "3   Paperbacks");
		System.out.printf("%s%n", "4   Periodicals");
		System.out.println();
		System.out.print("Enter type of book: ");

		int selection = in.nextInt();
		in.nextLine(); // discard \n

		return selection;
	}

	// "%s%n", "Enter type of book: "
	/**
	 * This method generates a RANDOM BOOKS. When the user enters the choice to
	 * produce a random book list, this method shuffles the array list and then
	 * takes the value to search random books equivalent to that value.
	 * 
	 * THIS METHOD ALSO CHECKS IF THE RANDOM NUMBERS GENERATED ARE NOT DUPLICATED
	 * !!!
	 * 
	 * 
	 * @param in Scanner
	 * @throws IndexOutOfBoundsException
	 */

	public void randomBooks(Scanner in) throws IndexOutOfBoundsException {
		// shuffle the collection
		Collections.shuffle(this.allBooks);

		// Ask user for number of random books to pick from list say = x
		System.out.print("Enter number of books:");
		Integer numOfBooks = 0;
		try { // taking the input from scanner, but if it is a string, then an exception is
				// thrown and program returns to main menu
			numOfBooks = in.nextInt();
			in.nextLine();
		} catch (IndexOutOfBoundsException e) {
			in.next(); // this is to capture the extra LN when the wrong string was entered
			System.out.println("Error: Invalid choice, please enter number of books: returning to main menu!");
			return;
		}
		if (numOfBooks.toString().length() >= 75) {
			/*
			 * if the input number of digits is incorrect (there are not more than 74 books
			 * in our text file) then throw error and return to main menu
			 */
			System.out.println("Error: Invalid choice, please enter number of books: returning to main menu!");
			return;
		}

		System.out.println("Random books: ");

		// generate x many random numbers (which will be indexes in the Arraylist)
		ArrayList<Integer> randomIndexes = new ArrayList<Integer>();
		// generate numofBooks random numbers and put them in an Arraylist and make sure
		// that the random numbers generated are not repeated
		while (randomIndexes.size() < numOfBooks) {
			int number = (int) (Math.random() * this.allBooks.size() + 1);
			if (randomIndexes.contains(number)) {
				continue; // this will make sure that we are not adding a duplicate random number into the
							// Arraylist
			} else {
				randomIndexes.add(number);
			}
		}

		/*
		 * print the books in the Arraylist for the random numbers generated in the
		 * above step and get the books at each index, checks what type of book it is,
		 * and display it (call its toString method)
		 */
		for (Integer i : randomIndexes) {
			Object book = this.allBooks.get(i);

			if (book instanceof ChildrensBook) {
				ChildrensBook chiB = (ChildrensBook) book;
				System.out.println(chiB);
			} else if (book instanceof CookBook) {
				CookBook cookB = (CookBook) book;
				System.out.println(cookB);
			} else if (book instanceof Paperback) {
				Paperback paperB = (Paperback) book;
				System.out.println(paperB);
			} else if (book instanceof Periodicals) {
				Periodicals periodB = (Periodicals) book;
				System.out.println(periodB);
			}
		}
	}

	/**
	 * EXIT AND SAVE. This method runs to exit the program and write the data back
	 * to the book file.
	 * 
	 * 
	 * @param FILE_PATH "res/books.txt
	 * @param inFile    Scanner
	 * @throws FileNotFoundException
	 */
	public void exitAndPersist(String FILE_PATH, Scanner inFile) throws FileNotFoundException {
		File file = new File(FILE_PATH);
//		if (file.exists()) {
//			System.out.println("File already exists");
//			System.exit(0);
//		}

		// Create a file
		PrintWriter output = new PrintWriter(file);

		// Write formatted output to the file
		for (Object book : this.allBooks) {
			if (book instanceof ChildrensBook) {
				ChildrensBook chiB = (ChildrensBook) book;
				output.println(chiB.getIsbn() + ";" + chiB.getCallNumber() + ";" + chiB.getAvailable() + ";"
						+ chiB.getTotal() + ";" + chiB.getTitle() + ";" + chiB.getAuthor() + ";" + chiB.getFormat());
			} else if (book instanceof CookBook) {
				CookBook cookB = (CookBook) book;
				output.println(cookB.getIsbn() + ";" + cookB.getCallNumber() + ";" + cookB.getAvailable() + ";"
						+ cookB.getTotal() + ";" + cookB.getTitle() + ";" + cookB.getPublisher() + ";"
						+ cookB.getDiet());
			} else if (book instanceof Paperback) {
				Paperback paperB = (Paperback) book;
				output.println(paperB.getIsbn() + ";" + paperB.getCallNumber() + ";" + paperB.getAvailable() + ";"
						+ paperB.getTotal() + ";" + paperB.getTitle() + ";" + paperB.getAuthor() + ";"
						+ paperB.getYear() + ";" + paperB.getGenre());
			} else if (book instanceof Periodicals) {
				Periodicals periodB = (Periodicals) book;
				output.println(periodB.getIsbn() + ";" + periodB.getCallNumber() + ";" + periodB.getAvailable() + ";"
						+ periodB.getTotal() + ";" + periodB.getTitle() + ";" + periodB.getFrequency());
			}
		}

		// Close the file
		output.close();
	}

}
