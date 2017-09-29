package fr.pizzeria.exception;

public class UpdatePizzaException extends StockageException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	public UpdatePizzaException(String msg){
		super(msg);
	}

	public UpdatePizzaException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpdatePizzaException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public UpdatePizzaException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	
}
