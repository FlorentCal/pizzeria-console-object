package fr.pizzeria.dao;

import java.io.IOException;
import java.util.List;

import fr.pizzeria.model.Pizza;

public interface IPizzaDao {

	List<Pizza> findAllPizzas() throws IOException;
	boolean saveNewPizza(Pizza pizza) throws IOException;
	boolean updatePizza(String codePizza, Pizza pizza);
	boolean deletePizza(String codePizza);
	int getNombrePizzas();
	
}
