package br.com.grrecurso.service.login;

import java.io.Serializable;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.grrecurso.entities.usuario.Usuario;

@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class UsuarioService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4344896204368371422L;
	
	@Inject private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listAll(){
		return em.createNamedQuery("Usuario.listAll").getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Usuario saveOrUpdate(Usuario usuario) {
		em.merge(usuario);
		return usuario;
	}	
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Usuario loadById(Long idUsuario) {
		Query query = em.createNamedQuery("Usuario.findById");
		query.setParameter("idUsuario", idUsuario);
		return (Usuario)query.getSingleResult();
	}
	
	@TransactionAttribute()
	public Usuario loadByEmail(String email){
		Query query = em.createNamedQuery("Usuario.loadByEmail");
		query.setParameter("email", email);
		return (Usuario)query.getSingleResult();
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

