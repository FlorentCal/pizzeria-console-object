package fr.pizzeria.console;

import java.util.ArrayList;
import java.util.Scanner;

import fr.pizzeria.model.Pizza;

/**
 * @author Florent Callaou
 * Classe main du projet
 */
public class PizzeriaConsoleApp {
	
	// Scanner d'entrée
	private static Scanner sc = new Scanner(System.in);
	// Liste de pizzas
	private static ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
	
	/**
	 * Méthode main
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Choix de l'utilisateur dans le menu
		int choix;
		
		// Instanciations des pizzas
		Pizza peperoni = new Pizza("PEP", "Pépéroni", 12.50);
		Pizza margherita = new Pizza("MAR", "Margherita", 14.00);
		Pizza reine = new Pizza("REIN", "La Reine", 11.50);
		Pizza fromage = new Pizza("FRO", "La 4 fromages", 12.00);
		Pizza cannibale = new Pizza("CAN", "La cannibale", 12.50);
		Pizza savoyarde = new Pizza("SAV", "La savoyarde", 13.00);
		Pizza orientale = new Pizza("ORI", "L’orientale", 13.50);
		Pizza indienne = new Pizza("IND", "L’indienne", 14.00);
			
		// Ajouts des pizzas
		pizzas.add(peperoni);
		pizzas.add(margherita);
		pizzas.add(reine);
		pizzas.add(fromage);
		pizzas.add(cannibale);
		pizzas.add(savoyarde);
		pizzas.add(orientale);
		pizzas.add(indienne);
		
		// Affichage du menu
		menu();
	
		// Récupération du choix
		choix = sc.nextInt();
		
		// Tant que le choix n'est pas de sortir, on continue
		while(choix != 99){
			
			switch(choix){
			// Si choix 1 : affichage des pizzas
			case 1:
				affichage();
				
				break;
			// Si choix 2 : ajout d'une pizza
			case 2:
				pizzas.add(ajout());
				break;
			// Si choix 3 : Mise à jour d'une pizza
			case 3:
				miseAJour();
				
				break;
			// Si choix 4 : Suppression d'une pizza
			case 4:
				suppression();
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
	
	
	/**
	 * Méthode d'affichage de la liste des pizzas
	 */
	private static void affichage(){
		System.out.println("Liste des pizzas : ");
		for (Pizza pizza : pizzas) {
			System.out.println(pizza.toString());;
		}
	}
	
	
	/**
	 * Méthode d'ajout d'une pizza
	 * @return la pizza à créer dans la liste
	 */
	private static Pizza ajout(){
		System.out.println("Ajout d’une nouvelle pizza");
		
		System.out.println("Veuillez saisir le code");
		String codeTemp = sc.next();
		
		System.out.println("Veuillez saisir le nom (sans espace)");
		String nomTemp = sc.next();
		
		System.out.println("Veuillez saisir le prix (Avec une virgule pour les décimales)");
		double prixTemp = sc.nextDouble();
		
		// On retourne la pizza à créer 
		return new Pizza(codeTemp.toUpperCase(), nomTemp, prixTemp);
		
	}
	
	/**
	 * Méthode de mise à jour d'une pizza
	 */
	private static void miseAJour(){
		System.out.println("Mise à jour d’une pizza");
		affichage();
		System.out.println("Veuillez entrer le code de la pizza à modifier");
		System.out.println("(99 pour abandonner)");
		
		String codeAModifier = sc.next();
				
		if(codeAModifier.equals("99")){
			return;
		}
		
		Pizza pizzaModifiee = ajout();
		
		for (Pizza pizza : pizzas) {
			// Si le code de la pizza parcourue est égal au code que l'utilisateur à renseigné, on met à jour la pizza de la liste
			if(codeAModifier.equals(pizza.getCode())){
				pizza.setCode(pizzaModifiee.getCode());
				pizza.setNom(pizzaModifiee.getNom());
				pizza.setPrix(pizzaModifiee.getPrix());		
				break;
			}
		}
			
	}
	
	/**
	 * Méthode de suppression d'une pizza
	 */
	private static void suppression(){
		System.out.println("Suppression d’une pizza");
		affichage();
		System.out.println("Veuillez entrer le code de la pizza à modifier");
		System.out.println("(99 pour abandonner)");
		
		String codeASupprimer = sc.next();
				
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
