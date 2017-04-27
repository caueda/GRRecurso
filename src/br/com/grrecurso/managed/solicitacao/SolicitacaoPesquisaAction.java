package br.com.grrecurso.managed.solicitacao;

import java.util.List;

import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import org.omnifaces.cdi.ViewScoped;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.com.grrecurso.core.managed.AbstractManagedBean;
import br.com.grrecurso.core.managed.BeanInterceptor;
import br.com.grrecurso.core.managed.annotation.ValidarPermissoes;
import br.com.grrecurso.entities.Solicitacao;
import br.com.grrecurso.service.solicitacao.SolicitacaoService;

@Named
@ViewScoped
@URLBeanName("solicitacaoPesquisaAction")
@URLMappings( mappings= {
		@URLMapping(id="solicitacaoPesquisa", pattern="/app/solicitacao/criteria/pesquisa", viewId="/application/solicitacao/solicitacaoPesquisa.jsf"),
		
})
@Interceptors({BeanInterceptor.class})
public class SolicitacaoPesquisaAction extends AbstractManagedBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7537013458287313484L;
	
	public static final String PERMISSAO_PESQUISA = "solicitacao.pesquisa";
	
	private Solicitacao solicitacao;
	private List<Solicitacao> listaSolicitacoes;
	
	@EJB
	private SolicitacaoService solicitacaoService;
	
	@ValidarPermissoes
	@URLAction(mappingId="solicitacaoPesquisa", onPostback=false)
	public void exibirPesquisa() {
		
		//validarHasPermissao(PERMISSAO_PESQUISA);
		
	}
	
	@PreDestroy
	public void destroy() {
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
