package fr.pizzeria.model;

import java.util.ArrayList;
import java.util.List;

public class ListerPizzasOptionMenu extends OptionMenu {

	private List<Pizza> pizzas = new ArrayList<>();

	public ListerPizzasOptionMenu(List<Pizza> pizzas) {
		super();
		this.pizzas = pizzas;
	}
		
	/**
	 * Méthode d'affichage de la liste des pizzas
	 */
	public void execute(){
		System.out.println("Liste des pizzas : ");
		for (Pizza pizza : pizzas) {
			System.out.println(pizza.toString());;
		}
	}
	
}
