package sait.frms.exception;


/**
 * Invalid Citizenship exception when the field is null
 * @author Madhu
 * @version June 29th 2021
 *
 */
@SuppressWarnings("serial")
public class InvalidCitizenshipException extends Exception {
	public InvalidCitizenshipException() {
		super("The citizenship is Invalid (null or empty) ");
	}
}
