package fr.pizzeria.exception;

public class UpdatePizzaException extends StockageException {

	private static final long serialVersionUID = -5210740160673539378L;

	public UpdatePizzaException(String msg){
		super(msg);
	}

	public UpdatePizzaException() {
		super();
	}

	public UpdatePizzaException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public UpdatePizzaException(Throwable arg0) {
		super(arg0);
	}

	
}
