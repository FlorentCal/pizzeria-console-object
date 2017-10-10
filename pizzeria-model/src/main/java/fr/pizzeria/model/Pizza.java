package fr.pizzeria.model;

/**
 * @author Florent Callaou
 * Class containing elements of a Pizza
 */
public class Pizza {


	/** id : int */
	private int id;
	
	/** currentId : int : L'id actuel des pizzas */
	private static int currentId;
	
	/** code : String */
	@ToString(uppercase = true, separator = "#val ->")
	private String code;
	
	/** name : String */
	@ToString(uppercase = true, separator = " #val")
	private String name;
	
	/** price : double */
	@ToString(decimalFormat = true, separator = " (#val€)")
	private double price;

	/** category : PizzaCategory */
	@ToString(separator = " [#val]")
	private PizzaCategory category;

	/**
	 * Pizza Constructor
	 * @param code : Code of the pizza
	 * @param nom : Name of the pizza
	 * @param price : Price of the pizza
	 */
	public Pizza(String code, String name, double price, PizzaCategory category) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.category = category;
		setId(currentId);
		currentId++;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {	
		return StringUtils.convert(this);
	}	
	
	/** Getter for id
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/** Setter for id
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/** Getter for currentId
	 * @return the currentId
	 */
	public static int getCurrentId() {
		return currentId;
	}

	/** Setter for currentId
	 * @param currentId the currentId to set
	 */
	public static void setCurrentId(int currentId) {
		Pizza.currentId = currentId;
	}

	/** Getter for code
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/** Setter for code
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/** Getter for name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/** Setter for name
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** Getter for price
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/** Setter for price
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/** Getter for category
	 * @return the category
	 */
	public PizzaCategory getCategory() {
		return category;
	}

	/** Setter for category
	 * @param category the category to set
	 */
	public void setCategory(PizzaCategory category) {
		this.category = category;
	}

}
