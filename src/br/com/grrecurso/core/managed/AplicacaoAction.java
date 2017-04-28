package br.com.grrecurso.core.managed;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("aplicacao")
@RequestScoped
public class AplicacaoAction extends AbstractManagedBean {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7537013458287313484L;

	@Override
	public boolean hasPermissao(String permissao) {
		return super.hasPermissaoItemMenu(permissao);
	}

	@Override
	public boolean hasRole(String role) {
		return super.hasRole(role);
	}	
}
