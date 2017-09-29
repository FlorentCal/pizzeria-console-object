package fr.pizzeria.dao;

import java.util.List;

import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Pizza;

public interface IPizzaDao {

	List<Pizza> findAllPizzas();
	void saveNewPizza(Pizza pizza) throws StockageException;
	void updatePizza(String codePizza, Pizza pizza) throws StockageException;
	void deletePizza(String codePizza) throws StockageException;
	int getNombrePizzas();
	
	
}
