package br.com.grrecurso.managed.solicitacao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import javax.faces.view.ViewScoped;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.com.grrecurso.core.interceptor.BeanInterceptor;
import br.com.grrecurso.core.managed.AbstractManagedBean;
import br.com.grrecurso.dominio.DominioSituacaoSolicitacao;
import br.com.grrecurso.entities.Solicitacao;
import br.com.grrecurso.entities.usuario.Modulo;
import br.com.grrecurso.service.solicitacao.SolicitacaoService;

@Named
@ViewScoped
@URLBeanName(value="solicitacaoAction")
@URLMappings( mappings= {
		@URLMapping(id="newSolicitacao", pattern="/app/solicitacao/#{tipoOperacao : solicitacaoAction.tipoOperacao}", viewId="/application/solicitacao/solicitacao.jsf"),
		@URLMapping(id="editSolicitacao", pattern="/app/solicitacao/#{tipoOperacao : solicitacaoAction.tipoOperacao}/id/#{idSolicitacao : solicitacaoAction.idSolicitacao}", 
		onPostback=false, viewId="/application/solicitacao/solicitacao.jsf"),
})
@Interceptors({BeanInterceptor.class})
public class SolicitacaoAction extends AbstractManagedBean {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7537013458287313484L;
	private Solicitacao solicitacao;
	private Long idSolicitacao;
	@Inject
	private List<Modulo> listaModulos;
	
	@EJB
	private SolicitacaoService solicitacaoService;
	
	@PostConstruct
	public void init() {
		logger.info("[SolicitacaoAction.init] " + this.toString());
	}
	
	@PreDestroy
	public void destroy() {
		logger.info("[SolicitacaoAction.destroy] " + this.toString());
	}
	
	@URLAction(mappingId="editSolicitacao")
	public void exibirEdicao(){
		if(!isIncluir() && getIdSolicitacao() != null) {
			setTipoOperacao(ALTERAR);
			setSolicitacao(solicitacaoService.loadById(getIdSolicitacao()));
		}
	}
	
	public String persistir() {
//		printScopedReferences(beanManager);		
		if(isIncluir()) {
			try {			
				solicitacaoService.saveOrUpdate(this.solicitacao);			
				incluirInfo("Solicitacao incluída com sucesso.");
				setSolicitacao(new Solicitacao());
			} catch(Exception e) {
				e.printStackTrace();
				incluirError("Erro ao incluir a solicitação.", e.getMessage());
			}
			return "pretty:";
		} else if(isAlterar()) {
			try {			
				solicitacaoService.saveOrUpdate(this.solicitacao);			
				incluirInfo("Solicitação alterada com sucesso.");
				setSolicitacao(new Solicitacao());
				return "pretty:solicitacaoPesquisa";
			} catch(Exception e) {
				e.printStackTrace();
				incluirError("Erro ao alterar solicitação.", e.getMessage());
			}
		}
		return "pretty:";
	}

	public Solicitacao getSolicitacao() {
		if(this.solicitacao == null){
			setSolicitacao(new Solicitacao());
		}
		return this.solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

	public Long getIdSolicitacao() {
		return idSolicitacao;
	}

	public void setIdSolicitacao(Long idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}
	
	public DominioSituacaoSolicitacao[] getListaSituacaoSolicitacao(){
		return DominioSituacaoSolicitacao.values();
	}

	public List<Modulo> getListaModulos() {
		return listaModulos;
	}
}
