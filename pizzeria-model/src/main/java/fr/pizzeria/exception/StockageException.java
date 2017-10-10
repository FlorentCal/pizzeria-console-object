package fr.pizzeria.exception;

/**
 * @author Florent Callaou
 * Exception thrown when performing an action on a pizza
 */
public class StockageException extends Exception {
	
	private static final long serialVersionUID = 2037331187913174367L;

	public StockageException(String msg){
		super(msg);
	}

	public StockageException() {
		super();
	}

	public StockageException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public StockageException(Throwable arg0) {
		super(arg0);
	}
	
	
}
