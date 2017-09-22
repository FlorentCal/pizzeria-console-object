package fr.pizzeria.ihm;

import java.util.Scanner;

/**
 * @author Florent Callaou
 * Classe mère des actions sur liste de pizzas
 */
public abstract class OptionMenu {
	
	/**
	 * Constructeur
	 */
	public OptionMenu() {
		super();
	}
	
	public abstract void execute(Scanner sc);
		

}
