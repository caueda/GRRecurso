package br.com.grrecurso.service.login;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.grrecurso.entities.usuario.Usuario;
import br.com.grrecurso.service.AbstractService;

@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class UsuarioService extends AbstractService implements UsuarioSvcLocal, UsuarioSvcRemote{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4344896204368371422L;
	
	@PersistenceContext 
	private EntityManager em;
	
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Usuario loadById(Long idUsuario) {
		Query query = em.createNamedQuery("Usuario.findById");
		query.setParameter("idUsuario", idUsuario);
		return (Usuario)query.getSingleResult();
	}
	
	@TransactionAttribute()
	public Usuario loadByEmail(String email){
		Session session = (Session)em.getDelegate();
		org.hibernate.Query query = session.createQuery("select u from Usuario u where u.email = :email");
		query.setParameter("email", email);
		return (Usuario)query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listAll(){
		return em.createNamedQuery("Usuario.listAll").getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Usuario saveOrUpdate(Usuario usuario) {
		em.merge(usuario);
		return usuario;
	}	
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void alterarSenha(Long idUsuario, String novaSenha){
		Usuario usuario = loadById(idUsuario);
		usuario.setSenha(novaSenha);
		em.merge(usuario);
	}
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Usuario> list(Usuario usuarioPesquisa){
		Session session = (Session)em.getDelegate();
		Criteria criteria = session.createCriteria(Usuario.class);
		
		if(StringUtils.isNotBlank(usuarioPesquisa.getNome())) {
			criteria.add(Restrictions.like("nome", usuarioPesquisa.getNome()));
		} 
		if(StringUtils.isNotBlank(usuarioPesquisa.getEmail())) {
			criteria.add(Restrictions.like("email", usuarioPesquisa.getEmail()));
		}
		
		return (List<Usuario>)criteria.list();
	}
}

