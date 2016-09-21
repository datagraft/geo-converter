package net.datagraft.exception;

/**
 * Thrown when expected shape input is not found
 * @author nive
 *
 */
public class MissingShapeFileException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public MissingShapeFileException() {
	}

	// Constructor that accepts a message
	public MissingShapeFileException(String message) {
		super(message);
	}
}
