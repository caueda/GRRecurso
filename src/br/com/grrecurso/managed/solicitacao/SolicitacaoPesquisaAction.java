package br.com.grrecurso.managed.solicitacao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.com.grrecurso.core.managed.AbstractManagedBean;
import br.com.grrecurso.entities.Solicitacao;
import br.com.grrecurso.service.solicitacao.SolicitacaoService;

@Named
@ViewScoped
@URLMappings( mappings= {
		@URLMapping(id="solicitacaoPesquisa", pattern="/app/solicitacao/q/pesquisa", viewId="/application/solicitacao/solicitacaoPesquisa.jsf"),
		
})
public class SolicitacaoPesquisaAction extends AbstractManagedBean {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7537013458287313484L;
	private Solicitacao solicitacao;
	private List<Solicitacao> listaSolicitacoes;
	
	@EJB
	private SolicitacaoService solicitacaoService;
	
	@PostConstruct
	public void init() {
		logger.info("[SolicitacaoPesquisaAction.init] " + this.toString());
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("[SolicitacaoPesquisaAction.destroy] " + this.toString());
	}

	public void consultar() {
		setListaSolicitacoes(solicitacaoService.list(getSolicitacao()));
//		printScopedReferences(beanManager);
	}

	public List<Solicitacao> getListaSolicitacoes() {
		return listaSolicitacoes;
	}

	public void setListaSolicitacoes(List<Solicitacao> listaSolicitacoes) {
		this.listaSolicitacoes = listaSolicitacoes;
	}

	public Solicitacao getSolicitacao() {
		if(this.solicitacao == null) {
			setSolicitacao(new Solicitacao());
		}
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}
}
