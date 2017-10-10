package fr.pizzeria.exception;

/**
 * @author Florent Callaou
 * Exception thrown when trying to add a pizza
 */
public class SavePizzaException extends StockageException {

	private static final long serialVersionUID = 5098304493322570567L;

	public SavePizzaException(String msg){
		super(msg);
	}

	public SavePizzaException() {
		super();
	}

	public SavePizzaException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public SavePizzaException(Throwable arg0) {
		super(arg0);
	}
	
	

}
