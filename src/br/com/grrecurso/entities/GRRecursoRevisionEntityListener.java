package br.com.grrecurso.entities;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.envers.RevisionListener;

import br.com.grrecurso.entities.usuario.UserBean;

public class GRRecursoRevisionEntityListener implements RevisionListener {
	private UserBean userBean = null;
	@Override
	public void newRevision(Object revisionEntity) {
		 HttpSession session = null;
		 try {
			 session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			 if(session != null){
				 userBean = (UserBean)session.getAttribute(UserBean.USER_LOGGED);
			 } 
		 } catch(Exception e){
			 //Chamada pela API-Restful não possui contexto JSF
			 userBean = new UserBean();
			 userBean.setEmail("api@admin.com");
			 userBean.setIdUsuario(-1l);
			 userBean.setNome("api");	
		 }
		 GRRecursoRevisionEntity revEntity = (GRRecursoRevisionEntity) revisionEntity;
		 revEntity.setUsername(userBean.getEmail());
	}
}
