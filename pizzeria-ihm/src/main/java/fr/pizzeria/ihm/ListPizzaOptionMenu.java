package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

public class ListPizzaOptionMenu extends OptionMenu {
	
	public ListPizzaOptionMenu(IPizzaDao dao) {
		super(dao, "List of pizzas");
	}

	@Override
	public void execute(Scanner sc) throws StockageException {
		listPizzas(dao.findAllPizzas());	
	}
	

}
