/**
 * The Book Class is the parent class which has the main attributes in the program. 
 *
 * @author: Madhu Madhavan
 * @version 6/12/2021
 * 
 */

package sait.bms.problemdomain;

public class Book {

	private Long isbn;
	private String CallNumber;
	private int available;
	private int total;
	private String title;

	public Book() {
		super();

	}

	public Book(Long isbn, String callNumber, int available, int total, String title) {
		super();
		this.isbn = isbn;
		this.CallNumber = callNumber;
		this.available = available;
		this.total = total;
		this.title = title;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public String getCallNumber() {
		return CallNumber;
	}

	public void setCallNumber(String callNumber) {
		CallNumber = callNumber;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * This method decrements the availability of books by 1 when it is called from
	 * the BookManager class
	 */
	public void decrementAvailability() {
		this.available--;
	}

	@Override
	public String toString() {
		return "ISBN:\t\t\t\t" + getIsbn() + "\nCallNumber:\t\t\t" + getCallNumber() + "\nAvailable:\t\t\t"
				+ getAvailable() + "\nTotal:\t\t\t\t" + getTotal() + "\nTitle:\t\t\t\t" + getTitle() + "\n";

	}

}
