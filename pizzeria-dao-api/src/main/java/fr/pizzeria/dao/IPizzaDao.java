package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.PizzaCategory;

/**
 * @author Florent Callaou
 * Interface of pizzas
 */
public interface IPizzaDao {
	
	/**
	 * @return the list of pizzas
	 */
	List<Pizza> findAllPizzas();

	default Optional<Pizza> findPizzaByCode(String pizzaCode) {
		return findAllPizzas().stream()
				.filter(pizza -> pizza.getCode().equals(pizzaCode))
				.findAny();
	}

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
	void updatePizza(Integer id, Pizza pizza) throws StockageException;

	/**
	 * Delete a pizza
	 * @param codePizza
	 * @throws StockageException
	 */
	void deletePizza(Pizza pizza) throws StockageException;
	
	void close();


}
