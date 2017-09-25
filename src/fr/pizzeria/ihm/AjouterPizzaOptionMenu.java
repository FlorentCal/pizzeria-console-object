package fr.pizzeria.ihm;

import java.util.InputMismatchException;
import java.util.Scanner;

import fr.pizzeria.dao.PizzaPersistenceMemoire;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UnknownPizzaCodeException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * @author Florent Callaou
 * @see OptionMenu
 *	Classe permettant d'ajouter une pizza
 */
public class AjouterPizzaOptionMenu extends OptionMenu {
	
	/**
	 * Constructeur
	 * @param pizzas : liste des pizzas commune
	 */
	public AjouterPizzaOptionMenu(PizzaPersistenceMemoire dao) {
		super(dao);
	}	
	
	/**
	 * Méthode d'ajout d'une pizza
	 */
	public void execute(Scanner sc) throws SavePizzaException, UnknownPizzaCodeException {
		
		System.out.println("Ajout d’une nouvelle pizza");
		
		System.out.println("Veuillez saisir la catégorie : ");
		System.out.println("1. Viande");
		System.out.println("2. Poisson");
		System.out.println("3. Sans viande");
		CategoriePizza categoriePizza = null;
		int index = sc.nextInt();
		if(index > 3 || index < 1) {
			throw new UnknownPizzaCodeException("Code inconnu");
		}
		categoriePizza = CategoriePizza.getCategoriePizza(index);
		
		System.out.println("Veuillez saisir le code");
		String codeTemp = sc.next();
		if(codeTemp.length() > 3){
			throw new SavePizzaException("Code trop long");
		}
		
		System.out.println("Veuillez saisir le nom (sans espace)");
		String nomTemp = sc.next();
		
		System.out.println("Veuillez saisir le prix (Avec une virgule pour les décimales)");
		double prixTemp = 0;
		try {
			prixTemp = sc.nextDouble();	
			if(!dao.saveNewPizza(new Pizza(codeTemp.toUpperCase(), nomTemp, prixTemp, categoriePizza))){
				throw new SavePizzaException("Code déjà existant");
			}
		} catch (InputMismatchException IME){
			System.out.println("Entrez un nombre");
		}
			
	}
	
}
