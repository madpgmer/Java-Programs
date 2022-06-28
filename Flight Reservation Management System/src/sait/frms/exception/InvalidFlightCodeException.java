package sait.frms.exception;
/**
 * Invalid Flight Code exception 
 * @author Madhu
 * @version June 29th 2021
 *
 */
@SuppressWarnings("serial")
public class InvalidFlightCodeException extends Exception {
	
	public InvalidFlightCodeException(String code) { 
		super("This Flight Code is invalid: " + code);
	}
}
