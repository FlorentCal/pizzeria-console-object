package fr.pizzeria.controller;

import java.net.URL;
import java.util.ResourceBundle;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.viewModel.PizzaViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.converter.DoubleStringConverter;

public class FXMLDocumentControllerPizza implements Initializable {

	
	//TODO Delete ViewModel and replace binding with PizzaDAO
	PizzaViewModel pizzaViewModel;

	@FXML
	private ListView<PizzaViewModel> listViewPizzas;
	
	@FXML
    private TextField textFieldCode;
    
    @FXML
    private TextField textFieldName;
    
    @FXML
    private TextField textFieldPrice;
    
    private ObjectProperty<Double> priceConverter;
    
    IPizzaDao dao = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			instanciationViewModel();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		listViewPizzas.getItems().setAll(dao.findAllPizzas());
		
		cellFactories();

		bindListView();	

	}

	private void instanciationViewModel() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		dao = (IPizzaDao) Class.forName("fr.pizzeria.dao.PizzaPersistenceMemoire").newInstance();
	}

	private void cellFactories(){           
		listViewPizzas.setCellFactory(param -> {return getListCellListView();});
	}
	
	private void bindListView(){
        listViewPizzas.getSelectionModel().selectedItemProperty().addListener((o, old, newV) -> {
            if (old != null){
                textFieldCode.textProperty().unbindBidirectional(old.codeProperty());
                textFieldName.textProperty().unbindBidirectional(old.nameProperty());
                textFieldPrice.textProperty().unbindBidirectional(priceConverter);
                                                
            }
            if(newV != null){
            	priceConverter = newV.priceProperty().asObject();
                
                textFieldCode.textProperty().bindBidirectional(newV.codeProperty());
                textFieldName.textProperty().bindBidirectional(newV.nameProperty());
                textFieldPrice.textProperty().bindBidirectional(priceConverter, new DoubleStringConverter());
                                
                pizzaViewModel = newV;
            }
        });
    }

	private ListCell<PizzaViewModel> getListCellListView(){
		return new ListCell<PizzaViewModel>() {

			@Override
			protected void updateItem(PizzaViewModel item, boolean empty) {
				super.updateItem(item, empty);
				if(!empty){
					textProperty().bind(item.codeProperty().concat(" -> ").concat(item.nameProperty()));
				} else {
					textProperty().unbind();
					setText(null);
				}
			}


		};
	}

}
