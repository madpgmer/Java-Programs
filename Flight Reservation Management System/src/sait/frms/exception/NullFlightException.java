package sait.frms.exception;
/**
 * Flight full exception when the field is null
 * @author Madhu
 * @version June 29th 2021
 *
 */
@SuppressWarnings("serial")
public class NullFlightException extends Exception {
	public NullFlightException() {
		super("The flight is null! ");
	}
}
