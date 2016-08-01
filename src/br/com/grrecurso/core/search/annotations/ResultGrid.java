package br.com.grrecurso.core.search.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ResultGrid {
	String label();
	String campo() default "";
	int ordem() default 0;
}
