package br.com.grrecurso.managed.usuario;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.com.grrecurso.core.managed.AbstractManagedBean;
import br.com.grrecurso.entities.usuario.Permissao;
import br.com.grrecurso.service.login.PermissaoService;

@Named
@ViewScoped
@URLBeanName("permissaoAction")
@URLMappings( mappings= {
		@URLMapping(id="newPermissao", pattern="/app/permissao/#{tipoOperacao : permissaoAction.tipoOperacao}", viewId="/application/permissao/permissao.jsf"),
		@URLMapping(id="editPermissao", pattern="/app/permissao/#{tipoOperacao : permissaoAction.tipoOperacao}/id/#{idPermissao : permissaoAction.idPermissao}", 
		onPostback=false, viewId="/application/permissao/permissao.jsf"),
})
public class PermissaoAction extends AbstractManagedBean {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1338935076679440203L;
	private Permissao permissao;
	private Long idPermissao;
	
	@EJB
	private PermissaoService permissaoService;
	
	@PostConstruct
	public void init() {
		logger.info("[PermissaoAction.init] " + this.toString());
	}
	
	@PreDestroy
	public void destroy() {
		logger.info("[PermissaoAction.destroy] " + this.toString());
	}
	
	public void exibirEdicao(){
		if(!isIncluir() && getIdPermissao() != null) {
			setTipoOperacao(ALTERAR);
			setPermissao(permissaoService.loadById(getIdPermissao()));
		}
	}
	
	
	public String persistir() {
		if(isIncluir()) {
			try {			
				permissaoService.saveOrUpdate(this.permissao);			
				incluirInfo("Permissão incluída com sucesso.");
				setPermissao(new Permissao());
			} catch(Exception e) {
				e.printStackTrace();
				incluirError("Erro ao incluir permissão.", e.getMessage());
			}
			return "pretty:";
		} else if(isAlterar()) {
			try {			
				permissaoService.saveOrUpdate(this.permissao);			
				incluirInfo("Permissão alterada com sucesso.");
				setPermissao(new Permissao());
				return "pretty:permissaoPesquisa";
			} catch(Exception e) {
				e.printStackTrace();
				incluirError("Erro ao alterar permissão.", e.getMessage());
			}
		}
		return "pretty:";
	}

	public Permissao getPermissao() {
		if(this.permissao == null){
			setPermissao(new Permissao());
		}
		return this.permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public Long getIdPermissao() {
		return this.idPermissao;
	}

	public void setIdPermissao(Long idPermissao) {
		this.idPermissao = idPermissao;
	}
}
