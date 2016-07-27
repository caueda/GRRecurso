package br.com.grrecurso.service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.grrecurso.entities.Solicitacao;
import br.com.grrecurso.entities.usuario.Usuario;
import br.com.grrecurso.seguranca.spring.user.GRRecursoUser;

public abstract class AbstractService<T, ID extends Serializable> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7398964389307608328L;
	
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@PersistenceContext
	protected EntityManager em;
	
	protected GRRecursoUser principal;
	
	protected Usuario usuarioLogado;
	
	private Class<T> clazz;
	
	protected AbstractService(Class<T> clazz){
		this.clazz = clazz;
	}
	
	public Session getSession() {
		Session session = em.unwrap(Session.class);		
		Object callerPrincipal = null;
		if(SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null){
			callerPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		List<Long> idModules = new ArrayList<Long>();
		if(callerPrincipal != null && callerPrincipal instanceof GRRecursoUser){
			principal = (GRRecursoUser)callerPrincipal;
			idModules = principal.getModuleIds();			
		}
		if(this.clazz.getSimpleName().equals(Solicitacao.class.getSimpleName())){
			session.enableFilter("porModulo").setParameterList("idModulos", idModules);
		}
		return session;
	}

	public GRRecursoUser getPrincipal() {
		return principal;
	}

	public void setPrincipal(GRRecursoUser principal) {
		this.principal = principal;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listAll(){
		Criteria criteria = getSession().createCriteria(this.clazz);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public T loadById(ID id){
		String idAttribute = getIdAnnotatedAttribute(this.clazz);
		Criteria criteria = getSession().createCriteria(this.clazz);
		criteria.add(Restrictions.eq(idAttribute, id));
		return (T)criteria.uniqueResult();
	}
	
	public String getIdAnnotatedAttribute(Class<?> clazz){
		String idAttribute = null;
		for (Field f : clazz.getDeclaredFields()) {
			if (f.isAnnotationPresent(Id.class)) {				
				idAttribute = f.getName();				
			}
		}
		if(idAttribute == null && !clazz.getSuperclass().getName().equals(Object.class.getName())){
			idAttribute = getIdAnnotatedAttribute(clazz.getSuperclass());
		}
		return idAttribute;
	}
	
	public T saveOrUpdate(T entity){
		getSession().saveOrUpdate(entity);
		return entity;
	}
}
