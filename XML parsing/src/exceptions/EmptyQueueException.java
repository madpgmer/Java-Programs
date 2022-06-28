package exceptions;

/**
 * 
 * @author Madhu
 *         Exceptions in the program and extends the Exceptions class.
 *
 */

@SuppressWarnings("serial")
public class EmptyQueueException extends Exception {

	public EmptyQueueException(String message) {
		super(message);
	}

}
