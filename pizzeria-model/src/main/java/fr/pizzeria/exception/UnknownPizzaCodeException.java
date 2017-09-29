package fr.pizzeria.exception;

public class UnknownPizzaCodeException extends StockageException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	
	public UnknownPizzaCodeException(String msg) {
		super(msg);
	}

	public UnknownPizzaCodeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UnknownPizzaCodeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public UnknownPizzaCodeException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	

}
