package fr.pizzeria.exception;

public class SavePizzaException extends StockageException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SavePizzaException(String msg){
		super(msg);
	}

	public SavePizzaException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SavePizzaException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public SavePizzaException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	

}
