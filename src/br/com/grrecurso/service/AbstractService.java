package br.com.grrecurso.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.grrecurso.entities.Solicitacao;
import br.com.grrecurso.entities.usuario.Usuario;
import br.com.grrecurso.seguranca.spring.user.GRRecursoUser;

public abstract class AbstractService<T> implements Serializable {
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
}
