package fr.pizzeria.dao;

import java.io.FileNotFoundException;
import java.util.List;

import fr.pizzeria.model.Pizza;

public interface IPizzaDao {

	List<Pizza> findAllPizzas() throws FileNotFoundException;
	boolean saveNewPizza(Pizza pizza);
	boolean updatePizza(String codePizza, Pizza pizza);
	boolean deletePizza(String codePizza);
	
}
