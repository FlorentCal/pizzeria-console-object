package fr.pizzeria.exception;

/**
 * @author Florent Callaou
 * Exception thrown when trying to search a code
 */
public class UnknownPizzaCodeException extends StockageException {
	
	private static final long serialVersionUID = -9042111564621177089L;

	public UnknownPizzaCodeException(String msg) {
		super(msg);
	}

	public UnknownPizzaCodeException() {
		super();
	}

	public UnknownPizzaCodeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public UnknownPizzaCodeException(Throwable arg0) {
		super(arg0);
	}
	

}
