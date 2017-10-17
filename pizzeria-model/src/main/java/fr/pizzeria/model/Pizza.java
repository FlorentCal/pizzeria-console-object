package fr.pizzeria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Florent Callaou
 * Class containing elements of a Pizza
 */
@Entity
@Table(name="Pizza")
public class Pizza {
	
	/** Integer : int */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** code : String */
	@ToString(uppercase = true, separator = "#val ->")
	@Column(name = "CODE", length = 3, nullable = false)
	private String code;

	/** name : String */
	@ToString(uppercase = true, separator = " #val")
	@Column(name = "NAME", length = 100, nullable = false)
	private String name;

	/** price : double */
	@ToString(decimalFormat = true, separator = " (#val€)")
	@Column(name = "PRICE", nullable = false)
	private double price;

	/** category : PizzaCategory */
	@ToString(separator = " [#val]")
	@Enumerated(EnumType.STRING)
	private PizzaCategory category;

	public Pizza() {
		super();
	}

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
	}
	
	public Pizza(Integer id, String code, String name, double price, PizzaCategory category) {
		this(code, name, price, category);
		this.id = id;
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
	public Integer getId() {
		return id;
	}

	/** Setter for id
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
