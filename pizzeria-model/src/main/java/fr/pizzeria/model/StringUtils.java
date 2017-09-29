package fr.pizzeria.model;

import java.lang.reflect.Field;
import java.text.DecimalFormat;


/**
 * @author Florent Callaou 
 * Classe permettant de manipuler les String
 */
public class StringUtils {

	// Permet d'afficher les nombres avec 2 décimales
	static DecimalFormat df = new DecimalFormat("0.00");

	private StringUtils(){
		
	}
	
	/**
	 * Méthode de paramétrage d'affichage d'un objet
	 * @param o : l'objet à manipuler
	 * @return : La chaîne de caractères à afficher 
	 */
	public static String convert(Object o){
		Class<? extends Object> classe = o.getClass();
		StringBuilder builder = new StringBuilder();

		// Récupération des attributs de l'objet
		Field[] fields = classe.getDeclaredFields();
		for (Field field : fields) {
			// Pour pouvoir laisser les modifiers à private (Sinon : IllegalAccessException)
			field.setAccessible(true);
			// Valeur de l'attribut
			Object value = null;
			try {
				// Récupération de la valeur
				value = field.get(o);
			} catch (IllegalArgumentException | IllegalAccessException e1) {
				e1.getMessage();
			}
			// Si la valeur est différent de null et qu'on lui a annoté ToString : on l'affiche 
			if (value != null && field.isAnnotationPresent(ToString.class)) {

				Object finalValue = field.getAnnotation(ToString.class).separator().replaceAll("#val", value.toString());
				// Vérification du paramètre de ToString
				if(field.getAnnotation(ToString.class).decimalFormat()){
					finalValue = df.format(value);
					finalValue = field.getAnnotation(ToString.class).separator().replaceAll("#val", finalValue.toString());
				}
				// Vérification du paramètre de ToString
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
