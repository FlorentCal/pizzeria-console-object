package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

/**
 * @author Florent Callaou
 * List pizzas
 */
public class ListPizzaOptionMenu extends OptionMenu {
	
	/**
	 * Construcor
	 * @param dao
	 */
	public ListPizzaOptionMenu(IPizzaDao dao) {
		super(dao, "List of pizzas");
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.ihm.OptionMenu#execute(java.util.Scanner)
	 */
	@Override
	public void execute(Scanner sc) throws StockageException {
		listPizzas(dao.findAllPizzas());	
	}
	

}
