package fr.pizzeria.controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.PizzaCategory;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class FXMLDocumentControllerPizza implements Initializable {

	Pizza pizzaModel;

	@FXML
	private ListView<Pizza> listViewPizzas;
	
	@FXML
    private TextField textFieldCode;
    
    @FXML
    private TextField textFieldName;
    
    @FXML
    private TextField textFieldPrice;
    
    @FXML
    private ComboBox<String> comboBoxCategory;
    
    @FXML
    Label labelInfo;
    
    @FXML
    Button yesButton;
    
    @FXML
    Button noButton;    
    
    private ObjectProperty<Double> priceConverter;
    
    IPizzaDao dao = null;
    
    private DecimalFormat df = new DecimalFormat("0.00");

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			instanciationViewModel();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		listViewPizzas.getItems().setAll(dao.findAllPizzas());
		
		cellFactories();
		
		for (PizzaCategory value : PizzaCategory.values()) {
			comboBoxCategory.getItems().add(value.getCategory());
		}
		
	}

	private void instanciationViewModel() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		dao = (IPizzaDao) Class.forName("fr.pizzeria.dao.PizzaDaoJDBC").newInstance();
	}

	private void cellFactories(){           
		listViewPizzas.setCellFactory(param -> {return getListCellListView();});
	}

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
	
	public void onClickedList(){
		Pizza pizza = listViewPizzas.getSelectionModel().selectedItemProperty().get();
		textFieldCode.setText(pizza.getCode());	
		textFieldName.setText(pizza.getName());	
		textFieldPrice.setText(String.valueOf(df.format(pizza.getPrice())));	
		comboBoxCategory.getSelectionModel().select(pizza.getCategory().getIndex() - 1);
	}
	
	public void onClickedAdd(){
		textFieldCode.clear();
		textFieldName.clear();
		textFieldPrice.clear();
		textFieldCode.setPromptText("Please enter code (3 caracters)");
		textFieldName.setPromptText("Please enter name");
		textFieldPrice.setPromptText("Please enter price");
		comboBoxCategory.getSelectionModel().select(0);
	}
	
	public void onClickSave(){

		
		try {
			dao.saveNewPizza(pizzaCreator());
		} catch (NumberFormatException | StockageException e) {
			labelInfo.setText(e.getMessage());
		}
		
		actualiseList();
	}

	public void onClickDelete(){
		labelInfo.setText("Are you sure you want to delete de pizza with the code " + textFieldCode.getText() + " ?");
		
		yesButton.setVisible(true);
		noButton.setVisible(true);
	}

	public void onClickedYes() {
		try {
			dao.deletePizza(listViewPizzas.getSelectionModel().getSelectedItem());
		} catch (StockageException e) {
			labelInfo.setText(e.getMessage());
		}
		
		actualiseList();
	}
	
	public void onClickedNo(){
		clearElements();
	}
	
	private void actualiseList() {
		dao.findAllPizzas();
		listViewPizzas.getItems().setAll(dao.findAllPizzas());
		listViewPizzas.getSelectionModel().select(0);
		onClickedList();
		clearElements();
	}
	
	public void onClickModify(){
		String previousCode = listViewPizzas.getSelectionModel().getSelectedItem().getCode();
		try {
			dao.updatePizza(previousCode, pizzaCreator());
		} catch (NumberFormatException | StockageException e) {
			labelInfo.setText(e.getMessage());
		}
		
		actualiseList();
	}
	  
	private Pizza pizzaCreator(){
		return new Pizza(
				textFieldCode.getText().toUpperCase(), 
				textFieldName.getText(), 
				Double.parseDouble(textFieldPrice.getText().replace(",", ".")), 
				PizzaCategory.valueOf(comboBoxCategory.getSelectionModel().getSelectedItem().toUpperCase()));
	}
	
	private void clearElements(){
		yesButton.setVisible(false);
		noButton.setVisible(false);
		
		labelInfo.setText("");
	}
}
