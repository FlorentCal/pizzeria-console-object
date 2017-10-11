package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;

/**
 * @author Florent Callaou
 * @see OptionMenu
 *	Class Adding a pizza
 */
public class AddPizzaOptionMenu extends OptionMenu {

	/**
	 * AddPizzaOptionMenu Constructor
	 * @param pizzas : liste des pizzas commune
	 */
	public AddPizzaOptionMenu(IPizzaDao dao) {
		super(dao, "Add a new pizza");
	}	

	/**
	 * Adding a pizza
	 * @throws StockageException 
	 */
	public void execute(Scanner sc) throws StockageException {

		LOG.info("Adding a new pizza");

		listPizzas(dao.findAllPizzas());

		try {	
			dao.saveNewPizza(pizzaCreator(sc));
		} catch (SavePizzaException e){
			LOG.info(e.getMessage());
		}

	}

}
