package br.com.grrecurso.service.login;

import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;

import br.com.grrecurso.core.service.AbstractService;
import br.com.grrecurso.entities.usuario.Usuario;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
@Path("/usuario")
public class UsuarioService extends AbstractService<Usuario, Long> implements UsuarioSvcLocal, UsuarioSvcRemote{

	private static final long serialVersionUID = 4344896204368371422L;
	
	public UsuarioService() {
		super(Usuario.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario findByEmail(String email){
		Session session = getSession();
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.setFetchMode("roles", FetchMode.JOIN);
		criteria.setFetchMode("modulos", FetchMode.JOIN);
		criteria.add(Restrictions.eq("email", email));
		Usuario usuario = (Usuario)criteria.uniqueResult();
		em.detach(usuario);
		return usuario;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void alterarSenha(Long idUsuario, String novaSenha){
		em.getTransaction().begin();
		Usuario usuario = loadById(idUsuario);
		usuario.setSenha(novaSenha);
		em.merge(usuario);
	}
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Usuario> list(Usuario usuarioPesquisa){
		Session session = getSession();
		Criteria criteria = session.createCriteria(Usuario.class);
		
		if(StringUtils.isNotBlank(usuarioPesquisa.getNome())) {
			criteria.add(Restrictions.like("nome", usuarioPesquisa.getNome()));
		} 
		if(StringUtils.isNotBlank(usuarioPesquisa.getEmail())) {
			criteria.add(Restrictions.like("email", usuarioPesquisa.getEmail()));
		}
		
		return (List<Usuario>)criteria.list();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Usuario> listaAll(){
		Session session = getSession();
		Criteria criteria = session.createCriteria(Usuario.class);
		return (List<Usuario>)criteria.list();
	}
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Usuario findById(@PathParam("id")long id){
		Session session = getSession();
		Query query = session.createQuery("from Usuario u where u.idUsuario = :id");
		query.setParameter("id", id);
		return (Usuario)query.uniqueResult();
	}
	
	public int count(String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        return this.list(-1,-1,null,null,filters).size();
    }
}

