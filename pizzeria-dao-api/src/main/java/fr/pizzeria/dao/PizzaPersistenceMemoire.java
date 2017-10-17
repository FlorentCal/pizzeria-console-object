package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.init.PizzaProvider;
import fr.pizzeria.model.PizzaCategory;
import fr.pizzeria.model.Pizza;

public class PizzaPersistenceMemoire implements IPizzaDao {

	private List<Pizza> pizzas = new ArrayList<>();

	/**
	 * Constructeur
	 * @param pizzas : liste des pizzas commune
	 */
	public PizzaPersistenceMemoire() {
		super();
		pizzas = PizzaProvider.provideInitialPizzaList();
	}


	@Override
	public List<Pizza> findAllPizzas() {
		return pizzas;
	}

	@Override
	public void saveNewPizza(Pizza pizzaToAdd) throws StockageException {
		
		verifyAdd(pizzaToAdd);
		
		pizzas.add(pizzaToAdd);
	}

	@Override
	public void updatePizza(Integer id, Pizza pizzaUpdated) throws StockageException {
		
		verifyCodeLenght(pizzaUpdated.getCode());
		
		for (Pizza pizza : pizzas) {
			// Si le code de la pizza parcourue est égal au code que l'utilisateur à renseigné, on met à jour la pizza de la liste
			if(id.equals(pizza.getId())){
				pizza.setCode(pizzaUpdated.getCode().toUpperCase());
				pizza.setName(pizzaUpdated.getName());
				pizza.setPrice(pizzaUpdated.getPrice());		
				return;
			}
		}
		throw new UpdatePizzaException("Code inconnu");
	}

	@Override
	public void deletePizza(Pizza pizzaToDelete) throws DeletePizzaException {
		for (Pizza pizza : pizzas) {
			// Si le code de la pizza parcourue est égal au code que l'utilisateur à renseigné, on supprime la pizza
			if(pizzaToDelete.getCode().equals(pizza.getCode())){
				pizzas.remove(pizza);	
				return;
			}
		}
		throw new DeletePizzaException("Code inconnu");
	}
	private void verifyAdd(Pizza pizzaToAdd) throws StockageException{
		verifyCodeLenght(pizzaToAdd.getCode());
		if(pizzaToAdd.getCode().length() > 3){
			throw new SavePizzaException("Code trop long (3 caractères autorisés)");
		}
		for (Pizza pizza : pizzas) {
			if(pizza.getCode().equals(pizzaToAdd.getCode())){
				throw new SavePizzaException("Code déjà existant");
			}
		}
	}

	private void verifyCodeLenght(String code) throws StockageException{
		if(code.length() > 3){
			throw new StockageException("Code trop long (3 caractères autorisés)");
		}
	}


	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}


}
