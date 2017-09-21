package fr.pizzeria.model;

import java.text.DecimalFormat;


/**
 * @author Florent Callaou
 * Classe contenant les données d'une pizza
 */
public class Pizza {
	
	
	/**
	 * Identifiant unique de la pizz
	 */
	private int id;
	/**
	 * Compteur d'id
	 */
	private static int currentId;
	/**
	 * Code de la pizza
	 */
	private String code;
	/**
	 * Nom de la pizza 
	 */
	private String nom;
	/**
	 * Prix de la pizza
	 */
	private double prix;
	
	// Permet d'afficher les nombres avec 2 décimales
	DecimalFormat df = new DecimalFormat("0.00");
	
	
	/**
	 * Constructeur d'une pizza
	 * @param code : Code de la pizza
	 * @param nom : Nom de la pizza 
	 * @param prix : Prix de la pizza
	 */
	public Pizza(String code, String nom, double prix) {
		super();
		setCode(code);
		setNom(nom);
		setPrix(prix);
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return code + " -> " + nom + " (" + df.format(prix) + "€)";
	}


	
	

}
