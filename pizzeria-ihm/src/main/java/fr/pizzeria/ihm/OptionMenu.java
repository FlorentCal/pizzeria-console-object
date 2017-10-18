package fr.pizzeria.ihm;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.PizzaCategory;

/**
 * @author Florent Callaou
 * Class of action on pizzas
 */
public abstract class OptionMenu {
	
	/** dao : IPizzaDao */
	protected IPizzaDao dao;
	/** line : String */
	protected String line;
	
	/** MIN_CHOICE : int : Le choix max du code de category */
	private static final int MIN_CHOICE = 1;
	/** MAX_CHOICE : int : Le choix min du code de category */
	private static final int MAX_CHOICE = 3;
	
	/** LOG : Logger */
	protected static final Logger LOG = LoggerFactory.getLogger(OptionMenu.class);
	
	/**
	 * OptionMenu Constructor
	 */
	public OptionMenu(IPizzaDao dao2, String line) {
		super();
		this.dao = dao2;
		this.line = line;
	}
	
	/** Getter for line
	 * @return the line
	 */
	public String getLine() {
		return line;
	}

	/**
	 * Action on a pizza
	 * @param sc : Current scanner
	 * @throws StockageException
	 */
	public abstract void execute(Scanner sc) throws StockageException;
	
	/**
	 * List pizzas
	 * @param pizzas
	 */
	protected void listPizzas(List<Pizza> pizzas) {
		if (pizzas.isEmpty()) {
			LOG.info("The pizza list is empty");
		} else {
			pizzas.stream().forEach(pizza -> LOG.info("  {}", pizza));
		}
	}
	
	/**
	 * Create a pizza with verifications
	 * @param sc
	 * @return : the pizza created
	 */
	protected Pizza pizzaCreator(Scanner sc) {
		
		PizzaCategory categoryPizza = PizzaCategory.getCategoriePizza(categoryChoice(sc));
			
		return new Pizza(codeControl(sc), nameControl(sc), priceControl(sc), categoryPizza);
	}
	
	/**
	 * Allow user to chose a category for its pizza
	 * @param sc
	 * @return : the index chosen
	 */
	private int categoryChoice(Scanner sc) {
		int index = 1;
		while(index <= MAX_CHOICE || index >= MIN_CHOICE){
			LOG.info("Please select a category : ");
			LOG.info("1. Meat");
			LOG.info("2. Fish");
			LOG.info("3. Without meat");
			
			try{
				index = sc.nextInt();
			} catch(InputMismatchException e){
				LOG.info(e.getMessage());
			}

			if(index > MAX_CHOICE || index < MIN_CHOICE) {
				LOG.info("Choix incorrect (choix valide : 1, 2, 3)");
			} else {
				break;
			}
		}
		return index;
	}

	/**
	 * Control the code entered
	 * @param sc
	 * @return : the code
	 */
	private String codeControl(Scanner sc) {
		LOG.info("Please select the new code");
		String codeTemp = sc.next();
		
		if(codeTemp.length() > 3){
			try {
				throw new StockageException("Code too long (3 caracters authorized)");
			} catch (StockageException e) {
				LOG.info(e.getMessage());
			}
		}
		
		dao.findPizzaByCode(codeTemp).ifPresent(pizza -> { 
			try {
				throw new SavePizzaException("There is already pizza with the code : " + pizza.getCode());
			} catch (SavePizzaException e) {
				LOG.info(e.getMessage());
			}
			});
		
		return codeTemp;
	}
	
	/**
	 * Control the named entered
	 * @param sc
	 * @return : the name
	 */
	private String nameControl(Scanner sc){
		LOG.info("Please select the new name (spaceless)");
		return sc.next();
	}
	
	/**
	 * Control the price entered
	 * @param sc
	 * @return : the price
	 */
	private double priceControl(Scanner sc){
		LOG.info("Please select the new price (with a comma for decimals");
		double priceTemp = 0;
		try {
			priceTemp = sc.nextDouble();
		} catch (InputMismatchException e){
			LOG.info(e.getMessage());
		}
		
		return priceTemp;
	}
	
}
