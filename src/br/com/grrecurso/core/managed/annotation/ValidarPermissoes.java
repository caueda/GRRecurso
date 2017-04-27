package br.com.grrecurso.core.managed.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ValidarPermissoes {
	String[] permissoes() default {};
}
