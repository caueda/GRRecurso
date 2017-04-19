package br.com.grrecurso.managed.usuario;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
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
@URLBeanName("permissaoPesquisaAction")
@URLMappings( mappings= {
		@URLMapping(id="permissaoPesquisa", pattern="/app/permissao/criteria/pesquisa", viewId="/application/permissao/permissaoPesquisa.jsf"),
})
public class PermissaoPesquisaAction extends AbstractManagedBean {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4257696224107567482L;
	
	private Permissao permissao;
	private List<Permissao> listaPermissao;
	
	@EJB
	private PermissaoService permissaoService;
	@Inject
	protected BeanManager beanManager;
	
	@PostConstruct
	public void init() {
		logger.info("[PermissaoPesquisaAction.init] " + this.toString());
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("[PermissaoPesquisaAction.destroy] " + this.toString());
	}

	public void consultar() {
		setListaPermissao(permissaoService.list(getPermissao()));
	}

	public List<Permissao> getListaPermissao() {
		return listaPermissao;
	}

	public void setListaPermissao(List<Permissao> listaPermissao) {
		this.listaPermissao = listaPermissao;
	}

	public Permissao getPermissao() {
		if(this.permissao == null) {
			setPermissao(new Permissao());
		}
		return this.permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}
	
	public String pesquisar(){
		try {
			return pesquisar(Permissao.class);
		} catch (ClassNotFoundException e) {
			incluirError("Contate o administrador do sistema: " + e.getMessage());
		}
		return null;
	}
}
