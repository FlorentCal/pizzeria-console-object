package fr.pizzeria.ihm;

import java.util.InputMismatchException;
import java.util.Scanner;

import fr.pizzeria.dao.PizzaPersistenceMemoire;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

/**
 * @author Florent Callaou
 * @see OptionMenu
 *	Classe permettant de modifier une pizza
 */
public class ModifierPizzaOptionMenu extends OptionMenu {

		/**
	 * Constructeur
	 * @param pizzas : liste des pizzas commune
	 */
	public ModifierPizzaOptionMenu(PizzaPersistenceMemoire dao) {
		super(dao);
	}	
		
	/**
	 * Méthode de mise à jour d'une pizza
	 */
	public void execute(Scanner sc) throws UpdatePizzaException {
		
		System.out.println("Mise à jour d’une pizza");
		
		dao.findAllPizzas();
		
		System.out.println("Veuillez entrer le code de la pizza à modifier");
		System.out.println("(99 pour abandonner)");
		
		String codeAModifier = sc.next();
		codeAModifier = codeAModifier.toUpperCase();
				
		if(codeAModifier.equals("99")){
			return;
		}
		
		System.out.println("Ajout d’une nouvelle pizza");
		
		System.out.println("Veuillez saisir le code");
		String codeTemp = sc.next();
		
		System.out.println("Veuillez saisir le nom (sans espace)");
		String nomTemp = sc.next();
		
		System.out.println("Veuillez saisir le prix (Avec une virgule pour les décimales)");
		double prixTemp = 0;
		try {
			prixTemp = sc.nextDouble();
			if(!dao.updatePizza(codeAModifier, new Pizza(codeTemp, nomTemp, prixTemp))){
				throw new UpdatePizzaException("Code inconnu");
			}

		} catch (InputMismatchException IME){
			System.out.println(IME.getStackTrace().toString());
		}
				
		
	}
	
}
