package fr.pizzeria.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SupprimerPizzaOptionMenu extends OptionMenu {

	private Scanner sc = new Scanner(System.in);
	private List<Pizza> pizzas = new ArrayList<>();
	private ListerPizzasOptionMenu lister;

	public SupprimerPizzaOptionMenu(List<Pizza> pizzas) {
		super();
		this.pizzas = pizzas;
		lister = new ListerPizzasOptionMenu(pizzas);
	}	

	/**
	 * Méthode de suppression d'une pizza
	 */

	public void execute(){
		System.out.println("Suppression d’une pizza");
		lister.execute();
		System.out.println("Veuillez entrer le code de la pizza à modifier");
		System.out.println("(99 pour abandonner)");

		String codeASupprimer = sc.next();
		codeASupprimer.toUpperCase();

		if(codeASupprimer.equals("99")){
			return;
		}

		for (Pizza pizza : pizzas) {
			// Si le code de la pizza parcourue est égal au code que l'utilisateur à renseigné, on supprim ela pizza
			if(codeASupprimer.equals(pizza.getCode())){
				pizzas.remove(pizza);	
				break;
			}
		}
	}

}
