package fr.pizzeria.ihm;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaPersistenceMemoire;
import fr.pizzeria.exception.UnknownPizzaCodeException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
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
	public ModifierPizzaOptionMenu(IPizzaDao dao) {
		super(dao);
	}
		
	/**
	 * Méthode de mise à jour d'une pizza
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public void execute(Scanner sc) throws UpdatePizzaException, UnknownPizzaCodeException, IOException, ClassNotFoundException {
		
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
		
		System.out.println("Veuillez saisir la catégorie : ");
		System.out.println("1. Viande");
		System.out.println("2. Poisson");
		System.out.println("3. Sans viande");
		CategoriePizza categoriePizza;
		int index = sc.nextInt();
		if(index > 3 || index < 1) {
			throw new UnknownPizzaCodeException("Code inconnu");
		}
		categoriePizza = CategoriePizza.getCategoriePizza(index);
		
		System.out.println("Veuillez saisir le code");
		String codeTemp = sc.next();
		if(codeTemp.length() > 3){
			throw new UpdatePizzaException("Code trop long");
		}
		
		System.out.println("Veuillez saisir le nom (sans espace)");
		String nomTemp = sc.next();
		
		System.out.println("Veuillez saisir le prix (Avec une virgule pour les décimales)");
		double prixTemp = 0;
		try {
			prixTemp = sc.nextDouble();
			if(!dao.updatePizza(codeAModifier, new Pizza(codeTemp, nomTemp, prixTemp, categoriePizza))){
				throw new UpdatePizzaException("Code inconnu");
			}

		} catch (InputMismatchException IME){
			System.out.println(IME.getStackTrace().toString());
		}
				
		
	}
	
}
