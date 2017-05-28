package br.com.grrecurso.managed.usuario;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.inject.spi.BeanManager;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.com.grrecurso.core.interceptor.annotation.WebAccess;
import br.com.grrecurso.core.managed.AbstractManagedBean;
import br.com.grrecurso.entities.usuario.Modulo;
import br.com.grrecurso.entities.usuario.Role;
import br.com.grrecurso.service.login.RoleService;
import br.com.grrecurso.service.modulo.ModuloService;

@Named
@ViewScoped
@URLMappings( mappings= {
		@URLMapping(id="rolePesquisa", pattern="/app/usuario/role/criteria/pesquisa", viewId="/application/user/rolePesquisa.jsf"),		
		@URLMapping(id="rolePesquisaPopup", pattern="/app/usuario/role/criteria/popup", viewId="/application/user/rolePesquisaPopup.jsf"),
})
@WebAccess
public class RolePesquisaAction extends AbstractManagedBean {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7537013458287313484L;
	private Role role;
	private List<Role> listaRoles;
	
	@EJB
	private RoleService roleService;
	
	@EJB
	private ModuloService moduloService;
	
	@Inject
	protected BeanManager beanManager;
	
	@PostConstruct
	public void init() {
		logger.info("[RolePesquisaAction.init] " + this.toString());
	}
	
	public List<Modulo> getListaModulos(){
		return moduloService.listaModulos();
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("[RolePesquisaAction.destroy] " + this.toString());
	}

	public void consultar() {
		setListaRoles(roleService.list(getRole()));
//		printScopedReferences(beanManager);
	}

	public List<Role> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<Role> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public Role getRole() {
		if(this.role == null) {
			setRole(new Role());
		}
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
