package br.com.grrecurso.core.search.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import br.com.grrecurso.core.search.FieldTextOperations;
import br.com.grrecurso.core.search.FieldTextPresentation;

@Retention(RetentionPolicy.RUNTIME)
public @interface FieldTextFilter {
	String label();
	String campo() default "";
	FieldTextOperations[] operacao();
	FieldTextPresentation apresentacao();
	int maxLength() default 40;
	int size() default 40;
	boolean obrigatorio() default false;
	boolean fixo() default false;
}
