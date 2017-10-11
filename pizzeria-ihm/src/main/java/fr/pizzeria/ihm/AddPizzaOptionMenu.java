package fr.pizzeria.ihm;

import java.util.InputMismatchException;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UnknownPizzaCodeException;
import fr.pizzeria.model.PizzaCategory;
import fr.pizzeria.model.Pizza;

/**
 * @author Florent Callaou
 * @see OptionMenu
 *	Class Adding a pizza
 */
public class AddPizzaOptionMenu extends OptionMenu {

	private static final int MIN_CHOICE = 1;
	private static final int MAX_CHOICE = 3;
	
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
		
		LOG.info("Please select a category : ");
		LOG.info("1. Meat");
		LOG.info("2. Fish");
		LOG.info("3. Without meat");
	
		PizzaCategory categoryPizza = null;
		int index = sc.nextInt();
		if(index > MAX_CHOICE || index < MIN_CHOICE) {
			throw new UnknownPizzaCodeException("Unlnown code");
		}
		categoryPizza = PizzaCategory.getCategoriePizza(index);
		
		LOG.info("Please select the code");
		String codeTemp = sc.next();
		
		LOG.info("Please select the name (spaceless)");
		String nameTemp = sc.next();
		
		LOG.info("Please select the price (with a comma for decimals)");
		double priceTemp = 0;
		try {
			priceTemp = sc.nextDouble();	
			dao.saveNewPizza(new Pizza(codeTemp.toUpperCase(), nameTemp, priceTemp, categoryPizza));
		} catch (InputMismatchException | SavePizzaException e){
			LOG.info(e.getMessage());
		}
			
	}
	
}