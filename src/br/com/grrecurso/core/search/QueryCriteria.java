package br.com.grrecurso.core.search;

import java.io.Serializable;

public class QueryCriteria<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1199854068464998943L;
	private Class<T> clazzEntity;
	private String field;
	private String condition;
	private Object value;
	private boolean eager;
	private String valueType;
	
	public QueryCriteria(Class<T> clazz){
		super();
		setClazzEntity(clazz);
	}
	
	public Class<T> getClazzEntity() {
		return clazzEntity;
	}
	
	private void setClazzEntity(Class<T> clazzEntity){
		this.clazzEntity = clazzEntity;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public boolean isEager() {
		return eager;
	}

	public void setEager(boolean eager) {
		this.eager = eager;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((condition == null) ? 0 : condition.hashCode());
		result = prime * result + (eager ? 1231 : 1237);
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result + ((valueType == null) ? 0 : valueType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		QueryCriteria other = (QueryCriteria) obj;
		if (condition == null) {
			if (other.condition != null)
				return false;
		} else if (!condition.equals(other.condition))
			return false;
		if (eager != other.eager)
			return false;
		if (field == null) {
			if (other.field != null)
				return false;
		} else if (!field.equals(other.field))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		if (valueType == null) {
			if (other.valueType != null)
				return false;
		} else if (!valueType.equals(other.valueType))
			return false;
		return true;
	}
}
