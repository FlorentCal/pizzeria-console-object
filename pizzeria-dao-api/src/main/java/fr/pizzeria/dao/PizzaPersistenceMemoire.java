package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.init.PizzaProvider;
import fr.pizzeria.model.Pizza;

/**
 * @author Florent Callaou
 * Dao persisting in memory
 */
public class PizzaPersistenceMemoire implements IPizzaDao {

	private List<Pizza> pizzas = new ArrayList<>();

	/**
	 * Constructeor
	 */
	public PizzaPersistenceMemoire() {
		super();
		pizzas = PizzaProvider.provideInitialPizzaList();
	}


	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#findAllPizzas()
	 */
	@Override
	public List<Pizza> findAllPizzas() {
		return pizzas;
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#saveNewPizza(fr.pizzeria.model.Pizza)
	 */
	@Override
	public void saveNewPizza(Pizza pizzaToAdd) throws StockageException {
		
		verifyAdd(pizzaToAdd);
		
		pizzas.add(pizzaToAdd);
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#updatePizza(java.lang.Integer, fr.pizzeria.model.Pizza)
	 */
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

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#deletePizza(fr.pizzeria.model.Pizza)
	 */
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
	
	
	/** 
	 * Verify if the pizza can be added
	 * @param pizzaToAdd
	 * @throws StockageException
	 */
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

	/**
	 * Verify the code lenght
	 * @param code
	 * @throws StockageException
	 */
	private void verifyCodeLenght(String code) throws StockageException{
		if(code.length() > 3){
			throw new StockageException("Code trop long (3 caractères autorisés)");
		}
	}


	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#close()
	 */
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}


}
