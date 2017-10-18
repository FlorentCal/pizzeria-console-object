package fr.pizzeria.controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.PizzaCategory;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class FXMLDocumentControllerPizza implements Initializable {

	/** pizzaModel : Pizza */
	Pizza pizzaModel;

	/** listViewPizzas : ListView<Pizza> */
	@FXML
	private ListView<Pizza> listViewPizzas;
	
	/** textFieldCode : TextField */
	@FXML
    private TextField textFieldCode;
    
    /** textFieldName : TextField */
    @FXML
    private TextField textFieldName;
    
    /** textFieldPrice : TextField */
    @FXML
    private TextField textFieldPrice;
    
    /** comboBoxCategory : ComboBox<String> */
    @FXML
    private ComboBox<String> comboBoxCategory;
    
    /** labelInfo : Label */
    @FXML
    Label labelInfo;
    
    /** yesButton : Button */
    @FXML
    Button yesButton;
    
    /** noButton : Button */
    @FXML
    Button noButton;  
    
    /** quitButton : Button */
    @FXML
    Button quitButton;
            
    /** dao : IPizzaDao */
    IPizzaDao dao = null;
    
    /** df : DecimalFormat */
    private DecimalFormat df = new DecimalFormat("0.00");

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			instanciationViewModel();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			labelInfo.setText("Couldn't create Dao, contact developper");
		}
		
		listViewPizzas.getItems().setAll(dao.findAllPizzas());
		
		cellFactories();
		
		for (PizzaCategory value : PizzaCategory.values()) {
			comboBoxCategory.getItems().add(value.getCategory());
		}
		
		
	}

	/**
	 * Create an instance of DAO
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	private void instanciationViewModel() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		dao = (IPizzaDao) Class.forName("fr.pizzeria.dao.PizzaDaoJPA").newInstance();
	}

	/**
	 * Create a cellFactory to display lines in the pizza list
	 */
	private void cellFactories(){           
		listViewPizzas.setCellFactory(param -> {return getListCellListView();});
	}

	/**
	 * Create the optimized cells
	 * @return a ListCell of pizzas
	 */
	private ListCell<Pizza> getListCellListView(){
		return new ListCell<Pizza>() {
			@Override
			protected void updateItem(Pizza item, boolean empty) {
				super.updateItem(item, empty);
				if(!empty){
					textProperty().bind(Bindings.concat(item.getCode().concat("\t : ").concat(item.getName())));
				} else {
					textProperty().unbind();
					setText(null);
				}
			}


		};
	}
	
	/**
	 * Evenement handled when clicking on a pizza
	 * select the pizza
	 */
	public void onClickedList(){
		Pizza pizza = listViewPizzas.getSelectionModel().selectedItemProperty().get();
		textFieldCode.setText(pizza.getCode());	
		textFieldName.setText(pizza.getName());	
		textFieldPrice.setText(String.valueOf(df.format(pizza.getPrice())));	
		comboBoxCategory.getSelectionModel().select(pizza.getCategory().getIndex() - 1);
	}
	
	/**
	 * Evenement handled when clicking on the add button
	 * allow user to create a new pizza
	 */
	public void onClickedAdd(){
		textFieldCode.clear();
		textFieldName.clear();
		textFieldPrice.clear();
		textFieldCode.setPromptText("Please enter code (3 caracters)");
		textFieldName.setPromptText("Please enter name");
		textFieldPrice.setPromptText("Please enter price");
		comboBoxCategory.getSelectionModel().select(0);
	}
	
	/**
	 * Evenement handled when clicking the save button
	 * create a new pizza
	 */
	public void onClickSave(){
		try {
			dao.saveNewPizza(pizzaCreator());
		} catch (StockageException e) {
			labelInfo.setText(e.getMessage());
		}
		
		actualiseList();
	}
	
	/**
	 * Evenement handled when clicking on the modify button
	 * modify the selected pizza
	 */
	public void onClickModify(){
		Integer id = listViewPizzas.getSelectionModel().getSelectedItem().getId();
		try {
			dao.updatePizza(id, pizzaCreator());
		} catch (NumberFormatException | StockageException e) {
			labelInfo.setText(e.getMessage());
		}
		
		actualiseList();
	}

	/**
	 * Evenement handled when clicking on the delete button
	 * delete the selected pizza
	 */
	public void onClickDelete(){
		labelInfo.setText("Are you sure you want to delete de pizza with the code " + textFieldCode.getText() + " ?");
		
		yesButton.setVisible(true);
		noButton.setVisible(true);
	}

	/**
	 * Evenement handled when clicking on the yes button
	 * confirm delete
	 */
	public void onClickedYes() {
		try {
			dao.deletePizza(listViewPizzas.getSelectionModel().getSelectedItem());
		} catch (StockageException e) {
			labelInfo.setText(e.getMessage());
		}
		
		actualiseList();
	}
	
	/**
	 * Evenement handled when clicking on the no button
	 * cancel the delete of a pizza
	 */
	public void onClickedNo(){
		clearElements();
	}
	
	/**
	 * actualise list after action on the pizzas list
	 */
	private void actualiseList() {
		dao.findAllPizzas();
		listViewPizzas.getItems().setAll(dao.findAllPizzas());
		listViewPizzas.getSelectionModel().select(0);
		onClickedList();
		clearElements();
	}
	  
	/**
	 * Create a new pizza (model)
	 * @return : the pizza
	 */
	private Pizza pizzaCreator(){
		return new Pizza(
				textFieldCode.getText().toUpperCase(), 
				textFieldName.getText(), 
				Double.parseDouble(textFieldPrice.getText().replace(",", ".")), 
				PizzaCategory.valueOf(comboBoxCategory.getSelectionModel().getSelectedItem().replaceAll(" ", "_").toUpperCase()));
	}
	
	/**
	 * Clear fields
	 */
	private void clearElements(){
		yesButton.setVisible(false);
		noButton.setVisible(false);
		
		labelInfo.setText("");
	}
	
	/**
	 * Evenement handled when clicking on the quit button
	 * Quit the application (close the EMF for JPA)
	 */
	public void onCloseApplication(){
		dao.close();
		Platform.exit();
		System.exit(0);
		
	}
	

}
