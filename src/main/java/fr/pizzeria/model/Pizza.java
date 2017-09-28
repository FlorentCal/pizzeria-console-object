package fr.pizzeria.model;

/**
 * @author Florent Callaou
 * Classe contenant les données d'une pizza
 */
public class Pizza {

	/**
	 * Identifiant unique de la pizza
	 */
	private int id;
	/**
	 * Compteur d'id
	 */
	private static int currentId;
	/**
	 * Code de la pizza
	 */
	@ToString(uppercase = true, separator = "#val ->")
	private String code;
	/**
	 * Nom de la pizza 
	 */
	@ToString(uppercase = true, separator = " #val")
	private String nom;
	/**
	 * Prix de la pizza
	 */
	@ToString(decimalFormat = true, separator = " (#val€)")
	private double prix;

	@ToString(separator = " [#val]")
	private CategoriePizza categorie;

	/**
	 * Constructeur d'une pizza
	 * @param code : Code de la pizza
	 * @param nom : Nom de la pizza 
	 * @param prix : Prix de la pizza
	 */
	public Pizza(String code, String nom, double prix, CategoriePizza categorie) {
		super();
		setCode(code);
		setNom(nom);
		setPrix(prix);
		setCategorie(categorie);
		setId(currentId);
		currentId++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public CategoriePizza getCategorie() {
		return categorie;
	}

	public void setCategorie(CategoriePizza categorie) {
		this.categorie = categorie;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {	
		return StringUtils.convert(this);
	}	


}
