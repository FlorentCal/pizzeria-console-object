package fr.pizzeria.ihm;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UnknownPizzaCodeException;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.PizzaCategory;
import fr.pizzeria.viewModel.PizzaViewModel;

/**
 * @author Florent Callaou
 * Class of action on pizzas
 */
public abstract class OptionMenu {
	
	protected IPizzaDao dao;
	protected String line;
	
	private static final int MIN_CHOICE = 1;
	private static final int MAX_CHOICE = 3;
	
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
	
	protected void listPizzas(List<PizzaViewModel> pizzas) {
		if (pizzas.isEmpty()) {
			LOG.info("The pizza list is empty");
		} else {
			pizzas.stream().forEach(pizza -> LOG.info("  {}", pizza));
		}
	}
	
	protected PizzaViewModel pizzaCreator(Scanner sc) throws UnknownPizzaCodeException{
		
		PizzaCategory categoryPizza = PizzaCategory.getCategoriePizza(categoryChoice(sc));
			
		return new PizzaViewModel(codeControl(sc), nameControl(sc), priceControl(sc));
	}
	
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

	private String codeControl(Scanner sc) {
		LOG.info("Please select the new code");
		String codeTemp = sc.next();
		
		dao.findPizzaByCode(codeTemp).ifPresent(pizza -> { 
			try {
				throw new SavePizzaException("There is already pizza with the code : " + pizza.getCode());
			} catch (SavePizzaException e) {
				LOG.info(e.getMessage());
			}
			});
		
		return codeTemp;
	}
	
	private String nameControl(Scanner sc){
		LOG.info("Please select the new name (spaceless)");
		return sc.next();
	}
	
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
