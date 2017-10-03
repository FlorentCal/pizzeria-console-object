package fr.pizzeria.ihm;

import java.io.IOException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

/**
 * @author Florent Callaou
 * @see OptionMenu
 *	Classe permettant de supprimer une pizza
 */
public class SupprimerPizzaOptionMenu extends OptionMenu {
	
	private static final Logger LOG = LoggerFactory.getLogger(SupprimerPizzaOptionMenu.class);
	
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
	public void execute(Scanner sc) throws StockageException {
		LOG.info("Suppression d’une pizza");
		
		dao.findAllPizzas();
		
		LOG.info("Veuillez entrer le code de la pizza à modifier");
		LOG.info("(99 pour abandonner)");

		String codeASupprimer = sc.next();
		codeASupprimer = codeASupprimer.toUpperCase();

		if(codeASupprimer.equals("99")){
			return;
		}

		try {
			dao.deletePizza(codeASupprimer);

		} catch (StockageException e){
			LOG.error(e.getMessage());
		}
		
	}

}
