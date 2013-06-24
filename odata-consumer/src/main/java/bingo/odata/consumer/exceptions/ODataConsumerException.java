package bingo.odata.consumer.exceptions;

public abstract class ODataConsumerException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2782581833325406051L;

	public ODataConsumerException() {
		super();
	}
	
	public ODataConsumerException(String message) {
		super(message);
	}
	
	public ODataConsumerException(Throwable arg0) {
		super(arg0);
	}
	
	public ODataConsumerException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
