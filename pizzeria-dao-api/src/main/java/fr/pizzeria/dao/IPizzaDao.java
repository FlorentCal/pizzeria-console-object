package fr.pizzeria.dao;

import java.util.List;

import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Pizza;

/**
 * @author Florent Callaou
 * Interface of pizzas
 */
public interface IPizzaDao {

	/**
	 * @return the list of pizzas
	 */
	List<Pizza> findAllPizzas();
	
	/**
	 * Add a new pizza
	 * @param pizza
	 * @throws StockageException
	 */
	void saveNewPizza(Pizza pizza) throws StockageException;
	
	/**
	 * Update a pizza
	 * @param codePizza
	 * @param pizza
	 * @throws StockageException
	 */
	void updatePizza(String codePizza, Pizza pizza) throws StockageException;
	
	/**
	 * Delete a pizza
	 * @param codePizza
	 * @throws StockageException
	 */
	void deletePizza(String codePizza) throws StockageException;
	
	/**
	 * @return the number of pizzas
	 */
	int getPizzasNumber();
	
	
}
