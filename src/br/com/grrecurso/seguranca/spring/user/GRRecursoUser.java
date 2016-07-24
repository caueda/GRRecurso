package br.com.grrecurso.seguranca.spring.user;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.grrecurso.entities.usuario.Modulo;

public class GRRecursoUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -455051826366177111L;
	
	private List<Modulo> modulos;

	public GRRecursoUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public List<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}
}
