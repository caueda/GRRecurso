package br.com.grrecurso.seguranca.spring.user;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class GRRecursoUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -455051826366177111L;
	
	private List<Long> moduleIds;
	private Long idUsuario;
	private String nome;
	private String email;
	private Integer isDesenvolvedor;
	
	private Map<String, String> permissaoItemMenu = new HashMap<String, String>();
	private Map<String, String> roles = new HashMap<String, String>();
	
	public GRRecursoUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);		
	}

	public List<Long> getModuleIds() {
		return moduleIds;
	}

	public void setModuleIds(List<Long> moduleIds) {
		this.moduleIds = moduleIds;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Map<String, String> getPermissaoItemMenu() {
		return this.permissaoItemMenu;
	}
	
	public void setPermissaoItemMenu(Map<String, String> permissaoItemMenu) {
		this.permissaoItemMenu = permissaoItemMenu;
	}

	public Map<String, String> getRoles() {
		return roles;
	}
	
	public void setRoles(Map<String, String> roles) {
		this.roles = roles;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIsDesenvolvedor() {
		return isDesenvolvedor;
	}

	public void setIsDesenvolvedor(Integer isDesenvolvedor) {
		this.isDesenvolvedor = isDesenvolvedor;
	}
}
