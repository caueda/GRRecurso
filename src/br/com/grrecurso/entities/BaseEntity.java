package br.com.grrecurso.entities;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7950810171021897788L;

	@Override
	public String toString() {
	    return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}
	
	public abstract Long getId();
	public abstract void setId(Long id);
}
