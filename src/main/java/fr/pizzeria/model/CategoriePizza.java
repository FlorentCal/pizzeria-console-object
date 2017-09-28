package fr.pizzeria.model;

public enum CategoriePizza {

	VIANDE("Viande", 1),
	POISSON("Poisson", 2),
	SANS_VIANDE("Sans Viande", 3);
	
	private String categorie;
	private int index;
	
	
	private CategoriePizza(String categorie, int index) {
		this.categorie = categorie;
		this.index = index;
	}
	
	public String getCategorie(){
		return categorie;
	}
	
	public int getIndex() {
		return index;
	}
	
	public static CategoriePizza getCategoriePizza(int index) {
		for (CategoriePizza element : values()) {
			if(element.getIndex() == index){
				return element;
			}
		}
		return null;

	}
	
}
