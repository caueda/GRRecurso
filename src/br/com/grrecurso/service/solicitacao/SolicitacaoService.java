package br.com.grrecurso.service.solicitacao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;

import br.com.grrecurso.entities.Solicitacao;
import br.com.grrecurso.entities.usuario.Usuario;
import br.com.grrecurso.service.AbstractService;

@Named
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class SolicitacaoService extends AbstractService<Solicitacao, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 336983759745248607L;
	
	public SolicitacaoService(){
		super(Solicitacao.class);
	}
	
	@PostConstruct
	private void init(){
	}
	
	@Override
	public Solicitacao loadById(Long idSolicitacao){
		Criteria criteria = getSession().createCriteria(Solicitacao.class);
		criteria.add(Restrictions.eq("idSolicitacao", idSolicitacao));
		criteria.setFetchMode("modulo", FetchMode.JOIN);
		return (Solicitacao)criteria.uniqueResult();
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Solicitacao saveOrUpdate(Solicitacao solicitacao) {
		if(solicitacao.getIdSolicitacao() == null){
			solicitacao.setUsuario(getSession().load(Usuario.class, getPrincipal().getIdUsuario()));
		}
		return super.saveOrUpdate(solicitacao);		
	}	
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Solicitacao> list(Solicitacao solicitacao){
		Criteria criteria = getSession().createCriteria(Solicitacao.class);
		
		if(StringUtils.isNotBlank(solicitacao.getChamado())) {
			criteria.add(Restrictions.like("chamado", solicitacao.getChamado()));
		} 
		return criteria.list();
	}
}
