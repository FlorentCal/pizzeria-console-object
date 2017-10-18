package fr.pizzeria.console;

import java.util.HashMap;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.ihm.AddPizzaOptionMenu;
import fr.pizzeria.ihm.UpdatePizzaOptionMenu;
import fr.pizzeria.ihm.DeletePizzaOptionMenu;
import fr.pizzeria.ihm.ListPizzaOptionMenu;
import fr.pizzeria.ihm.OptionMenu;
import fr.pizzeria.ihm.PizzeriaMenu;

/**
 * @author Florent Callaou
 * Console application
 */
public class PizzeriaConsoleApp {
		
	public static void main(String[] args) throws Exception {
		
		IPizzaDao dao = (IPizzaDao) Class.forName("fr.pizzeria.dao.PizzaDaoJDBC").newInstance();
		
		HashMap<Integer, OptionMenu> menus = new HashMap<>();		
		menus.put(1, new ListPizzaOptionMenu(dao));
		menus.put(2, new AddPizzaOptionMenu(dao));
		menus.put(3, new UpdatePizzaOptionMenu(dao));
		menus.put(4, new DeletePizzaOptionMenu(dao));
				
		PizzeriaMenu menu = new PizzeriaMenu(menus);
		
		menu.menuDisplay();
	}

}
