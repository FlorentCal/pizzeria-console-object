package fr.pizzeria.model;

/**
 * @author Florent Callaou
 * Enum for categories of pizzas
 */
public enum PizzaCategory {

	MEAT("Meat", 1),
	FISH("Fish", 2),
	WITHOUT_MEAT("Without meat", 3);
	
	/** category : String */
	private String category;
	
	/** index : int */
	private int index;
	
	/**
	 * PizzaCategory Constructor
	 * @param category : the category of a pizza
	 * @param index : the index of the category
	 */
	private PizzaCategory(String category, int index) {
		this.category = category;
		this.index = index;
	}
	
		
	/** Getter for category
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/** Getter for index
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Get the category for a given index
	 * @param index : index of the category
	 * @return : the category
	 */
	public static PizzaCategory getCategoriePizza(int index) {
		for (PizzaCategory element : values()) {
			if(element.getIndex() == index){
				return element;
			}
		}
		return null;
	}
	
}
