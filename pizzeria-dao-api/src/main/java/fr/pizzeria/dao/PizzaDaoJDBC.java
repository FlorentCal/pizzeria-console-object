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
import fr.pizzeria.init.PizzaProvider;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.PizzaCategory;


public class PizzaDaoJDBC implements IPizzaDao {

	private static final Logger LOG = LoggerFactory.getLogger(PizzaDaoJDBC.class);

	private String driver;
	private String url;
	private String user;
	private String password;

	List<Pizza> pizzas = new ArrayList<>();

	
	public PizzaDaoJDBC() throws SQLException, ClassNotFoundException {

		ResourceBundle bundle = ResourceBundle.getBundle("jdbc");

		driver = bundle.getString("className");

		Class.forName(driver);

		url = bundle.getString("connection");
		user = bundle.getString("user");
		password = bundle.getString("password");

		pizzas = PizzaProvider.provideInitialPizzaList();
		initializeDatabase();
	}

	public Connection connect() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	private void initializeDatabase(){

		pizzas.forEach(pizza -> insertPizza(pizza));
	}

	private void insertPizza(Pizza pizza) {

		if(!findPizzaByCode(pizza.getCode()).isPresent()){
			String query = "INSERT INTO PIZZA(CODE, NAME, PRICE, CATEGORY) VALUES (?,?,?,?)";

			try (Connection myConnection = connect(); PreparedStatement statement = myConnection.prepareStatement(query)) {

				statement.setString(1, pizza.getCode());
				statement.setString(2, pizza.getName());
				statement.setDouble(3, pizza.getPrice());
				statement.setString(4, pizza.getCategory().name());
				statement.executeUpdate();
				statement.close();
			} catch (SQLException e) {
				LOG.info("Error while creating pizza : {}", e.getMessage());
			}
		}
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
						PizzaCategory.valueOf(resultats.getString("CATEGORY").replaceAll(" ", "_").toUpperCase())
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
						PizzaCategory.valueOf(resultats.getString("CATEGORY").replaceAll(" ", "_").toUpperCase())
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
	public void updatePizza(Integer id, Pizza pizza) throws UpdatePizzaException {

		String query = "UPDATE PIZZA SET CODE=?, NAME=?, PRICE=?, CATEGORY=? WHERE ID = ?";
		try (Connection myConnection = connect(); PreparedStatement statement = myConnection.prepareStatement(query)) {
			statement.setString(1, pizza.getCode());
			statement.setString(2, pizza.getName());
			statement.setDouble(3, pizza.getPrice());
			statement.setString(4, pizza.getCategory().name());
			statement.setInt(5, id);
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
	public void close() {

	}
	

}
