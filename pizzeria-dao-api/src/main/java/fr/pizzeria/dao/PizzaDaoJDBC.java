package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.PizzaCategory;


public class PizzaDaoJDBC implements IPizzaDao {

	private static final Logger LOG = LoggerFactory.getLogger(PizzaDaoJDBC.class);

	private String driver;
	private String url;
	private String user;
	private String password;

	List<Pizza> pizzas = new ArrayList<>();

	Pizza peperoni = new Pizza("PEP", "Pépéroni", 12.50, PizzaCategory.MEAT);
	Pizza margherita = new Pizza("MAR", "Margherita", 14.00, PizzaCategory.FISH);
	Pizza reine = new Pizza("REI", "La Reine", 11.50, PizzaCategory.MEAT);
	Pizza fromage = new Pizza("FRO", "La 4 fromages", 12.00, PizzaCategory.WITHOUT_MEAT);
	Pizza cannibale = new Pizza("CAN", "La cannibale", 12.50, PizzaCategory.MEAT);
	Pizza savoyarde = new Pizza("SAV", "La savoyarde", 13.00, PizzaCategory.MEAT);
	Pizza orientale = new Pizza("ORI", "L’orientale", 13.50, PizzaCategory.MEAT);
	Pizza indienne = new Pizza("IND", "L’indienne", 14.00, PizzaCategory.MEAT);

	public PizzaDaoJDBC() throws SQLException, ClassNotFoundException {

		ResourceBundle bundle = ResourceBundle.getBundle("jdbc");

		driver = bundle.getString("className");

		Class.forName(driver);

		url = bundle.getString("connection");
		user = bundle.getString("user");
		password = bundle.getString("password");

		initializeDatabase();
	}

	public Connection connect() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	private void initializeDatabase(){
		pizzas.add(peperoni);
		pizzas.add(margherita);
		pizzas.add(reine);
		pizzas.add(fromage);
		pizzas.add(cannibale);
		pizzas.add(savoyarde);
		pizzas.add(orientale);
		pizzas.add(indienne);

		for (PizzaCategory pizzaCategory : PizzaCategory.values()) {
			insertPizzaCategory(pizzaCategory);
		}
		
		pizzas.forEach(pizza -> insertPizza(pizza));
	}

	private void insertPizza(Pizza pizza) {

		if(!findPizzaByCode(pizza.getCode()).isPresent()){
			String query = "INSERT INTO PIZZA(CODE, NAME, PRICE, ID_CAT) VALUES (?,?,?,?)";

			try (Connection myConnection = connect(); PreparedStatement statement = myConnection.prepareStatement(query)) {

				statement.setString(1, pizza.getCode());
				statement.setString(2, pizza.getName());
				statement.setDouble(3, pizza.getPrice());
				statement.setInt(4, PizzaCategory.valueOf(pizza.getCategory().name()).getIndex());
				statement.executeUpdate();
				statement.close();
			} catch (SQLException e) {
				LOG.info("Error while creating pizza : {}", e.getMessage());
			}
		}
	}
	
	private void insertPizzaCategory(PizzaCategory pizzaCategory) {

		if(!findPizzaCategoryByLibelle(pizzaCategory.getCategory()).isPresent()){
			String query = "INSERT INTO CATEGORY(LIBELLE) VALUES (?)";

			try (Connection myConnection = connect(); PreparedStatement statement = myConnection.prepareStatement(query)) {
				statement.setString(1, pizzaCategory.getCategory());
				statement.executeUpdate();
				statement.close();
			} catch (SQLException e) {
				LOG.info("Error while creating pizza category : {}", e.getMessage());
			}
		}
	}

	public Optional<PizzaCategory> findPizzaCategoryByLibelle(String libelle) {
		String query = "SELECT * FROM CATEGORY WHERE LIBELLE = \"" + libelle + "\";";
		PizzaCategory pizzaCategoryFound = null;

		try(Connection myConnection = connect(); PreparedStatement statement = myConnection.prepareStatement(query); ResultSet resultats = statement.executeQuery();){
			if(resultats.next()){
				pizzaCategoryFound = PizzaCategory.valueOf(resultats.getString("LIBELLE").replace(" ", "_").toUpperCase());
			}
		} catch (SQLException e) {
			LOG.info("Error while loading pizza category : {}", e.getMessage());
		}
		
		return Optional.ofNullable(pizzaCategoryFound);
	}
	
	@Override
	public Optional<Pizza> findPizzaByCode(String code) {
		String query = "SELECT * FROM PIZZA WHERE CODE = \"" + code + "\";";
		Pizza pizzaFound = null;

		try(Connection myConnection = connect(); PreparedStatement statement = myConnection.prepareStatement(query); ResultSet resultats = statement.executeQuery();){
			if(resultats.next()){
				pizzaFound = new Pizza(
						resultats.getString("CODE"),
						resultats.getString("NAME"), 
						resultats.getDouble("PRICE"), 
						PizzaCategory.getCategoriePizza(resultats.getInt("ID_CAT"))
						);	
			}
		} catch (SQLException e) {
			LOG.info("Error while loading pizza : {}", e.getMessage());
		}
		
		return Optional.ofNullable(pizzaFound);
	}

	@Override
	public List<Pizza> findAllPizzas() {
		pizzas = new ArrayList<>();
		String query = "SELECT * FROM PIZZA";

		try(Connection myConnection = connect(); PreparedStatement statement = myConnection.prepareStatement(query); ResultSet resultats = statement.executeQuery();){
			while(resultats.next()) {

				pizzas.add(new Pizza(
						resultats.getString("CODE"),
						resultats.getString("NAME"),
						resultats.getDouble("PRICE"),
						PizzaCategory.getCategoriePizza(resultats.getInt("ID_CAT"))
						));							
			}
		} catch (SQLException e) {
			LOG.info("Error while loading pizza : {}", e.getMessage());
		} 

		return pizzas;
	}

	@Override
	public void saveNewPizza(Pizza pizzaToAdd) throws SavePizzaException {
		insertPizza(pizzaToAdd);
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {

		String query = "UPDATE PIZZA SET CODE=?, NAME=?, PRICE=?, ID_CAT=? WHERE CODE = ?";
		try (Connection myConnection = connect(); PreparedStatement statement = myConnection.prepareStatement(query)) {
			statement.setString(1, pizza.getCode());
			statement.setString(2, pizza.getName());
			statement.setDouble(3, pizza.getPrice());
			statement.setInt(4, PizzaCategory.valueOf(pizza.getCategory().name()).getIndex());
			statement.setString(5, codePizza);
			int linesChanged = statement.executeUpdate();
			statement.close();

			if (linesChanged == 0) {
				throw new UpdatePizzaException("Failed to update pizza");
			}
		} catch (SQLException e) {
			LOG.info("Error while updating pizza : {}", e.getMessage());
		}

	}

	@Override
	public void deletePizza(Pizza pizzaToDelete) throws DeletePizzaException {
		String query = "DELETE FROM PIZZA WHERE CODE = ?";

		try (Connection myConnection = connect(); PreparedStatement statement = myConnection.prepareStatement(query)) {
			statement.setString(1, pizzaToDelete.getCode());
			int linesChanged = statement.executeUpdate();
			statement.close();

			if (linesChanged == 0) {
				throw new DeletePizzaException("Failed to delete pizza");
			}
		} catch (SQLException e) {
			LOG.info("Error while deleting pizza : {}", e.getMessage());
		}
	}

	@Override
	public int getPizzasNumber() {
		return 0;
	}

}
