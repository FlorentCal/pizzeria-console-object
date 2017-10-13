package fr.pizzeria.dao;

import java.util.List;
import java.util.Optional;

import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.viewModel.PizzaViewModel;

/**
 * @author Florent Callaou
 * Interface of pizzas
 */
public interface IPizzaDao {

	/**
	 * @return the list of pizzas
	 */
	List<PizzaViewModel> findAllPizzas();

	default Optional<PizzaViewModel> findPizzaByCode(String pizzaCode) {
		return findAllPizzas().stream()
				.filter(pizza -> pizza.getCode().equals(pizzaCode))
				.findAny();
	}

	/**
	 * Add a new pizza
	 * @param pizza
	 * @throws StockageException
	 */
	void saveNewPizza(PizzaViewModel pizza) throws StockageException;

	/**
	 * Update a pizza
	 * @param codePizza
	 * @param pizza
	 * @throws StockageException
	 */
	void updatePizza(String codePizza, PizzaViewModel pizza) throws StockageException;

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
