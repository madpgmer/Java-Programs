/**
 * This Paperback class inherits from its parent Book class and has its own few attributes
 * 
 * @author: Madhu Madhavan
 * @version 6/16/2021
 */

package sait.bms.problemdomain;

public class Paperback extends Book {

	private String author;
	private String year;
	private String genre;

	public Paperback() {
		super();

	}

	public Paperback(Long isbn, String callNumber, int available, int total, String title, String author, String year,
			String genre) {
		super(isbn, callNumber, available, total, title);
		this.author = author;
		this.year = year;
		this.genre = genre;

	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}
	
	/**
	 * This method translates the input value of a single string character to its
	 * actual name for display
	 * 
	 * @return genre
	 */

	public String getGenreAndTranslate() {
		switch (this.genre) {
		case "A": {
			return "Adventure";
		}
		case "D": {
			return "Drama";
		}
		case "E": {
			return "Education";
		}
		case "C": {
			return "Classic";
		}
		case "F": {
			return "Fantasy";
		}
		case "S": {
			return "Science Fiction";
		}
		default: {
			return genre;
		}
		}

	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "ISBN:\t\t\t\t" + getIsbn() + "\nCallNumber:\t\t\t" + getCallNumber() + "\nAvailable:\t\t\t"
				+ getAvailable() + "\nTotal:\t\t\t\t" + getTotal() + "\nTitle:\t\t\t\t" + getTitle()
				+ "\nAuthor:\t\t\t\t" + getAuthor() + "\nYear:\t\t\t\t" + getYear() + "\nGenre:\t\t\t\t"
				+ getGenreAndTranslate() + "\n";
	}

}
