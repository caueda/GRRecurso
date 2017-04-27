package br.com.grrecurso.entities;

import org.hibernate.envers.RevisionListener;

import br.com.grrecurso.seguranca.spring.user.GRRecursoUser;
import br.com.grrecurso.seguranca.spring.util.SpringSecUtil;

public class GRRecursoRevisionEntityListener implements RevisionListener {
	private GRRecursoUser principal = null;
	@Override
	public void newRevision(Object revisionEntity) {		 
		 try {
			 principal = SpringSecUtil.getPrincipal();
			 if(principal == null) {
				 throw new Exception("Requisição sem usuário logado.");
			 }
		 } catch(Exception e){
			 //Chamada pela API-Restful não possui contexto JSF
			 principal = new GRRecursoUser("api", "", false, false, false, false, null);			 
			 principal.setEmail("api@admin.com");
			 principal.setIdUsuario(-1l);
			 principal.setNome("api");	
		 }
		 GRRecursoRevisionEntity revEntity = (GRRecursoRevisionEntity) revisionEntity;
		 revEntity.setUsername(principal.getEmail());
	}
}
