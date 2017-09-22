package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaPersistenceMemoire;
import fr.pizzeria.exception.DeletePizzaException;

/**
 * @author Florent Callaou
 * @see OptionMenu
 *	Classe permettant de supprimer une pizza
 */
public class SupprimerPizzaOptionMenu extends OptionMenu {
	
	/**
	 * Constructeur
	 * @param pizzas : liste des pizzas commune
	 */
	public SupprimerPizzaOptionMenu(PizzaPersistenceMemoire dao) {
		super(dao);
	}	

	/**
	 * Méthode de suppression d'une pizza
	 */
	public void execute(Scanner sc) throws DeletePizzaException {
		System.out.println("Suppression d’une pizza");
		
		dao.findAllPizzas();
		
		System.out.println("Veuillez entrer le code de la pizza à modifier");
		System.out.println("(99 pour abandonner)");

		String codeASupprimer = sc.next();
		codeASupprimer = codeASupprimer.toUpperCase();

		if(codeASupprimer.equals("99")){
			return;
		}

		if(!dao.deletePizza(codeASupprimer)){
			throw new DeletePizzaException("Code inconnu");
		}	
	
	}

}
