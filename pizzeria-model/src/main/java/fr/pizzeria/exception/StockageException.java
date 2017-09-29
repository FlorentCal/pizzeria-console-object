package fr.pizzeria.exception;

public class StockageException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	
	public StockageException(String msg){
		super(msg);
	}

	public StockageException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StockageException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public StockageException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	
}
