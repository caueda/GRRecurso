package br.com.grrecurso.core.service;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import org.hibernate.Query;

import br.com.grrecurso.core.managed.CriteriaBean;
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
	
	@SuppressWarnings("unchecked")
	public <T> List<T> list(Map<String, CriteriaBean> filter, Class<T> clazzEntity){
		List<T> result = null;
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT vo FROM " + clazzEntity.getName() + " vo WHERE 1=1 ");
		for(Map.Entry<String, CriteriaBean> entry : filter.entrySet()){
			CriteriaBean bean = entry.getValue();
			hql.append(" and lower(vo." + entry.getKey() + ")" + bean.getOperacao().replace(":value", bean.getSingleValue().toString()));
		}
		Query query = getSession().createQuery(hql.toString());
		result = query.list();
		return result;
	}
}
