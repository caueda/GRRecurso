package br.com.grrecurso.core.service;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.grrecurso.core.persistence.GenericEntity;

@Stateless
public class GenericService extends AbstractService<GenericEntity, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7821349291779399207L;

	protected GenericService() {
		super(GenericEntity.class);
	}
	
	public <T> List<T> list(Map<String, Object> filter, Class<T> clazzEntity){
		List<T> result = null;
		Criteria criteria = getSession().createCriteria(clazzEntity);
		for(Map.Entry<String, Object> entry : filter.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		result = criteria.list();
		return result;
	}
}
