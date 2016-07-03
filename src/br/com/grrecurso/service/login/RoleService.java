package br.com.grrecurso.service.login;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.grrecurso.entities.usuario.Role;
import br.com.grrecurso.service.AbstractService;

@Named
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class RoleService extends AbstractService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 336983759745248607L;
	@Inject private EntityManager em;
	
	@PostConstruct
	private void init(){
	}
	
	@SuppressWarnings("unchecked")
	public List<Role> getRoles(){
		Query query = em.createNamedQuery("Role.listAll");
		return query.getResultList();
	}
}
