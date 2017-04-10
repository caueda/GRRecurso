package br.com.grrecurso.core.search.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import br.com.grrecurso.core.search.FieldComboSelectOperations;

@Retention(RetentionPolicy.RUNTIME)
public @interface FieldComboSelectFilter {
	String label();
	String campo() default "";
	FieldComboSelectOperations[] operacao() default FieldComboSelectOperations.SELECT_IGUAL;
	Class<?> classe();
	boolean obrigatorio() default false;
	boolean fixo() default false;
}
