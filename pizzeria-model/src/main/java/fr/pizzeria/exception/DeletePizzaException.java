package fr.pizzeria.exception;

public class DeletePizzaException extends StockageException {

	private static final long serialVersionUID = 2769821308584269270L;

	public DeletePizzaException(String msg) {
		super(msg);
	}
	
	public DeletePizzaException() {
		super();
	}

	public DeletePizzaException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DeletePizzaException(Throwable arg0) {
		super(arg0);
	}

	
}
