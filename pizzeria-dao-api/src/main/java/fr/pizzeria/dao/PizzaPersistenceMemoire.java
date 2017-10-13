package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.PizzaCategory;
import fr.pizzeria.viewModel.PizzaViewModel;
import fr.pizzeria.model.Pizza;

public class PizzaPersistenceMemoire implements IPizzaDao {

	private List<PizzaViewModel> pizzas = new ArrayList<>();

	PizzaViewModel peperoni = new PizzaViewModel("PEP", "Pépéroni", 12.50);
	PizzaViewModel margherita = new PizzaViewModel("MAR", "Margherita", 14.00);
	PizzaViewModel reine = new PizzaViewModel("REI", "La Reine", 11.50);
	PizzaViewModel fromage = new PizzaViewModel("FRO", "La 4 fromages", 12.00);
	PizzaViewModel cannibale = new PizzaViewModel("CAN", "La cannibale", 12.50);
	PizzaViewModel savoyarde = new PizzaViewModel("SAV", "La savoyarde", 13.00);
	PizzaViewModel orientale = new PizzaViewModel("ORI", "L’orientale", 13.50);
	PizzaViewModel indienne = new PizzaViewModel("IND", "L’indienne", 14.00);

	/**
	 * Constructeur
	 * @param pizzas : liste des pizzas commune
	 */
	public PizzaPersistenceMemoire() {
		super();
		addPizzas();
	}

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
	public List<PizzaViewModel> findAllPizzas() {
		return pizzas;
	}

	@Override
	public void saveNewPizza(PizzaViewModel pizzaToAdd) throws StockageException {
		
		verifyAdd(pizzaToAdd);
		
		pizzas.add(pizzaToAdd);
	}

	@Override
	public void updatePizza(String pizzaCode, PizzaViewModel pizzaUpdated) throws StockageException {
		
		verifyCodeLenght(pizzaUpdated.getCode());
		
		for (PizzaViewModel pizza : pizzas) {
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
		for (PizzaViewModel pizza : pizzas) {
			// Si le code de la pizza parcourue est égal au code que l'utilisateur à renseigné, on supprime la pizza
			if(pizzaCode.equals(pizza.getCode())){
				pizzas.remove(pizza);	
				return;
			}
		}
		throw new DeletePizzaException("Code inconnu");
	}
	private void verifyAdd(PizzaViewModel pizzaToAdd) throws StockageException{
		verifyCodeLenght(pizzaToAdd.getCode());
		if(pizzaToAdd.getCode().length() > 3){
			throw new SavePizzaException("Code trop long (3 caractères autorisés)");
		}
		for (PizzaViewModel pizza : pizzas) {
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
