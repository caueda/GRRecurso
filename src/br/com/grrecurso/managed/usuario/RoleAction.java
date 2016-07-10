package br.com.grrecurso.managed.usuario;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.com.grrecurso.entities.usuario.Role;
import br.com.grrecurso.managed.AbstractManagedBean;
import br.com.grrecurso.service.login.RoleService;

@Named
@ViewScoped
@URLMappings( mappings= {
		@URLMapping(id="newRole", pattern="/app/usuario/role/#{tipoOperacao : roleAction.tipoOperacao}", viewId="/application/user/role.jsf"),
		@URLMapping(id="editRole", pattern="/app/usuario/role/#{tipoOperacao : roleAction.tipoOperacao}/id/#{idRole : roleAction.idRole}", 
		onPostback=false, viewId="/application/user/role.jsf"),
})
public class RoleAction extends AbstractManagedBean {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7537013458287313484L;
	private Role role;
	private Long idRole;
	
	@EJB
	private RoleService roleService;
	
	@PostConstruct
	public void init() {
		System.out.println("[RoleAction.init] " + this.toString());
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("[RoleAction.destroy] " + this.toString());
	}
	
	public void exibirEdicao(){
		if(!isIncluir() && getIdRole() != null) {
			setTipoOperacao(ALTERAR);
			setRole(roleService.loadById(getIdRole()));
		}
	}
	
	public String persistir() {
//		printScopedReferences(beanManager);
		if(isIncluir()) {
			try {			
				roleService.saveOrUpdate(this.role);			
				incluirInfo("Papel incluído com sucesso.");
				setRole(new Role());
			} catch(Exception e) {
				e.printStackTrace();
				incluirError("Erro ao incluir papel.", e.getMessage());
			}
			return "pretty:";
		} else if(isAlterar()) {
			try {			
				roleService.saveOrUpdate(this.role);			
				incluirInfo("Papel alterado com sucesso.");
				setRole(new Role());
				return "pretty:rolePesquisa";
			} catch(Exception e) {
				e.printStackTrace();
				incluirError("Erro ao alterar papel.", e.getMessage());
			}
		}
		return "pretty:";
	}

	public Role getRole() {
		if(role == null){
			setRole(new Role());
		}
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}
}
