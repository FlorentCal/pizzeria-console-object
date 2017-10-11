package fr.pizzeria.ihm;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Pizza;

/**
 * @author Florent Callaou
 * Class of action on pizzas
 */
public abstract class OptionMenu {
	
	protected IPizzaDao dao;
	protected String line;
	
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
	
	protected void listPizzas(List<Pizza> pizzas) {
		if (pizzas.isEmpty()) {
			LOG.info("The pizza list is empty");
		} else {
			pizzas.stream().forEach(pizza -> LOG.info("  {}", pizza));
		}
	}

}
