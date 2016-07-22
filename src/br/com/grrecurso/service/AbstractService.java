package br.com.grrecurso.service;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

public abstract class AbstractService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7398964389307608328L;
	
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@PersistenceContext
	protected EntityManager em;
	
	public Session getSession() {
		Session session = em.unwrap(Session.class);
		session.enableFilter("porSistema").setParameter("sistema", "grrecurso");
		return session;
	}
}
