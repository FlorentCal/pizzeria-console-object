package fr.pizzeria.ihm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.lalyos.jfiglet.FigletFont;

import fr.pizzeria.exception.StockageException;

/**
 * @author Florent Callaou
 * Menu console
 */
public class PizzeriaMenu {
	
	/** LOG : Logger */
	public static final Logger LOG = LoggerFactory.getLogger(PizzeriaMenu.class);
	/** pizzeriaAdministration : String : Stylish display */
	private String pizzeriaAdministration = FigletFont.convertOneLine("Tower   of   pizza");

	/** menus : Map<Integer,OptionMenu> */
	private Map<Integer, OptionMenu> menus = new HashMap<>();
	/** sc : Scanner */
	Scanner sc = new Scanner(System.in);
	
	/**
	 * Constructor
	 * @param menus
	 */
	public PizzeriaMenu(Map<Integer, OptionMenu> menus){
		this.menus = menus;
		
	}
	
	/**
	 * Display the menu for the user
	 * @throws StockageException
	 */
	public void menuDisplay() throws StockageException{
		int choice = 0;
		
		LOG.info(pizzeriaAdministration);
		
		while(true){
			
			menus.forEach((id, menu) -> lineDisplay(id, menu.getLine()));
			lineDisplay(99, "Quit");
			
			choice = sc.nextInt();
			if(choice == 99){
				LOG.info("GoodBye ! \uD83C\uDF55  <- This is a slice of pizza");
				break;
			}
			
			menus.get(choice).execute(sc);
			
		}
		
	}
	
	/**
	 * Display one line
	 * @param id : the id of the line
	 * @param line 
	 */
	private void lineDisplay(int id, String line){
		LOG.info("{}. {}", id, line);
	}
	
}
