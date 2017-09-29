package fr.pizzeria.ihm;

import java.io.IOException;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

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
	public SupprimerPizzaOptionMenu(IPizzaDao dao) {
		super(dao);
	}	

	/**
	 * Méthode de suppression d'une pizza
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws StockageException 
	 */
	public void execute(Scanner sc) throws IOException, ClassNotFoundException, StockageException {
		System.out.println("Suppression d’une pizza");
		
		dao.findAllPizzas();
		
		System.out.println("Veuillez entrer le code de la pizza à modifier");
		System.out.println("(99 pour abandonner)");

		String codeASupprimer = sc.next();
		codeASupprimer = codeASupprimer.toUpperCase();

		if(codeASupprimer.equals("99")){
			return;
		}

		try {
			dao.deletePizza(codeASupprimer);

		} catch (StockageException e){
			System.out.println(e.getMessage());
		}
		
	}

}
