package br.com.grrecurso.producer;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import br.com.grrecurso.dominio.DominioAtivoInativo;
import br.com.grrecurso.entities.usuario.Role;
import br.com.grrecurso.entities.usuario.UserBean;
import br.com.grrecurso.producer.qualifiers.RolesList;
import br.com.grrecurso.producer.qualifiers.UsuarioLogado;

public class GRRecursoProducer {
	
	@PersistenceContext(unitName="grrecurso")
	private EntityManager em;
	
	@Produces
	public DominioAtivoInativo[] listaAtivoInativo(){
		return DominioAtivoInativo.values();
		
	}
	
	@Produces @RequestScoped
	public EntityManager getEm() {
		em.unwrap(Session.class).enableFilter("porNome").setParameter("nome", "Administrator");
		return em;
	}
	
	@Produces @RequestScoped
	public Session getSession() {
		em.unwrap(Session.class).enableFilter("porNome").setParameter("nome", "Administrator");
		return em.unwrap(Session.class);
	}
	
	@Produces
	@RolesList
	@SuppressWarnings("unchecked")
	public List<Role> listRoles(){
		Query query = em.createNamedQuery("Role.listAll");
		return (List<Role>)query.getResultList();
	}
	
	@Produces @UsuarioLogado
	public UserBean obtemUserBean() {
		UserBean userBean = null;
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if(session != null) {
			userBean = (UserBean)session.getAttribute(UserBean.USER_LOGGED);
			return userBean;
		} 
		return userBean;
	}
}
