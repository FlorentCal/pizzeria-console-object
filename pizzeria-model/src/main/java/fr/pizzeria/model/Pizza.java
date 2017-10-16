package fr.pizzeria.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * @author Florent Callaou
 * Class containing elements of a Pizza
 */
public class Pizza {
	
	/** id : int */
	private int id;
	public static final String PROP_ID = "id";

	/** currentId : int : L'id actuel des pizzas */
	private static int currentId = 1;
	public static final String PROP_CURRENT_ID = "currentId";

	/** code : String */
	@ToString(uppercase = true, separator = "#val ->")
	private String code;
	public static final String PROP_CODE = "code";

	/** name : String */
	@ToString(uppercase = true, separator = " #val")
	private String name;
	public static final String PROP_NAME = "name";

	/** price : double */
	@ToString(decimalFormat = true, separator = " (#val€)")
	private double price;
	public static final String PROP_PRICE = "price";

	/** category : PizzaCategory */
	@ToString(separator = " [#val]")
	private PizzaCategory category;
	public static final String PROP_CATEGORY = "category";

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
		int oldId = this.id;
		this.id = id;
		propertyChangeSupport.firePropertyChange(PROP_ID, oldId, id);
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
		String oldCode = this.code;
		this.code = code;
		propertyChangeSupport.firePropertyChange(PROP_CODE, oldCode, code);
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
		String oldName = this.name;
		this.name = name;
		propertyChangeSupport.firePropertyChange(PROP_NAME, oldName, name);
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
		double oldPrice = this.price;
		this.price = price;
		propertyChangeSupport.firePropertyChange(PROP_PRICE, oldPrice, price);
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
		PizzaCategory oldCategory = this.category;
		this.category = category;
		propertyChangeSupport.firePropertyChange(PROP_CATEGORY, oldCategory, category);
	}

	private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	/**
	 * Add PropertyChangeListener.
	 *
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	/**
	 * Remove PropertyChangeListener.
	 *
	 * @param listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
}
