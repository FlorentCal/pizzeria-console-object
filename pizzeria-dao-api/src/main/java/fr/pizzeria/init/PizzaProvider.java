package fr.pizzeria.init;

import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.PizzaCategory;

public class PizzaProvider {
	
	static List<Pizza> pizzas; 
		
	public static List<Pizza> provideInitialPizzaList() {
		
		Pizza peperoni = new Pizza("PEP", "Pépéroni", 12.50, PizzaCategory.MEAT);
		Pizza margherita = new Pizza("MAR", "Margherita", 14.00, PizzaCategory.FISH);
		Pizza reine = new Pizza("REI", "La Reine", 11.50, PizzaCategory.MEAT);
		Pizza fromage = new Pizza("FRO", "La 4 fromages", 12.00, PizzaCategory.WITHOUT_MEAT);
		Pizza cannibale = new Pizza("CAN", "La cannibale", 12.50, PizzaCategory.MEAT);
		Pizza savoyarde = new Pizza("SAV", "La savoyarde", 13.00, PizzaCategory.MEAT);
		Pizza orientale = new Pizza("ORI", "L’orientale", 13.50, PizzaCategory.MEAT);
		Pizza indienne = new Pizza("IND", "L’indienne", 14.00, PizzaCategory.MEAT);
		
		pizzas = new ArrayList<>();
		
		pizzas.add(peperoni);
		pizzas.add(margherita);
		pizzas.add(reine);
		pizzas.add(fromage);
		pizzas.add(cannibale);
		pizzas.add(savoyarde);
		pizzas.add(orientale);
		pizzas.add(indienne);
		
		return pizzas;
		
	}

}
