package fr.pizzeria.ihm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.pizzeria.model.Pizza;

/**
 * @author Florent Callaou
 * @see OptionMenu
 *	Classe permettant de modifier une pizza
 */
public class ModifierPizzaOptionMenu extends OptionMenu {

	private Scanner sc = new Scanner(System.in);
	private List<Pizza> pizzas = new ArrayList<>();
	
	private ListerPizzasOptionMenu lister;

	/**
	 * Constructeur
	 * @param pizzas : liste des pizzas commune
	 */
	public ModifierPizzaOptionMenu(List<Pizza> pizzas) {
		super();
		this.pizzas = pizzas;
		lister = new ListerPizzasOptionMenu(pizzas);
	}	
		
	/**
	 * Méthode de mise à jour d'une pizza
	 */
	public void execute(){
		
		System.out.println("Mise à jour d’une pizza");
		
		lister.execute();
		
		System.out.println("Veuillez entrer le code de la pizza à modifier");
		System.out.println("(99 pour abandonner)");
		
		String codeAModifier = sc.next();
		codeAModifier.toUpperCase();
				
		if(codeAModifier.equals("99")){
			return;
		}
		
		System.out.println("Ajout d’une nouvelle pizza");
		
		System.out.println("Veuillez saisir le code");
		String codeTemp = sc.next();
		
		System.out.println("Veuillez saisir le nom (sans espace)");
		String nomTemp = sc.next();
		
		System.out.println("Veuillez saisir le prix (Avec une virgule pour les décimales)");
		double prixTemp = sc.nextDouble();
				
		for (Pizza pizza : pizzas) {
			// Si le code de la pizza parcourue est égal au code que l'utilisateur à renseigné, on met à jour la pizza de la liste
			if(codeAModifier.equals(pizza.getCode())){
				pizza.setCode(codeTemp.toUpperCase());
				pizza.setNom(nomTemp);
				pizza.setPrix(prixTemp);		
				break;
			}
		}
		sc.close();
	}
	
}
