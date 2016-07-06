package br.com.grrecurso.entities;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.envers.RevisionListener;

import br.com.grrecurso.entities.usuario.UserBean;

public class GRRecursoRevisionEntityListener implements RevisionListener {
	private UserBean userBean = null;
	@Override
	public void newRevision(Object revisionEntity) {
		 HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		 if(session != null){
			 userBean = (UserBean)session.getAttribute(UserBean.USER_LOGGED);
		 }
		 GRRecursoRevisionEntity revEntity = (GRRecursoRevisionEntity) revisionEntity;
		 revEntity.setUsername(userBean.getEmail());
	}
}
