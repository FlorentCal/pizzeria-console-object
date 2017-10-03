package fr.pizzeria.ihm;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UnknownPizzaCodeException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * @author Florent Callaou
 * @see OptionMenu
 *	Classe permettant d'ajouter une pizza
 */
public class AjouterPizzaOptionMenu extends OptionMenu {
	
	private static final Logger LOG = LoggerFactory.getLogger(AjouterPizzaOptionMenu.class);
	
	/**
	 * Constructeur
	 * @param pizzas : liste des pizzas commune
	 */
	public AjouterPizzaOptionMenu(IPizzaDao dao) {
		super(dao);
	}	
	
	/**
	 * Méthode d'ajout d'une pizza
	 * @throws StockageException 
	 * @throws IOException 
	 */
	public void execute(Scanner sc) throws StockageException {
		
		LOG.info("Ajout d’une nouvelle pizza");
		
		LOG.info("Veuillez saisir la catégorie : ");
		LOG.info("1. Viande");
		LOG.info("2. Poisson");
		LOG.info("3. Sans viande");
	
		CategoriePizza categoriePizza = null;
		int index = sc.nextInt();
		if(index > 3 || index < 1) {
			throw new UnknownPizzaCodeException("Code inconnu");
		}
		categoriePizza = CategoriePizza.getCategoriePizza(index);
		
		LOG.info("Veuillez saisir le code");
		String codeTemp = sc.next();
		
		LOG.info("Veuillez saisir le nom (sans espace)");
		String nomTemp = sc.next();
		
		LOG.info("Veuillez saisir le prix (Avec une virgule pour les décimales)");
		double prixTemp = 0;
		try {
			prixTemp = sc.nextDouble();	
			dao.saveNewPizza(new Pizza(codeTemp.toUpperCase(), nomTemp, prixTemp, categoriePizza));
		} catch (InputMismatchException | SavePizzaException e){
			LOG.info(e.getMessage());
		}
			
	}
	
}
