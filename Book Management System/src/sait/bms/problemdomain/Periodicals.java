/**
 * This Periodicals class inherits from its parent Book class and has its own few attributes
 * 
 * @author: Madhu Madhavan
 * @version 6/16/2021
 */

package sait.bms.problemdomain;

public class Periodicals extends Book {

	private String frequency;

	public Periodicals() {
		super();

	}

	public Periodicals(Long isbn, String callNumber, int available, int total, String title, String frequency) {
		super(isbn, callNumber, available, total, title);
		this.frequency = frequency;

	}

	public String getFrequency() {
		return frequency;
	}
	/**
	 * This method translates the input value of a single string character to its
	 * actual name for display
	 * 
	 * @return frequency
	 */

	public String getFrequencyAndTranslate() {
		switch (this.frequency) {
		case "D": {
			return "Daily";
		}
		case "W": {
			return "Weekly";
		}
		case "M": {
			return "Monthly";
		}
		case "B": {
			return "Bi-Monthly";
		}
		case "Q": {
			return "Quarterly";
		}
		default: {
			return frequency;
		}
		}

	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	@Override
	public String toString() {
		return "ISBN:\t\t\t\t" + getIsbn() + "\nCallNumber:\t\t\t" + getCallNumber() + "\nAvailable:\t\t\t"
				+ getAvailable() + "\nTotal:\t\t\t\t" + getTotal() + "\nTitle:\t\t\t\t" + getTitle()
				+ "\nFrequency:\t\t\t" + getFrequencyAndTranslate() + "\n";
	}
}
