package br.com.grrecurso.entities;

import javax.persistence.Entity;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

@Entity
@org.hibernate.envers.RevisionEntity(GRRecursoEntityListener.class)
public class GRRecursoRevisionEntity extends DefaultRevisionEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7802146736649694434L;
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
