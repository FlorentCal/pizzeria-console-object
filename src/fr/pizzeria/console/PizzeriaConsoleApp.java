package fr.pizzeria.console;

import java.util.InputMismatchException;
import java.util.Scanner;

import fr.pizzeria.dao.PizzaPersistenceMemoire;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.ihm.AjouterPizzaOptionMenu;
import fr.pizzeria.ihm.ModifierPizzaOptionMenu;
import fr.pizzeria.ihm.SupprimerPizzaOptionMenu;
import fr.pizzeria.model.Pizza;

/**
 * @author Florent Callaou
 * Classe main du projet
 */
public class PizzeriaConsoleApp {
		
	/**
	 * Méthode main
	 * @param args
	 * @throws DeletePizzaException 
	 */
	public static void main(String[] args) throws Exception {
		
		// Choix de l'utilisateur dans le menu
		int choix;
		// Scanner d'entrée
		Scanner sc = new Scanner(System.in);
			
		PizzaPersistenceMemoire dao = new PizzaPersistenceMemoire();
		
		AjouterPizzaOptionMenu ajouterPizza = new AjouterPizzaOptionMenu(dao);
		ModifierPizzaOptionMenu modifierPizza = new ModifierPizzaOptionMenu(dao);
		SupprimerPizzaOptionMenu supprimerPizza = new SupprimerPizzaOptionMenu(dao);
		
		// Affichage du menu
		menu();
	
		// Récupération du choix
		choix = sc.nextInt();
		
		// Tant que le choix n'est pas de sortir, on continue
		while(choix != 99){
			
			switch(choix){
			// Si choix 1 : affichage des pizzas
			case 1:
				dao.findAllPizzas();
				break;
			// Si choix 2 : ajout d'une pizza
			case 2:
				try {
					ajouterPizza.execute(sc);
				} catch (SavePizzaException SPE) {
					System.out.println(SPE.getMessage());
				}	
				break;
			// Si choix 3 : Mise à jour d'une pizza
			case 3:
				try {
					modifierPizza.execute(sc);	
				} catch (UpdatePizzaException UPE) {
					System.out.println(UPE.getMessage());
				}		
				break;
			// Si choix 4 : Suppression d'une pizza
			case 4:
				try {
					supprimerPizza.execute(sc);
				} catch(DeletePizzaException DPE){
					System.out.println(DPE.getMessage());
				}
				break;
			case 5:
				System.out.println("Nombre de pizzas : " + Pizza.getNombrePizzas());
				break;
			// Sinon : Mauvaise entrée
			default:
				System.out.println("Mauvaise entrée");
				break;
			}
			
			menu();
			try {
				choix = sc.nextInt();	
			} catch(InputMismatchException IME){
				System.out.println(IME.getStackTrace().toString());
			}
		
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
		System.out.println("5. Connaître nombre de pizzas");
		System.out.println("99. Sortir");
	}

}
