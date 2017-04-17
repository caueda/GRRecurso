package br.com.grrecurso.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> list(Map<String, CriteriaBean> filter, Class<T> clazzEntity){
		List<T> result = null;
		int cont = 0;
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT vo FROM " + clazzEntity.getName() + " vo WHERE 1=1 ");
		for(Map.Entry<String, CriteriaBean> entry : filter.entrySet()){
			CriteriaBean bean = entry.getValue();
			if(bean.getClazz() == null){
				hql.append(" and lower(vo." + entry.getKey() + ")" + bean.getOperacao().replace(":value", bean.getSingleValue().toString()));
			} else if(bean.getClazz().isEnum()) {
				String parameterName = entry.getKey() + "" + ++cont;
				hql.append(" and vo." + entry.getKey() + bean.getOperacao().replace(":value", ":" + parameterName));
				Class<Enum> enumerator = (Class<Enum>) bean.getClazz();
				Enum value = Enum.valueOf(enumerator, bean.getSingleValue().toString());
				parameters.put(parameterName, value);
			}
		}
		Query query = getSession().createQuery(hql.toString());
		for(Entry<String, Object> entry : parameters.entrySet()){
			query.setParameter(entry.getKey(), entry.getValue());
		}
		result = query.list();
		return result;
	}
}
