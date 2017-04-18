package br.com.grrecurso.service.login;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.inject.Produces;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.grrecurso.core.service.AbstractService;
import br.com.grrecurso.entities.usuario.Permissao;
import br.com.grrecurso.producer.qualifiers.RolesList;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PermissaoService extends AbstractService<Permissao, Long> {

	protected PermissaoService() {
		super(Permissao.class);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 336983759745248607L;
	
	@PostConstruct
	private void init(){
	}
	
	@SuppressWarnings("unchecked")
	public List<Permissao> getPermissao(){
		Query query = em.createNamedQuery("Permissao.listAll");
		return query.getResultList();
	}
	
	public Permissao loadById(Long idPermissao){
		Query query = em.createNamedQuery("Permissao.loadById");
		query.setParameter("idPermissao", idPermissao);
		return (Permissao)query.getSingleResult();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Permissao saveOrUpdate(Permissao permissao) {
		em.merge(permissao);
		return permissao;
	}	
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Permissao> list(Permissao permissaoPesquisa){
		Session session = (Session)em.getDelegate();
		Criteria criteria = session.createCriteria(Permissao.class);
		
		if(StringUtils.isNotBlank(permissaoPesquisa.getNome())) {
			criteria.add(Restrictions.like("nome", permissaoPesquisa.getNome()));
		} 
		return (List<Permissao>)criteria.list();
	}
	
	@Produces
	@RolesList
	@SuppressWarnings("unchecked")
	public List<Permissao> listPermissao(){
		Query query = em.createNamedQuery("Permissao.listAll");
		return (List<Permissao>)query.getResultList();
	}
}
