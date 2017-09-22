package fr.pizzeria.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AjouterPizzaOptionMenu extends OptionMenu {

	private Scanner sc = new Scanner(System.in);
	private List<Pizza> pizzas = new ArrayList<>();
	
	public AjouterPizzaOptionMenu(List<Pizza> pizzas) {
		super();
		this.pizzas = pizzas;
	}	
	
	/**
	 * Méthode d'ajout d'une pizza
	 * @return la pizza à créer dans la liste
	 */
	public void execute(){
		
		System.out.println("Ajout d’une nouvelle pizza");
		
		System.out.println("Veuillez saisir le code");
		String codeTemp = sc.next();
		
		System.out.println("Veuillez saisir le nom (sans espace)");
		String nomTemp = sc.next();
		
		System.out.println("Veuillez saisir le prix (Avec une virgule pour les décimales)");
		double prixTemp = sc.nextDouble();
		
		pizzas.add(new Pizza(codeTemp, nomTemp, prixTemp));

	}
	
}
