package exception;

/**
 * 
 * @author anthony
 *
 */
public class CannotParseException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9010276151115492477L;
	
	public CannotParseException() {
		super();
	}
	
	public CannotParseException(String s) {
		super(s);
	}

}
