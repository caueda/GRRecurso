package br.com.grrecurso.core.util;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.grrecurso.seguranca.spring.user.GRRecursoUser;

public class LocalUtil {
	
	public static final GRRecursoUser getPrincipal() {
		Object anonymousUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//Considerar acesso anônimo
		if(anonymousUser != null && anonymousUser instanceof String && "anonymousUser".equals(anonymousUser)) {
				return null;
		} else {
			return (GRRecursoUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
	}
}
