package br.com.grrecurso.service;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import br.com.grrecurso.entities.usuario.Modulo;
import br.com.grrecurso.seguranca.spring.user.GRRecursoUser;

public abstract class AbstractService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7398964389307608328L;
	
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@PersistenceContext
	protected EntityManager em;
	
	@Resource
	protected Principal principal;
	
	public Session getSession() {
		Session session = em.unwrap(Session.class);
		List<Modulo> modulos = new ArrayList<Modulo>();
		if(principal != null && principal instanceof GRRecursoUser){
			modulos = ((GRRecursoUser)principal).getModulos();
		}
		session.enableFilter("porModulo").setParameter("modulos", modulos);
		return session;
	}
}
