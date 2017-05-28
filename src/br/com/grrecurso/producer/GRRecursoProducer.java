package br.com.grrecurso.producer;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.grrecurso.dominio.DominioAtivoInativo;
import br.com.grrecurso.producer.qualifiers.UsuarioLogado;
import br.com.grrecurso.seguranca.spring.user.GRRecursoUser;

@RequestScoped
public class GRRecursoProducer {	
	
	@Produces
	public DominioAtivoInativo[] listaAtivoInativo(){
		return DominioAtivoInativo.values();
	}
	
	@Produces @UsuarioLogado
	public GRRecursoUser getPrincipal() {
		
		GRRecursoUser user = null;
		
		if(SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
			user = (GRRecursoUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		
		return user;
	}
}
