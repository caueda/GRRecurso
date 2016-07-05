package br.com.grrecurso.entities;

import javax.inject.Inject;


import br.com.grrecurso.entities.usuario.UserBean;
import br.com.grrecurso.producer.qualifiers.UsuarioLogado;

public class GRRecursoRevisionEntityListener implements org.hibernate.envers.RevisionListener {
	@Inject @UsuarioLogado
	UserBean userBean;
	
	@Override
	public void newRevision(Object revisionEntity) {
		 GRRecursoRevisionEntity revEntity = (GRRecursoRevisionEntity) revisionEntity;
		 revEntity.setUsername(userBean.getEmail());
	}
}
