package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

/**
 * @author Florent Callaou
 * Class of action on pizzas
 */
public abstract class OptionMenu {
	
	protected IPizzaDao dao;
	
	/**
	 * OptionMenu Constructor
	 */
	public OptionMenu(IPizzaDao dao2) {
		super();
		this.dao = dao2;
	}
	
	/**
	 * Action on a pizza
	 * @param sc : Current scanner
	 * @throws StockageException
	 */
	public abstract void execute(Scanner sc) throws StockageException;

}
