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
			// Nom de l'attribut
			String name = field.getName();
			// Valeur de l'attribut
			Object value = null;
			try {
				// Récupération de la valeur
				value = field.get(o);
			} catch (IllegalArgumentException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
			// Si la valeur est différent de null et qu'on lui a annoté ToString : on l'affiche 
			if (value != null && field.isAnnotationPresent(ToString.class)) {
				// Vérification du paramètre de ToString
				if(field.getAnnotation(ToString.class).decimalFormat()){
					builder.append(df.format(value));
					builder.append(" €) [");
				}else {
					// Vérification du paramètre de ToString
					if(field.getAnnotation(ToString.class).uppercase()){
						builder.append(value.toString().toUpperCase());
					} else {
						builder.append(value);
					}
				}					
				if(name.equals("code")){
					builder.append(" -> ");
				} else if(name.equals("nom")){
					builder.append(" (");
				} else if(name.equals("categorie")){
					builder.append("]");
				}		
			}
		}
		return builder.toString();
	}
}
