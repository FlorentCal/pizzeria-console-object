package fr.pizzeria.ihm;

import java.util.Optional;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Pizza;

/**
 * @author Florent Callaou
 * @see OptionMenu
 *	Classe permettant de supprimer une pizza
 */
public class DeletePizzaOptionMenu extends OptionMenu {
	
	private static final String QUIT_CODE = "99";
	
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
		LOG.info("({} to quit)", QUIT_CODE);

		String codeToDelete = sc.next();
		codeToDelete = codeToDelete.toUpperCase();

		if(codeToDelete.equals(QUIT_CODE)){
			return;
		}
			
		Optional<Pizza> pizza = dao.findPizzaByCode(codeToDelete);
		if(!pizza.isPresent()){
			throw new DeletePizzaException("Aucune pizza avec le code : " + codeToDelete);
		}

		try {
			dao.deletePizza(pizza.get());

		} catch (StockageException e){
			LOG.error(e.getMessage());
		}
		
		LOG.info("La pizza avec le code {} a été supprimée", codeToDelete);
		
	}

}
