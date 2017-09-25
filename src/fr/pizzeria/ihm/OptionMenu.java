package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaPersistenceMemoire;

/**
 * @author Florent Callaou
 * Classe mère des actions sur liste de pizzas
 */
public abstract class OptionMenu {
	
	protected IPizzaDao dao = PizzaPersistenceMemoire.getInstance();
	//PizzaPersistenceMemoire dao = PizzaPersistenceMemoire.getInstance();
	
	/**
	 * Constructeur
	 */
	public OptionMenu(PizzaPersistenceMemoire dao) {
		super();
		this.dao = dao;
	}
	
	public abstract void execute(Scanner sc) throws Exception;

}
