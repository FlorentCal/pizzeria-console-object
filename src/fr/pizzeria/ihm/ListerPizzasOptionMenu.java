package fr.pizzeria.ihm;

import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.model.Pizza;

/**
 * @author Florent Callaou
 * @see OptionMenu
 *	Classe permettant de lister les pizzas
 */
public class ListerPizzasOptionMenu extends OptionMenu {

	private List<Pizza> pizzas = new ArrayList<>();
	
	/**
	 * Constructeur
	 * @param pizzas : liste des pizzas commune
	 */
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
