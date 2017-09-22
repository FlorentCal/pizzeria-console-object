package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaPersistenceMemoire implements IPizzaDao {

	
	private static PizzaPersistenceMemoire instanceUnique = null;
	private List<Pizza> pizzas = new ArrayList<>();

	// Instanciations des pizzas
	Pizza peperoni = new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE);
	Pizza margherita = new Pizza("MAR", "Margherita", 14.00, CategoriePizza.POISSON);
	Pizza reine = new Pizza("REIN", "La Reine", 11.50, CategoriePizza.VIANDE);
	Pizza fromage = new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE);
	Pizza cannibale = new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE);
	Pizza savoyarde = new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE);
	Pizza orientale = new Pizza("ORI", "L’orientale", 13.50, CategoriePizza.VIANDE);
	Pizza indienne = new Pizza("IND", "L’indienne", 14.00, CategoriePizza.VIANDE);
	
	/**
	 * Constructeur
	 * @param pizzas : liste des pizzas commune
	 */
	protected PizzaPersistenceMemoire() {
		super();
		addPizzas();
	}
	
	public static PizzaPersistenceMemoire getInstance(){
		if(instanceUnique == null){
			instanceUnique = new PizzaPersistenceMemoire();
		}
		return instanceUnique;
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
	public List<Pizza> findAllPizzas() {
		System.out.println("Liste des pizzas : ");
		for (Pizza pizza : pizzas) {
			System.out.println(pizza.toString());;
		}
		return null;
	}

	@Override
	public boolean saveNewPizza(Pizza pizzaToAdd) {
		for (Pizza pizza : pizzas) {
			if(pizza.getCode().equals(pizzaToAdd.getCode())){
				return false;
			}
		}
		pizzas.add(pizzaToAdd);
		return true;
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza pizzaUpdated) {
		for (Pizza pizza : pizzas) {
			// Si le code de la pizza parcourue est égal au code que l'utilisateur à renseigné, on met à jour la pizza de la liste
			if(codePizza.equals(pizza.getCode())){
				pizza.setCode(pizzaUpdated.getCode().toUpperCase());
				pizza.setNom(pizzaUpdated.getNom());
				pizza.setPrix(pizzaUpdated.getPrix());		
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deletePizza(String codePizza) {
		for (Pizza pizza : pizzas) {
			// Si le code de la pizza parcourue est égal au code que l'utilisateur à renseigné, on supprime la pizza
			if(codePizza.equals(pizza.getCode())){
				pizzas.remove(pizza);	
				return true;
			}
		}
		return false;
	}
	
	public int getNombrePizzas(){
		return pizzas.size();
	}

}
