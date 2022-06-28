/**
 * This ChildrensBook class inherits from its parent Book class and has its own few attributes
 * 
 * @author: Madhu Madhavan
 * @version 6/15/2021
 */

package sait.bms.problemdomain;

public class ChildrensBook extends Book {

	private String author;
	private String format;

	public ChildrensBook() {
		super();

	}

	public ChildrensBook(Long isbn, String callNumber, int available, int total, String title, String author,
			String format) {
		super(isbn, callNumber, available, total, title);
		this.author = author;
		this.format = format;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getFormat() {
		return format;
	}

	/**
	 * This method translates the input value of a single string character to its
	 * actual name for display
	 * 
	 * @return format
	 */
	public String getFormatAndTranslate() {
		switch (this.format) {
		case "P": {
			return "Picture book";
		}
		case "E": {
			return "Early Readers";
		}
		case "C": {
			return "Chapter book";
		}
		default: {
			return format;
		}
		}
	}

	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	public String toString() {
		return "ISBN:\t\t\t\t" + getIsbn() + "\nCallNumber:\t\t\t" + getCallNumber() + "\nAvailable:\t\t\t"
				+ getAvailable() + "\nTotal:\t\t\t\t" + getTotal() + "\nTitle:\t\t\t\t" + getTitle()
				+ "\nAuthor:\t\t\t\t" + getAuthor() + "\nformat:\t\t\t\t" + getFormatAndTranslate() + "\n";

	}

}
