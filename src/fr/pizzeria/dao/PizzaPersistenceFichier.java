package fr.pizzeria.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import fr.pizzeria.model.Pizza;

public class PizzaPersistenceFichier implements IPizzaDao {

	private static PizzaPersistenceMemoire instanceUnique = null;
	
	/**
	 * Constructeur
	 * @param pizzas : liste des pizzas commune
	 */
	protected PizzaPersistenceFichier() {
		super();
	}
	
	public static PizzaPersistenceMemoire getInstance(){
		if(instanceUnique == null){
			instanceUnique = new PizzaPersistenceMemoire();
		}
		return instanceUnique;
	}

	@Override
	public List<Pizza> findAllPizzas() throws FileNotFoundException {
		File file = new File("C:/Users/ETY1/git/pizzeria-console-object/ressources/saveFile");
		FileReader reader = new FileReader(file);
		BufferedReader bufferReader = new BufferedReader(reader);
		
		
		return null;
	}

	@Override
	public boolean saveNewPizza(Pizza pizza) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza pizza) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletePizza(String codePizza) {
		// TODO Auto-generated method stub
		return false;
	}



}
