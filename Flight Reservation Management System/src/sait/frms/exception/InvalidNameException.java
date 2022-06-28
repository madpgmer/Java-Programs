package sait.frms.exception;
/**
 * Invalid Name exception when the field is null
 * @author Madhu
 * @version June 29th 2021
 *
 */
@SuppressWarnings("serial")
public class InvalidNameException extends Exception {

	public InvalidNameException() {
		super("The name is Invalid (null or empty) ");
	}
}
