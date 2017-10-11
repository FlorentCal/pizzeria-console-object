package fr.pizzeria.ihm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.lalyos.jfiglet.FigletFont;

import fr.pizzeria.exception.StockageException;

public class PizzeriaMenu {
	
	public static final Logger LOG = LoggerFactory.getLogger(PizzeriaMenu.class);
	private String pizzeriaAdministration = FigletFont.convertOneLine("Tower   of   pizza");

	private Map<Integer, OptionMenu> menus = new HashMap<>();
	Scanner sc = new Scanner(System.in);
	
	public PizzeriaMenu(Map<Integer, OptionMenu> menus){
		this.menus = menus;
		
	}
	
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
	
	private void lineDisplay(int id, String line){
		LOG.info("{}. {}", id, line);
	}
	
}
