package br.com.grrecurso.managed.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.com.grrecurso.core.managed.SearchEngine;
import br.com.grrecurso.entities.usuario.Role;
import br.com.grrecurso.service.login.RoleService;

@Named
@URLBeanName("roleConsultaAction")
@ViewScoped
@URLMappings( mappings= {
		@URLMapping(id="roleConsulta", pattern="/app/user/papel/pesquisar", viewId="/application/user/roleConsulta.jsf", onPostback=false),		
})
public class RoleConsultaAction extends SearchEngine {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7537013458287313484L;
	private Role role;
	private List<Role> listaRoles = new ArrayList<Role>();
	
	public RoleConsultaAction(){
		super();
	}
	
	@EJB
	private RoleService roleService;
	
	@URLAction(mappingId="roleConsulta", onPostback=false)
	@Override
	public void preInit() {
		logger.info("[RolePesquisaConsulta.init] " + this.toString());
		setClazzEntity(Role.class);
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
