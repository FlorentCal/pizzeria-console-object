package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

/**
 * @author Florent Callaou
 * Classe mère des actions sur liste de pizzas
 */
public abstract class OptionMenu {
	
	protected IPizzaDao dao;
	
	/**
	 * Constructeur
	 */
	public OptionMenu(IPizzaDao dao2) {
		super();
		this.dao = dao2;
	}
	
	public abstract void execute(Scanner sc) throws StockageException;

}
