package br.com.grrecurso.service.login;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.Query;
import javax.persistence.criteria.Path;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;

import br.com.grrecurso.entities.usuario.Usuario;
import br.com.grrecurso.service.AbstractService;

@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class UsuarioService extends AbstractService implements UsuarioSvcLocal, UsuarioSvcRemote{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4344896204368371422L;
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Usuario loadById(Long idUsuario) {
		Query query = em.createNamedQuery("Usuario.loadById");
		query.setParameter("idUsuario", idUsuario);		
		return (Usuario)query.getSingleResult();
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Usuario loadByEmail(String email){
		Query query = em.createNamedQuery("Usuario.loadByEmail");
		query.setParameter("email", email);
		return (Usuario)query.getSingleResult();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Usuario saveOrUpdate(Usuario usuario) {
		em.merge(usuario);
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
	
	public int count(String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        return this.list(-1,-1,null,null,filters).size();
    }
	
	public List<Usuario> list(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        Criteria criteria = getSession().createCriteria(Usuario.class);        
//        Path<?> path = getPath(sortField, usuario, siteType);
        /*
        if (sortOrder == null){
            //just don't sort
        }else if (sortOrder.equals(SortOrder.ASCENDING)){
            query.orderBy(cb.asc(path));
        }else if (sortOrder.equals(SortOrder.DESCENDING)){
            query.orderBy(cb.asc(path));
        }else if (sortOrder.equals(SortOrder.UNSORTED)){
            //just don't sort
        }else{
            //just don't sort
        }
        */
        
        //filter
//        Predicate filterCondition = criteriaBuilder.conjunction();
        for (Map.Entry<String, Object> filter : filters.entrySet()) {
            if (!filter.getValue().equals("")) {
                //try as string using like
            	Path<String> pathFilter = null;
//                Path<String> pathFilter = getStringPath(filter.getKey(), usuario, siteType);
                if (pathFilter != null){
//                    filterCondition = criteriaBuilder.and(filterCondition, criteriaBuilder.like(pathFilter, "%"+filter.getValue()+"%"));
                }else{
                    //try as non-string using equal
//                    Path<?> pathFilterNonString = getPath(filter.getKey(), usuario, siteType);
                	  
//                    filterCondition = criteriaBuilder.and(filterCondition, criteriaBuilder.equal(pathFilterNonString, filter.getValue()));
                }
            }
        }
//        query.where(filterCondition);
        
        //pagination
        if (pageSize >= 0){
            criteria.setMaxResults(pageSize);
        }
        if (first >= 0){
            criteria.setFirstResult(first);
        }
        return criteria.list();
    }

}

