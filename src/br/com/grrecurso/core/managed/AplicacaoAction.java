package br.com.grrecurso.core.managed;

import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

@Named("aplicacao")
@ViewScoped
public class AplicacaoAction extends AbstractManagedBean {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7537013458287313484L;

	@Override
	public boolean hasPermissao(String permissao) {
		return super.hasPermissao(permissao);
	}

	@Override
	public boolean hasRole(String role) {
		return super.hasRole(role);
	}	
	
	
}
