package br.com.grrecurso.ejb.login;

import java.io.Serializable;
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

import br.com.grrecurso.entities.Usuario;

@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class UsuarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4344896204368371422L;
	@PersistenceContext(unitName="grrecurso")
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listAll(){
		return em.createNamedQuery("Usuario.listAll").getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Usuario saveOrUpdate(Usuario usuario) {
		em.persist(usuario);
		return usuario;
	}	
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Usuario loadById(Long idUsuario) {
		Query query = em.createNamedQuery("Usuario.findById");
		query.setParameter("idUsuario", idUsuario);
		return (Usuario)query.getSingleResult();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void alterarSenha(Long idUsuario, String novaSenha){
		Usuario usuario = loadById(idUsuario);
		usuario.setSenha(novaSenha);
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

