package br.com.grrecurso.core.service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.grrecurso.core.security.annotation.IgnorarPermissoes;
import br.com.grrecurso.core.util.LocalUtil;
import br.com.grrecurso.entities.Solicitacao;
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
	
	
	private Class<T> clazz;
	
	protected AbstractService(Class<T> clazz){
		this.clazz = clazz;
	}
	
	public Session getSession() {
		Session session = em.unwrap(Session.class);		
		Object callerPrincipal = null;
		if(SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null){
			callerPrincipal = LocalUtil.getPrincipal();
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
	protected List<T> listAll(){
		Criteria criteria = getSession().createCriteria(this.clazz);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	protected T loadById(ID id){
		String idAttribute = getIdAnnotatedAttribute(this.clazz);
		Criteria criteria = getSession().createCriteria(this.clazz);
		criteria.add(Restrictions.eq(idAttribute, id));
		return (T)criteria.uniqueResult();
	}
	
	protected String getIdAnnotatedAttribute(Class<?> clazz){
		String idAttribute = null;
		for (Field f : clazz.getDeclaredFields()) {
			if (f.isAnnotationPresent(Id.class)) {				
				idAttribute = f.getName();				
			}
		}
		for(Method m : clazz.getMethods()){
			String idMethodName = m.getName();
			if(m.isAnnotationPresent(Id.class)){
				idMethodName = idMethodName.replace("get", "");
				idAttribute = String.valueOf(idMethodName.charAt(0)).toLowerCase() + idMethodName.substring(1);
			}
		}
		
		if(idAttribute == null && !clazz.getSuperclass().getName().equals(Object.class.getName())){
			idAttribute = getIdAnnotatedAttribute(clazz.getSuperclass());
		}
		return idAttribute;
	}
	
	protected T saveOrUpdate(T entity){
		getSession().saveOrUpdate(entity);
		return entity;
	}
	
	@IgnorarPermissoes
	@SuppressWarnings("unchecked")
	public List<T> list(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        Criteria criteria = getSession().createCriteria(clazz);

        for (Map.Entry<String, Object> filter : filters.entrySet()){
            if (!filter.getValue().equals("")) {            	
            	System.out.println("Key: " + filter.getKey() + "  Value: " + filter.getValue());
            }
        }
        
        //pagination
        if (pageSize >= 0){
            criteria.setMaxResults(pageSize);
        }
        if (first >= 0){
            criteria.setFirstResult(first);
        }
        return criteria.list();
    }
}
