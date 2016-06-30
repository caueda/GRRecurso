package br.com.grrecurso.producer;

import java.util.List;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.grrecurso.dominio.DominioAtivoInativo;
import br.com.grrecurso.entities.usuario.Role;
import br.com.grrecurso.producer.qualifiers.RolesList;

public class GRRecursoProducer {
	@Produces
	@PersistenceContext(unitName="grrecurso")
	private EntityManager em;
	
	@Produces
	public DominioAtivoInativo[] listaAtivoInativo(){
		return DominioAtivoInativo.values();
	}
	
	@Produces
	@RolesList
	@SuppressWarnings("unchecked")
	public List<Role> listRoles(){
		Query query = em.createNamedQuery("Role.listAll");
		return (List<Role>)query.getResultList();
	}
}
