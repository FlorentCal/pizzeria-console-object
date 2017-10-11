package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.PizzaCategory;
import fr.pizzeria.model.Pizza;

public class PizzaPersistenceMemoire implements IPizzaDao {


	//	private static PizzaPersistenceMemoire instanceUnique = null;
	private List<Pizza> pizzas = new ArrayList<>();

	// Instanciations des pizzas
	Pizza peperoni = new Pizza("PEP", "Pépéroni", 12.50, PizzaCategory.MEAT);
	Pizza margherita = new Pizza("MAR", "Margherita", 14.00, PizzaCategory.FISH);
	Pizza reine = new Pizza("REI", "La Reine", 11.50, PizzaCategory.MEAT);
	Pizza fromage = new Pizza("FRO", "La 4 fromages", 12.00, PizzaCategory.WITHOUT_MEAT);
	Pizza cannibale = new Pizza("CAN", "La cannibale", 12.50, PizzaCategory.MEAT);
	Pizza savoyarde = new Pizza("SAV", "La savoyarde", 13.00, PizzaCategory.MEAT);
	Pizza orientale = new Pizza("ORI", "L’orientale", 13.50, PizzaCategory.MEAT);
	Pizza indienne = new Pizza("IND", "L’indienne", 14.00, PizzaCategory.MEAT);

	/**
	 * Constructeur
	 * @param pizzas : liste des pizzas commune
	 */
	public PizzaPersistenceMemoire() {
		super();
		addPizzas();
	}

	//	public static PizzaPersistenceMemoire getInstance(){
	//		if(instanceUnique == null){
	//			instanceUnique = new PizzaPersistenceMemoire();
	//		}
	//		return instanceUnique;
	//	}

	private void addPizzas(){
		pizzas.add(peperoni);
		pizzas.add(margherita);
		pizzas.add(reine);
		pizzas.add(fromage);
		pizzas.add(cannibale);
		pizzas.add(savoyarde);
		pizzas.add(orientale);
		pizzas.add(indienne);
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
	public void updatePizza(String pizzaCode, Pizza pizzaUpdated) throws StockageException {
		
		verifyCodeLenght(pizzaUpdated.getCode());
		
		for (Pizza pizza : pizzas) {
			// Si le code de la pizza parcourue est égal au code que l'utilisateur à renseigné, on met à jour la pizza de la liste
			if(pizzaCode.equals(pizza.getCode())){
				pizza.setCode(pizzaUpdated.getCode().toUpperCase());
				pizza.setName(pizzaUpdated.getName());
				pizza.setPrice(pizzaUpdated.getPrice());		
				return;
			}
		}
		throw new UpdatePizzaException("Code inconnu");
	}

	@Override
	public void deletePizza(String pizzaCode) throws DeletePizzaException {
		for (Pizza pizza : pizzas) {
			// Si le code de la pizza parcourue est égal au code que l'utilisateur à renseigné, on supprime la pizza
			if(pizzaCode.equals(pizza.getCode())){
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
	public int getPizzasNumber() {
		return pizzas.size();
	}

}
