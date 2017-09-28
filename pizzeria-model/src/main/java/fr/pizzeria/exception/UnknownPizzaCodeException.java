package fr.pizzeria.exception;

public class UnknownPizzaCodeException extends StockageException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	
	public UnknownPizzaCodeException(String msg) {
		super(msg);
	}
	

}
