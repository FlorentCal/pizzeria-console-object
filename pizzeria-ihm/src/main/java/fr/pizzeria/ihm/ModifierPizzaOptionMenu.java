package fr.pizzeria.ihm;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
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

	private static final Logger LOG = LoggerFactory.getLogger(SupprimerPizzaOptionMenu.class);
	
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
	public void execute(Scanner sc) throws UpdatePizzaException, UnknownPizzaCodeException {
		
		LOG.info("Mise à jour d’une pizza");
		
		dao.findAllPizzas();
		
		LOG.info("Veuillez entrer le code de la pizza à modifier");
		LOG.info("(99 pour abandonner)");
		
		String codeAModifier = sc.next();
		codeAModifier = codeAModifier.toUpperCase();
				
		if(codeAModifier.equals("99")){
			return;
		}
		
		LOG.info("Ajout d’une nouvelle pizza");

		CategoriePizza categoriePizza;
		int index = -1;
		while(index > 3 || index < 1){
			LOG.info("Veuillez saisir la catégorie : ");
			LOG.info("1. Viande");
			LOG.info("2. Poisson");
			LOG.info("3. Sans viande");

			index = sc.nextInt();
			if(index > 3 || index < 1) {
				LOG.info("Code inconnu (Code valable : 1, 2, 3");
			}
		}
		categoriePizza = CategoriePizza.getCategoriePizza(index);
		
		LOG.info("Veuillez saisir le code de la nouvelle pizza");
		String codeTemp = sc.next();
		
		LOG.info("Veuillez saisir le nom de la nouvelle pizza (sans espace)");
		String nomTemp = sc.next();
		
		LOG.info("Veuillez saisir le prix de la nouvelle pizza (avec une virgule pour les décimales)");
		double prixTemp = 0;
		try {
			prixTemp = sc.nextDouble();
			dao.updatePizza(codeAModifier, new Pizza(codeTemp, nomTemp, prixTemp, categoriePizza));

		} catch (InputMismatchException | StockageException e){
			LOG.info(e.getMessage());
		}
				
		
	}
	
}
