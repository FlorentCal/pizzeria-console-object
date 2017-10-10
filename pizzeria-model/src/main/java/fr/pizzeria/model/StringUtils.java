package fr.pizzeria.model;

import java.lang.reflect.Field;
import java.text.DecimalFormat;


/**
 * @author Florent Callaou 
 * Manipulation of Strings
 */
public class StringUtils {

	/** df : DecimalFormat : for displaying 2 decimals */
	static DecimalFormat df = new DecimalFormat("0.00");

	/**
	 * StringUtils Constructor
	 */
	private StringUtils(){
		
	}
	
	/**
	 * Parameterize object display
	 * @param o : l'objet à manipuler
	 * @return : La chaîne de caractères à afficher 
	 */
	public static String convert(Object o){
		Class<? extends Object> classe = o.getClass();
		StringBuilder builder = new StringBuilder();

		Field[] fields = classe.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Object value = null;
			try {
				value = field.get(o);
			} catch (IllegalArgumentException | IllegalAccessException e1) {
				e1.getMessage();
			}
			if (value != null && field.isAnnotationPresent(ToString.class)) {
				Object finalValue = field.getAnnotation(ToString.class).separator().replaceAll("#val", value.toString());
				if(field.getAnnotation(ToString.class).decimalFormat()){
					finalValue = df.format(value);
					finalValue = field.getAnnotation(ToString.class).separator().replaceAll("#val", finalValue.toString());
				}
				if(field.getAnnotation(ToString.class).uppercase()){
					builder.append(finalValue.toString().toUpperCase());
				} else {
					builder.append(finalValue);
				}
			}
		}
		return builder.toString();
	}
}
