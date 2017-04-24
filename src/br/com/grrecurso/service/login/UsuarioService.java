package br.com.grrecurso.service.login;

import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;

import br.com.grrecurso.core.service.AbstractService;
import br.com.grrecurso.entities.usuario.Usuario;

@SuppressWarnings("unchecked")
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
@Path("/usuario")
public class UsuarioService extends AbstractService<Usuario, Long> implements UsuarioSvcLocal, UsuarioSvcRemote{

	private static final long serialVersionUID = 4344896204368371422L;
	
	public UsuarioService() {
		super(Usuario.class);
	}
	
	@Override
	public Usuario saveOrUpdate(Usuario entity) {
		return super.saveOrUpdate(entity);
	}

	@Override
	public Usuario loadById(Long id) {
		
		Usuario usuario = super.loadById(id);
		
		Hibernate.initialize(usuario.getModulos());
		Hibernate.initialize(usuario.getEnderecos());
		Hibernate.initialize(usuario.getPerfis());		
		
		return usuario;
	}



	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario findByEmail(String email){
		Session session = getSession();
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.setFetchMode("perfis", FetchMode.JOIN);
		criteria.setFetchMode("modulos", FetchMode.JOIN);
		criteria.add(Restrictions.eq("email", email));
		Usuario usuario = (Usuario)criteria.uniqueResult();
		em.detach(usuario);
		return usuario;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void alterarSenha(Long idUsuario, String novaSenha){
		Usuario usuario = loadById(idUsuario);
		usuario.setSenha(novaSenha);
		em.merge(usuario);
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Usuario> list(Usuario usuarioPesquisa){
		Session session = getSession();
		
		Criteria criteria = session.createCriteria(Usuario.class);
		
		if(StringUtils.isNotBlank(usuarioPesquisa.getNome())) {
			criteria.add(Restrictions.ilike("nome", usuarioPesquisa.getNome()));
		} 
		if(StringUtils.isNotBlank(usuarioPesquisa.getEmail())) {
			criteria.add(Restrictions.ilike("email", usuarioPesquisa.getEmail()));
		}
		
		return (List<Usuario>)criteria.list();
	}
	
	@GET
	@Path("/listall")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Usuario> listaAll(){
		Session session = getSession();
		Criteria criteria = session.createCriteria(Usuario.class);
		return (List<Usuario>)criteria.list();
	}
	
	@GET
	@Path("/listallLogado")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Usuario> listaAllLogado(){
		Session session = getSession();
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.isNotNull("dataLogin"));
		return (List<Usuario>)criteria.list();
	}
	
	@GET
	@Path("/{first}/{size}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Usuario> listaAllPaginated(@PathParam("first") int first, @PathParam("size") int size){
		Session session = getSession();
		
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.setMaxResults(size);
		criteria.setFirstResult(first);
		
		
		return (List<Usuario>)criteria.list();
	}
	
	@Path("/count")
	@GET()
	@Produces({MediaType.APPLICATION_JSON})
	public Long count(){		
		
		StringBuilder hql = new StringBuilder();
		hql.append("select count(vo) ");
		hql.append("  from " + Usuario.class.getSimpleName()).append(" vo ");
		Query query = getSession().createQuery(hql.toString());
		
		return (Long) query.uniqueResult();
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
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String processarUpdate(Usuario usuario) {
		Session session = getSession();
		Usuario load = (Usuario)session.load(Usuario.class,usuario.getIdUsuario());
		load.setNome(usuario.getNome());
		load.setEmail(usuario.getEmail());
		session.update(load);
		return "OK";
	}
	
	public int count(String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        return this.list(-1,-1,null,null,filters).size();
    }
}

