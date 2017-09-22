package fr.pizzeria.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.pizzeria.ihm.AjouterPizzaOptionMenu;
import fr.pizzeria.ihm.ListerPizzasOptionMenu;
import fr.pizzeria.ihm.ModifierPizzaOptionMenu;
import fr.pizzeria.ihm.SupprimerPizzaOptionMenu;
import fr.pizzeria.model.Pizza;

/**
 * @author Florent Callaou
 * Classe main du projet
 */
public class PizzeriaConsoleApp {
	
	// Scanner d'entrée
	private static Scanner sc = new Scanner(System.in);
		
	/**
	 * Méthode main
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Choix de l'utilisateur dans le menu
		int choix;
		List<Pizza> pizzas = new ArrayList<>();
		
		// Instanciations des pizzas
		Pizza peperoni = new Pizza("PEP", "Pépéroni", 12.50);
		Pizza margherita = new Pizza("MAR", "Margherita", 14.00);
		Pizza reine = new Pizza("REIN", "La Reine", 11.50);
		Pizza fromage = new Pizza("FRO", "La 4 fromages", 12.00);
		Pizza cannibale = new Pizza("CAN", "La cannibale", 12.50);
		Pizza savoyarde = new Pizza("SAV", "La savoyarde", 13.00);
		Pizza orientale = new Pizza("ORI", "L’orientale", 13.50);
		Pizza indienne = new Pizza("IND", "L’indienne", 14.00);
			
		pizzas.add(peperoni);
		pizzas.add(margherita);
		pizzas.add(reine);
		pizzas.add(fromage);
		pizzas.add(cannibale);
		pizzas.add(savoyarde);
		pizzas.add(orientale);
		pizzas.add(indienne);
		
		ListerPizzasOptionMenu listerPizzas = new ListerPizzasOptionMenu(pizzas);
		AjouterPizzaOptionMenu ajouterPizza = new AjouterPizzaOptionMenu(pizzas);
		ModifierPizzaOptionMenu modifierPizza = new ModifierPizzaOptionMenu(pizzas);
		SupprimerPizzaOptionMenu supprimerPizza = new SupprimerPizzaOptionMenu(pizzas);
		
		// Affichage du menu
		menu();
	
		// Récupération du choix
		choix = sc.nextInt();
		
		// Tant que le choix n'est pas de sortir, on continue
		while(choix != 99){
			
			switch(choix){
			// Si choix 1 : affichage des pizzas
			case 1:
				listerPizzas.execute();
				
				break;
			// Si choix 2 : ajout d'une pizza
			case 2:
				ajouterPizza.execute();
				
				break;
			// Si choix 3 : Mise à jour d'une pizza
			case 3:
				modifierPizza.execute();
				
				break;
			// Si choix 4 : Suppression d'une pizza
			case 4:
				supprimerPizza.execute();
				break;
			// Sinon : Mauvaise entrée
			default:
				System.out.println("Mauvaise entrée");
				break;
			}
			
			menu();
			choix = sc.nextInt();			
			
		}
		
		// Sortie du programme
		System.out.println("Aurevoir \u2639");
		sc.close();
		
	}
		
	/**
	 * Méthode d'affichage du menu
	 */
	private static void menu(){
		System.out.println("***** Pizzeria Administration *****");
		System.out.println("1. Lister les pizzas");
		System.out.println("2. Ajouter une nouvelle pizza");
		System.out.println("3. Mettre à jour une pizza");
		System.out.println("4. Supprimer une pizza");
		System.out.println("99. Sortir");
	}

}
