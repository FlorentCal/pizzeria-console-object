package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UnknownPizzaCodeException;
import fr.pizzeria.exception.UpdatePizzaException;

/**
 * @author Florent Callaou
 * @see OptionMenu
 *	Class to update pizza
 */
public class UpdatePizzaOptionMenu extends OptionMenu {
	
	
	/**
	 * UpdatePizzaOptionMenu Constructor
	 * @param pizzas : liste des pizzas commune
	 */
	public UpdatePizzaOptionMenu(IPizzaDao dao) {
		super(dao, "Update a pizza");
	}
		
	/**
	 * Update a pizza
	 * @throws UpdatePizzaException 
	 * @throws UnknownPizzaCodeException 
	 */
	public void execute(Scanner sc) throws UpdatePizzaException, UnknownPizzaCodeException {
		
		LOG.info("Updating a pizza");
		
		listPizzas(dao.findAllPizzas());
		
		LOG.info("Please select the code of the pizza to update");
		LOG.info("(99 to quit)");
		
		String codeToUpdate = sc.next();
		codeToUpdate = codeToUpdate.toUpperCase();
				
		if(codeToUpdate.equals("99")){
			return;
		}
						
		try {
			dao.updatePizza(codeToUpdate, pizzaCreator(sc));
		} catch (StockageException e) {
			LOG.info(e.getMessage());
		}
		
	}
	
}
