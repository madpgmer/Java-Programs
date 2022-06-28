/**
 * This CookBook class inherits from its parent Book class and has its own few attributes
 * 
 * @author: Madhu Madhavan
 * @version 6/16/2021
 */

package sait.bms.problemdomain;

public class CookBook extends Book {

	private String publisher;
	private String diet;

	public CookBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CookBook(Long isbn, String callNumber, int available, int total, String title, String publisher,
			String diet) {
		super(isbn, callNumber, available, total, title);
		this.publisher = publisher;
		this.diet = diet;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getDiet() {
		return diet;
	}
	/**
	 * This method translates the input value of a single string character to its
	 * actual name for display
	 * 
	 * @return diet
	 */

	public String getDietAndTranslate() {
		switch (this.diet) {
		case "D": {
			return "Diabetic";
		}
		case "V": {
			return "Vegetarian";
		}
		case "G": {
			return "Gluten-Free";
		}
		case "I": {
			return "International";
		}
		case "N": {
			return "None";
		}
		default: {
			return diet;
		}
		}

	}

	public void setDiet(String diet) {
		this.diet = diet;
	}

	@Override
	public String toString() {
		return "ISBN:\t\t\t\t" + getIsbn() + "\nCallNumber:\t\t\t" + getCallNumber() + "\nAvailable:\t\t\t"
				+ getAvailable() + "\nTotal:\t\t\t\t" + getTotal() + "\nTitle:\t\t\t\t" + getTitle()
				+ "\nPublisher:\t\t\t" + getPublisher() + "\nDiet:\t\t\t\t" + getDietAndTranslate() + "\n";
	}

}
