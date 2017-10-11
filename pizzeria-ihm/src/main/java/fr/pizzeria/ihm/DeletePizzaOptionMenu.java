package fr.pizzeria.ihm;

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
public class DeletePizzaOptionMenu extends OptionMenu {
	
	private static final Logger LOG = LoggerFactory.getLogger(DeletePizzaOptionMenu.class);
	
	/**
	 * DeletePizzaOptionMenu Constructor
	 * @param dao : Iinterface of pizzas
	 */
	public DeletePizzaOptionMenu(IPizzaDao dao) {
		super(dao, "Delete a pizza");
	}	

	/**
	 * Deleting a pizza
	 * @throws StockageException 
	 */
	public void execute(Scanner sc) throws StockageException {
		LOG.info("Deleting a pizza");
		
		listPizzas(dao.findAllPizzas());
				
		LOG.info("Please enter the code of the pizza to delete");
		LOG.info("(99 to quit)");

		String codeToDelete = sc.next();
		codeToDelete = codeToDelete.toUpperCase();

		if(codeToDelete.equals("99")){
			return;
		}

		try {
			dao.deletePizza(codeToDelete);

		} catch (StockageException e){
			LOG.error(e.getMessage());
		}
		
	}

}
