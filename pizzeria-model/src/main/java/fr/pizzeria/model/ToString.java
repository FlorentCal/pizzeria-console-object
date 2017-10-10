package fr.pizzeria.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Florent Callaou
 * ToString Annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ToString {
	
	 /**
	  * fields to write with uppercase
	 */
	boolean uppercase() default false;
	
	 /**
	  * fields to write with specific decimal format
	 */
	boolean decimalFormat() default false;
	
	 /**
	  * separator of fields
	 */
	String separator() default "";
	 
}
