package fr.pizzeria.viewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.PizzaCategory;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Florent Callaou
 * Class containing elements of a Pizza
 */
public class PizzaViewModel implements PropertyChangeListener {

	private static final PizzaCategory PizzaCategory = null;

	private Pizza model;

	private final StringProperty code = new SimpleStringProperty();
		public String getCode() {return code.get();}
		public final void setCode(String value) {code.set(value);}
		public StringProperty codeProperty() {return code;}

	private final StringProperty name = new SimpleStringProperty();   
		public String getName() {return name.get();}
		public final void setName(String value) {name.set(value);}
		public StringProperty nameProperty() {return name;}

	private final DoubleProperty price = new SimpleDoubleProperty();
		public double getPrice() {return price.get();}
		public final void setPrice(double value) {price.set(value);}
		public DoubleProperty priceProperty() {return price;}

	private final ObjectProperty<PizzaCategory> category = new SimpleObjectProperty<>();
		public PizzaCategory getCategory() {return category.get();}
		public final void setCategory(PizzaCategory value) {category.set(value);}
		public ObjectProperty categoryProperty() {return category;}

	public PizzaViewModel(String newCode, String newName, double newPrice) {

		model = new Pizza(newCode, newName, newPrice, PizzaCategory.MEAT);

		setCode(model.getCode());
		setName(model.getName());
		setPrice(model.getPrice());
		setCategory(model.getCategory());

		code.addListener((o, old, newV) -> model.setCode(newV));
		name.addListener((o, old, newV) -> model.setName(newV));
		price.addListener((o, old, newV) -> model.setPrice(Double.parseDouble(newV.toString())));

		model.addPropertyChangeListener(this);

	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		if(evt.getPropertyName().equals(Pizza.PROP_CODE)){
			code.set(evt.getNewValue().toString());
		}
		if(evt.getPropertyName().equals(Pizza.PROP_NAME)){
			name.set(evt.getNewValue().toString());
		}
		if(evt.getPropertyName().equals(Pizza.PROP_PRICE)){
			price.set(Double.parseDouble(evt.getNewValue().toString()));
		}
		if(evt.getPropertyName().equals(Pizza.PROP_CATEGORY)){
			category.set(PizzaCategory.valueOf(evt.getNewValue().toString()));
		}
	}




}
