package br.com.grrecurso.core.persistence;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7950810171021897788L;
	
	public abstract Long getId();
	public abstract void setId(Long id);
}
