package br.com.grrecurso.managed.usuario;

import java.util.HashSet;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.com.grrecurso.core.managed.AbstractManagedBean;
import br.com.grrecurso.entities.usuario.Permissao;
import br.com.grrecurso.entities.usuario.Role;
import br.com.grrecurso.service.login.PermissaoService;
import br.com.grrecurso.service.login.RoleService;

@Named
@ViewScoped
@URLBeanName("roleAction")
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
	
	@EJB
	private PermissaoService permissaoService;
	
	@PostConstruct
	public void init() {
		logger.info("[RoleAction.init] " + this.toString());
	}
	
	public void updatePermissoes(){
		String idPermissao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idPermissao");
		if(idPermissao != null && !idPermissao.isEmpty()){
			Permissao p = permissaoService.loadById(Long.valueOf(idPermissao));
			if(role == null){
				role = new Role();
			}
			if(role.getPermissoes() == null){
				role.setPermissoes(new HashSet<Permissao>());
			}
			if(p != null){
				role.getPermissoes().add(p);
			}
		}
	}
	
	@PreDestroy
	public void destroy() {
		logger.info("[RoleAction.destroy] " + this.toString());
	}
	
	@URLAction(mappingId="editRole", onPostback=false)
	public void exibirEdicao(){
		if(!isIncluir() && getIdRole() != null) {
			setTipoOperacao(ALTERAR);
			setRole(roleService.loadById(getIdRole()));
		}
	}
	
	
	public String persistir() {
//		printScopedReferences(beanManager);
//		boolean isRole = JSFUtil.hasRole("Admin");
//		boolean isOther = JSFUtil.hasRole("Tester");
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
