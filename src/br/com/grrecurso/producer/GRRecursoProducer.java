package br.com.grrecurso.producer;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.grrecurso.dominio.DominioAtivoInativo;
import br.com.grrecurso.entities.usuario.Modulo;
import br.com.grrecurso.entities.usuario.Role;
import br.com.grrecurso.entities.usuario.UserBean;
import br.com.grrecurso.producer.qualifiers.RolesList;
import br.com.grrecurso.producer.qualifiers.UsuarioLogado;

public class GRRecursoProducer {
	@PersistenceUnit
	private EntityManagerFactory emf;
	
	@Produces @RequestScoped
	public EntityManager getEntityManager(){
		return emf.createEntityManager();
	}

	@Inject
	private EntityManager entityManager;
	
	public void closeEntityManager(@Disposes EntityManager entityManager){
		try {
			entityManager.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Produces
	public DominioAtivoInativo[] listaAtivoInativo(){
		return DominioAtivoInativo.values();
	}
	
	@Produces
	@RolesList
	@SuppressWarnings("unchecked")
	public List<Role> listRoles(){
		Query query = entityManager.createNamedQuery("Role.listAll");
		return (List<Role>)query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Produces
	public List<Modulo> listaModulos(){
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Modulo.class);
		return (List<Modulo>)criteria.list();
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
