package br.com.grrecurso.service.login;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.Query;

import org.hibernate.SQLQuery;

import br.com.grrecurso.core.service.AbstractService;
import br.com.grrecurso.core.service.BusinessException;
import br.com.grrecurso.entities.usuario.PerfilUsuario;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PerfilUsuarioService extends AbstractService<PerfilUsuario, Long> {

	protected PerfilUsuarioService() {
		super(PerfilUsuario.class);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 336983759745248607L;
	
	@PostConstruct
	private void init(){
	}
	
	@Override
	public List<PerfilUsuario> listAll() {
		return super.listAll();
	}

	public PerfilUsuario loadPerfilBase() throws BusinessException {
		PerfilUsuario perfilBase = null;
		
		Query query = em.createNamedQuery("PerfilUsuario.loadById");
		query.setParameter("idPerfilUsuario", Long.valueOf(1));
		perfilBase = (PerfilUsuario)query.getSingleResult();
		
		if(perfilBase == null) {
			throw new BusinessException("Perfil Base não cadastrado no sistema.");
		}
		
		return perfilBase;
	}
	
	@SuppressWarnings("unchecked")
	public List<PerfilUsuario> listaPerfisUsuarioExcept(Long idUsuario){
		StringBuilder hql = new StringBuilder();
		hql.append("select pu.* ")
		   .append(" from perfil_usuario pu ")
		   .append(" where pu.id_perfil_usuario not in ")
		   .append("(")
		   .append(" select pu.id_perfil_usuario ")
		   .append("  from usuario_perfil_usuario upu ")
		   .append("  join perfil_usuario pu on upu.id_perfil_usuario = pu.id_perfil_usuario ")
		   .append(" where upu.id_usuario = ").append(idUsuario.toString())
		   .append(")")
		   ;
		
		SQLQuery query = getSession().createSQLQuery(hql.toString());
		
		query.addEntity(PerfilUsuario.class);
		
		List<PerfilUsuario> perfis = query.list();
		
		return perfis;
	}
	
	@SuppressWarnings("unchecked")
	public List<PerfilUsuario> getPerfilUsuarios(){
		Query query = em.createNamedQuery("PerfilUsuario.listAll");
		return query.getResultList();
	}
	
	public PerfilUsuario loadById(Long idPerfilUsuario){
		Query query = em.createNamedQuery("PerfilUsuario.loadById");
		query.setParameter("idPerfilUsuario", idPerfilUsuario);
		return (PerfilUsuario)query.getSingleResult();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PerfilUsuario saveOrUpdate(PerfilUsuario perfilUsuario) {
		em.merge(perfilUsuario);
		return perfilUsuario;
	}	
	
//	@SuppressWarnings("unchecked")
//	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
//	public List<PerfilUsuario> list(PerfilUsuario perfilUsuarioPesquisa){
//		Session session = (Session)em.getDelegate();
//		Criteria criteria = session.createCriteria(PerfilUsuario.class);
//		
//		if(StringUtils.isNotBlank(perfilUsuarioPesquisa.getNome())) {
//			criteria.add(Restrictions.like("nome", perfilUsuarioPesquisa.getNome()));
//		} 
//		return (List<PerfilUsuario>)criteria.list();
//	}
}
