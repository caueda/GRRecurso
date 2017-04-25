package br.com.grrecurso.seguranca.spring.util;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.grrecurso.seguranca.spring.user.GRRecursoUser;

public final class SpringSecUtil {

	public static GRRecursoUser getPrincipal(){
		GRRecursoUser user = (GRRecursoUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
	
	public static boolean hasPermissao(String permissao){
		if(getPrincipal() != null && getPrincipal() instanceof GRRecursoUser){
			GRRecursoUser user = (GRRecursoUser) getPrincipal();
			return (user.getPermissoes().containsKey(permissao));			
		}
		return false;
	}
	
	public static boolean hasRole(String role) {
		if(getPrincipal() != null && getPrincipal() instanceof GRRecursoUser){
			GRRecursoUser user = (GRRecursoUser) getPrincipal();
			return (user.getPermissoes().containsKey(role));			
		}
		return false;
	}

}
