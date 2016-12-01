package br.com.grrecurso.core.search.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ConfiguracaoPesquisa {
	String rowsPerPageTemplate() default "10,25,50,100";
	String varAttr() default "row";
}