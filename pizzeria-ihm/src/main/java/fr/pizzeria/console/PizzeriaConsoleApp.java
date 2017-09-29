package fr.pizzeria.console;

import java.util.InputMismatchException;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.ihm.AjouterPizzaOptionMenu;
import fr.pizzeria.ihm.ModifierPizzaOptionMenu;
import fr.pizzeria.ihm.SupprimerPizzaOptionMenu;
import fr.pizzeria.model.Pizza;

import java.util.logging.Logger;
/**
 * @author Florent Callaou
 * Classe main du projet
 */
public class PizzeriaConsoleApp {

	//private static final Logger LOG = LoggerFactory.getLogger(PizzeriaConsoleApp.class);
	private static Logger LOG = Logger.getAnonymousLogger();

	/**
	 * Méthode main
	 * @param args
	 * @throws DeletePizzaException 
	 */
	public static void main(String[] args) throws Exception {

		// Choix de l'utilisateur dans le menu
		int choix = 0;
		// Scanner d'entrée
		Scanner sc = new Scanner(System.in);

		IPizzaDao dao = (IPizzaDao) Class.forName("fr.pizzeria.dao.PizzaPersistenceMemoire").newInstance();

		AjouterPizzaOptionMenu ajouterPizza = new AjouterPizzaOptionMenu(dao);
		ModifierPizzaOptionMenu modifierPizza = new ModifierPizzaOptionMenu(dao);
		SupprimerPizzaOptionMenu supprimerPizza = new SupprimerPizzaOptionMenu(dao);

		// Tant que le choix n'est pas de sortir, on continue
		while(choix != 99){

			menu();
			try {
				choix = sc.nextInt();	
			} catch(InputMismatchException ime){
				LOG.info("Entrez un nombre");
			}

			switch(choix){
			// Si choix 1 : affichage des pizzas
			case 1:
				pizzaListing(dao);
				break;
				// Si choix 2 : ajout d'une pizza
			case 2:
				ajouterPizza.execute(sc);
				break;
				// Si choix 3 : Mise à jour d'une pizza
			case 3:
				pizzaListing(dao);
				modifierPizza.execute(sc);	
				break;
				// Si choix 4 : Suppression d'une pizza
			case 4:
				pizzaListing(dao);
				supprimerPizza.execute(sc);
				break;
			case 5:
				LOG.info("Nombre de pizzas : " + dao.getNombrePizzas());
				break;
				// Sinon : Mauvaise entrée
			default:
				LOG.info("Mauvaise entrée");
				break;
			}

		}

		// Sortie du programme
		LOG.info("Aurevoir \u2639");
		sc.close();

	}

	/**
	 * Méthode d'affichage du menu
	 */
	private static void menu(){
		LOG.info("***** Pizzeria Administration *****");
		LOG.info("1. Lister les pizzas");
		LOG.info("2. Ajouter une nouvelle pizza");
		LOG.info("3. Mettre à jour une pizza");
		LOG.info("4. Supprimer une pizza");
		LOG.info("5. Connaître nombre de pizzas");
		LOG.info("99. Sortir");
	}

	private static void pizzaListing(IPizzaDao dao){
		LOG.info("1. Lister les pizzas");
		for (Pizza pizza : dao.findAllPizzas()) {
			LOG.info(pizza.toString());
		}

	}

}
