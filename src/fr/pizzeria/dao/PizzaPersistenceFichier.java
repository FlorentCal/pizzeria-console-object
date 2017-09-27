package fr.pizzeria.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import jdk.jfr.events.FileWriteEvent;

public class PizzaPersistenceFichier implements IPizzaDao {

	private static PizzaPersistenceFichier instanceUnique = null;
	private List<Pizza> pizzas = new ArrayList<>();
	private BufferedReader bufferReader;
	private BufferedWriter bufferWriter;
	
	/**
	 * Constructeur
	 * @param pizzas : liste des pizzas commune
	 */
	protected PizzaPersistenceFichier() {
		super();
	}
	
	public static PizzaPersistenceFichier getInstance(){
		if(instanceUnique == null){
			instanceUnique = new PizzaPersistenceFichier();
		}
		return instanceUnique;
	}

	private void loadFile() throws IOException{
		File file = new File("C:/Users/ETY1/git/pizzeria-console-object/ressources/saveFile");
		FileReader reader = new FileReader(file);
		bufferReader = new BufferedReader(reader);
		String line = null;
		String code = null;
		String name = null;
		double prix = 0;
		String categorie = null;
		String[] splittedLine;
		
		line =  bufferReader.readLine();
		while(line != null){
			splittedLine = line.split(";");
			
			code = splittedLine[0];
			name = splittedLine[1];
			prix = Double.parseDouble(splittedLine[2]);
			categorie = splittedLine[3];			
			
			pizzas.add(new Pizza(code, name, prix, CategoriePizza.valueOf(categorie)));
			
			line = bufferReader.readLine();
			
		}
		reader.close();
		bufferReader.close();
	}
	
	@Override
	public List<Pizza> findAllPizzas() throws IOException {
		loadFile();
		for (Pizza pizza : pizzas) {
			System.out.println(pizza.toString());;
		}
		return null;
	}

	@Override
	public boolean saveNewPizza(Pizza pizzaToAdd) throws IOException {
		for (Pizza pizza : pizzas) {
			if(pizza.getCode().equals(pizzaToAdd.getCode())){
				return false;
			}
		}
		
		File file = new File("C:/Users/ETY1/git/pizzeria-console-object/ressources/saveFile");
		FileWriter writer = new FileWriter(file);
		bufferWriter = new BufferedWriter(writer);
		StringBuilder sb = new StringBuilder();
		
		sb.append(pizzaToAdd.getCode() + ";");
		sb.append(pizzaToAdd.getNom() + ";");
		sb.append(pizzaToAdd.getPrix() + ";");
		sb.append(pizzaToAdd.getCategorie());
		
		bufferWriter.append(sb.toString());
		
//		writer.close();
//		bufferWriter.close();
		return true;
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

	@Override
	public int getNombrePizzas() {
		return pizzas.size();
	}



}
