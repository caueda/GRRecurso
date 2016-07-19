package br.com.grrecurso.service.login;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
	
	@PersistenceContext 
	private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Usuario loadById(Long idUsuario) {
		Query query = em.createNamedQuery("Usuario.loadById");
		query.setParameter("idUsuario", idUsuario);
		return (Usuario)query.getSingleResult();
	}
	
	@TransactionAttribute()
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
	
	public int count(String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        return this.listAll(-1,-1,null,null,filters).size();
    }
	
	public List<Usuario> listAll(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
        Root<Usuario> usuario = query.from(Usuario.class);
//        Join<Site,SiteType> siteType = site.join(Site_.siteType);
        query.select(usuario);


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
        Predicate filterCondition = cb.conjunction();
        for (Map.Entry<String, Object> filter : filters.entrySet()) {
            if (!filter.getValue().equals("")) {
            	/*
                //try as string using like            	
                Path<String> pathFilter = getStringPath(filter.getKey(), usuario, siteType);
                if (pathFilter != null){
                    filterCondition = cb.and(filterCondition, cb.like(pathFilter, "%"+filter.getValue()+"%"));
                }else{
                    //try as non-string using equal
                    Path<?> pathFilterNonString = getPath(filter.getKey(), usuario, siteType);
                    filterCondition = cb.and(filterCondition, cb.equal(pathFilterNonString, filter.getValue()));
                }
                */
            }
        }
        query.where(filterCondition);
        
        //pagination
        TypedQuery<Usuario> typedQuery = em.createQuery(query);
        if (pageSize >= 0){
            typedQuery.setMaxResults(pageSize);
        }
        if (first >= 0){
            typedQuery.setFirstResult(first);
        }
        return typedQuery.getResultList();
    }

}

