package fr.pizzeria.ihm;

import java.util.InputMismatchException;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UnknownPizzaCodeException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.PizzaCategory;
import fr.pizzeria.model.Pizza;

/**
 * @author Florent Callaou
 * @see OptionMenu
 *	Class to update pizza
 */
public class UpdatePizzaOptionMenu extends OptionMenu {
	
	private static final int MIN_CHOICE = 1;
	private static final int MAX_CHOICE = 3;
	
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
		
		PizzaCategory categoryPizza;
		int index = -1;
		while(index > MAX_CHOICE || index < MIN_CHOICE){
			LOG.info("Please select a category : ");
			LOG.info("1. Meat");
			LOG.info("2. Fish");
			LOG.info("3. Without meat");

			index = sc.nextInt();
			if(index > 3 || index < 1) {
				throw new UnknownPizzaCodeException("Unlnown code");
			}
		}
		categoryPizza = PizzaCategory.getCategoriePizza(index);
		
		LOG.info("Please select the new code");
		String codeTemp = sc.next();
		
		LOG.info("Please select the new name (spaceless)");
		String nameTemp = sc.next();
		
		LOG.info("Please select the new price (with a comma for decimals");
		double priceTemp = 0;
		try {
			priceTemp = sc.nextDouble();
			dao.updatePizza(codeToUpdate, new Pizza(codeTemp, nameTemp, priceTemp, categoryPizza));

		} catch (InputMismatchException | StockageException e){
			LOG.info(e.getMessage());
		}
				
		
	}
	
}
