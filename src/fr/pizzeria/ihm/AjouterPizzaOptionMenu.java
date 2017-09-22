package fr.pizzeria.ihm;

import java.util.InputMismatchException;
import java.util.Scanner;

import fr.pizzeria.dao.PizzaPersistenceMemoire;
import fr.pizzeria.exception.SavePizzaException;
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
	public void execute(Scanner sc) throws SavePizzaException {
		
		System.out.println("Ajout d’une nouvelle pizza");
		
		System.out.println("Veuillez saisir le code");
		String codeTemp = sc.next();
		
		System.out.println("Veuillez saisir le nom (sans espace)");
		String nomTemp = sc.next();
		
		System.out.println("Veuillez saisir le prix (Avec une virgule pour les décimales)");
		double prixTemp = 0;
		try {
			prixTemp = sc.nextDouble();	
			if(!dao.saveNewPizza(new Pizza(codeTemp.toUpperCase(), nomTemp, prixTemp))){
				throw new SavePizzaException("Code déjà existant");
			}
		} catch (InputMismatchException IME){
			System.out.println(IME.getStackTrace().toString());
		}
	
	}
	
}
