package br.com.grrecurso.service.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
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
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.Work;
import org.primefaces.model.SortOrder;

import br.com.grrecurso.core.security.annotation.IgnorarPermissoes;
import br.com.grrecurso.core.service.AbstractService;
import br.com.grrecurso.entities.usuario.Usuario;
import br.com.grrecurso.service.message.BeanMessage;

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
	
	@IgnorarPermissoes
	public void updateDataLoginNULL(Usuario entity) {
		getSession().evict(entity);
		getSession().doWork(new Work() {

			@Override
			public void execute(Connection conn) throws SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("update ")
				   .append(" Usuario ")
				   .append(" set data_login=? ")
				   .append(" where id_usuario=? ")
				;
				PreparedStatement ps = conn.prepareStatement(sql.toString());
				ps.setTimestamp(1,  null);				
				ps.setLong(2, entity.getIdUsuario());
				ps.executeUpdate();
			}
			
		});
	}
	
	@IgnorarPermissoes
	public void updateDataLogin(Usuario entity) {
		getSession().evict(entity);
		getSession().doWork(new Work() {

			@Override
			public void execute(Connection conn) throws SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("update ")
				   .append(" Usuario ")
				   .append(" set data_login=? ")
				   .append(" where id_usuario=? ")
				;
				PreparedStatement ps = conn.prepareStatement(sql.toString());
				ps.setTimestamp(1,  new Timestamp(new Date().getTime()));				
				ps.setLong(2, entity.getIdUsuario());
				ps.executeUpdate();
			}
			
		});
	}

	@Override
	public Usuario loadById(Long id) {
		
		Usuario usuario = super.loadById(id);
		
		Hibernate.initialize(usuario.getModulos());
		Hibernate.initialize(usuario.getEnderecos());
		Hibernate.initialize(usuario.getPerfis());		
		
		return usuario;
	}

	@IgnorarPermissoes
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario findByEmail(String email){
		
		StringBuilder hql = new StringBuilder();
		hql.append("select vo from " + Usuario.class.getSimpleName()).append(" vo ")
		   .append(" join fetch vo.perfis perfil ")
		   .append(" join fetch perfil.roles role ")		   
		   .append(" join fetch vo.modulos ")
		   .append(" where vo.email = :email ");
		
		Query query = getSession().createQuery(hql.toString());
		query.setParameter("email", email);
		
		query.list();
		
		Usuario usuario = (Usuario)query.uniqueResult();
		
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
	@Produces(MediaType.APPLICATION_JSON)
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public BeanMessage processarUpdate(Usuario usuario) {
		Session session = getSession();
		try {
			Usuario load = (Usuario)session.load(Usuario.class,usuario.getIdUsuario());
			load.setNome(usuario.getNome());
			load.setEmail(usuario.getEmail());
			session.update(load);
		} catch(Exception e) {
			return new BeanMessage(-1, "Erro", "Contacte o administrador. Erro: " + e.getMessage());
		}
		return new BeanMessage(100, "Sucesso", "Usuário alterado com sucesso!");
	}
	
	public int count(String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        return this.list(-1,-1,null,null,filters).size();
    }
}

