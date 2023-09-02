package br.com.grrecurso.entities;

import javax.persistence.Entity;

import lombok.Data;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

@Entity
@Data
@RevisionEntity(GRRecursoRevisionEntityListener.class)
public class GRRecursoRevisionEntity extends DefaultRevisionEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7802146736649694434L;
	private String username;
}
