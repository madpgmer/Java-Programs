package sait.frms.exception;
/**
 * No more seats exception when the field is null
 * @author Madhu
 * @version June 29th 2021
 */
@SuppressWarnings("serial")
public class NoMoreSeatsException extends Exception {
	
	public NoMoreSeatsException() {
		super("The are no more seats for this flight! ");
	}
}
