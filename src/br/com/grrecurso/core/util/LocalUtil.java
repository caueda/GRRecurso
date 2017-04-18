package br.com.grrecurso.core.util;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.grrecurso.seguranca.spring.user.GRRecursoUser;

public class LocalUtil {
	
	public static final GRRecursoUser getPrincipal() {
		return (GRRecursoUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
