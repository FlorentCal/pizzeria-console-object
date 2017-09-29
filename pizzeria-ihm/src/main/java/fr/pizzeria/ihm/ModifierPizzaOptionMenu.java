package fr.pizzeria.ihm;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UnknownPizzaCodeException;
//import fr.pizzeria.dao.PizzaPersistenceMemoire;
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

		CategoriePizza categoriePizza;
		int index = -1;
		while(index > 3 || index < 1){
			System.out.println("Veuillez saisir la catégorie : ");
			System.out.println("1. Viande");
			System.out.println("2. Poisson");
			System.out.println("3. Sans viande");
			index = sc.nextInt();
			if(index > 3 || index < 1) {
				System.out.println("Code inconnu (Code valable : 1, 2, 3");
			}
		}
		categoriePizza = CategoriePizza.getCategoriePizza(index);
		
		System.out.println("Veuillez saisir le code de la nouvelle pizza");
		String codeTemp = sc.next();
		
		System.out.println("Veuillez saisir le nom de la nouvelle pizza (sans espace)");
		String nomTemp = sc.next();
		
		System.out.println("Veuillez saisir le prix de la nouvelle pizza (avec une virgule pour les décimales)");
		double prixTemp = 0;
		try {
			prixTemp = sc.nextDouble();
			dao.updatePizza(codeAModifier, new Pizza(codeTemp, nomTemp, prixTemp, categoriePizza));

		} catch (InputMismatchException | StockageException e){
			System.out.println(e.getMessage());
		}
				
		
	}
	
}
