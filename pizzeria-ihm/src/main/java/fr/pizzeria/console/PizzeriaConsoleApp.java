package fr.pizzeria.console;

import java.util.InputMismatchException;
import java.util.Scanner;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.ihm.AddPizzaOptionMenu;
import fr.pizzeria.ihm.UpdatePizzaOptionMenu;
import fr.pizzeria.ihm.DeletePizzaOptionMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author Florent Callaou
 * Classe main du projet
 */
public class PizzeriaConsoleApp {

	private static final Logger LOG = LoggerFactory.getLogger(PizzeriaConsoleApp.class);
		
	public static void main(String[] args) throws Exception {

		int choice = 0;
		Scanner sc = new Scanner(System.in);

		IPizzaDao dao = (IPizzaDao) Class.forName("fr.pizzeria.dao.PizzaPersistenceMemoire").newInstance();

		AddPizzaOptionMenu addPizza = new AddPizzaOptionMenu(dao);
		UpdatePizzaOptionMenu updatePizza = new UpdatePizzaOptionMenu(dao);
		DeletePizzaOptionMenu deletePizza = new DeletePizzaOptionMenu(dao);

		// Tant que le choix n'est pas de sortir, on continue
		while(choice != 99){

			menu();
			try {
				choice = sc.nextInt();	
			} catch(InputMismatchException ime){
				LOG.info("Enter a number");
			}

			switch(choice){
			// Si choix 1 : affichage des pizzas
			case 1:
				pizzaListing(dao);
				break;
				// Si choix 2 : ajout d'une pizza
			case 2:
				addPizza.execute(sc);
				break;
				// Si choix 3 : Mise à jour d'une pizza
			case 3:
				pizzaListing(dao);
				updatePizza.execute(sc);	
				break;
				// Si choix 4 : Suppression d'une pizza
			case 4:
				pizzaListing(dao);
				deletePizza.execute(sc);
				break;
			case 5:
				LOG.info("Number of pizzas : " + dao.getPizzasNumber());
				break;
				// Sinon : Mauvaise entrée
			default:
				LOG.warn("Bad entry");
				break;
			}

		}

		// Sortie du programme
		LOG.info("Good Bye ! \u2639");
		sc.close();

	}

	/**
	 * Méthode d'affichage du menu
	 */
	private static void menu(){
		LOG.info("***** Pizzeria Administration *****");
		LOG.info("1. Listing pizzas");
		LOG.info("2. Add a new pizza");
		LOG.info("3. Update a pizza");
		LOG.info("4. Delete a pizza");
		LOG.info("5. Number of pizzas");
		LOG.info("99. Quit");
	}

	private static void pizzaListing(IPizzaDao dao){
		LOG.info("1. Listing pizzas");
		dao.findAllPizzas().forEach(pizza -> LOG.info(pizza.toString()));

	}

}
