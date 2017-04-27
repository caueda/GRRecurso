package br.com.grrecurso.producer;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

import br.com.grrecurso.dominio.DominioAtivoInativo;
import br.com.grrecurso.producer.qualifiers.UsuarioLogado;
import br.com.grrecurso.seguranca.spring.user.GRRecursoUser;
import br.com.grrecurso.seguranca.spring.util.SpringSecUtil;

@RequestScoped
public class GRRecursoProducer {	
	@Produces
	public DominioAtivoInativo[] listaAtivoInativo(){
		return DominioAtivoInativo.values();
	}
	
	@Produces @UsuarioLogado
	public GRRecursoUser getPrincipal() {
		
		GRRecursoUser principal = SpringSecUtil.getPrincipal();
		
		return principal;
	}
}
