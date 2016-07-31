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
import br.com.grrecurso.entities.usuario.Role;
import br.com.grrecurso.producer.qualifiers.RolesList;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RoleService extends AbstractService<Role, Long> {

	protected RoleService() {
		super(Role.class);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 336983759745248607L;
	
	@PostConstruct
	private void init(){
	}
	
	@SuppressWarnings("unchecked")
	public List<Role> getRoles(){
		Query query = em.createNamedQuery("Role.listAll");
		return query.getResultList();
	}
	
	public Role loadById(Long idRole){
		Query query = em.createNamedQuery("Role.loadById");
		query.setParameter("idRole", idRole);
		return (Role)query.getSingleResult();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Role saveOrUpdate(Role role) {
		em.merge(role);
		return role;
	}	
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Role> list(Role rolePesquisa){
		Session session = (Session)em.getDelegate();
		Criteria criteria = session.createCriteria(Role.class);
		
		if(StringUtils.isNotBlank(rolePesquisa.getNome())) {
			criteria.add(Restrictions.like("nome", rolePesquisa.getNome()));
		} 
		return (List<Role>)criteria.list();
	}
	
	@Produces
	@RolesList
	@SuppressWarnings("unchecked")
	public List<Role> listRoles(){
		Query query = em.createNamedQuery("Role.listAll");
		return (List<Role>)query.getResultList();
	}
}
