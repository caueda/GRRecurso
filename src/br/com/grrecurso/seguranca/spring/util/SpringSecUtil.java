package br.com.grrecurso.seguranca.spring.util;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.grrecurso.seguranca.spring.user.GRRecursoUser;


public final class SpringSecUtil {

	public static GRRecursoUser getPrincipal(){
		GRRecursoUser user = null;
		
		if(SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
			user = (GRRecursoUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		
		return user;
	}
}
