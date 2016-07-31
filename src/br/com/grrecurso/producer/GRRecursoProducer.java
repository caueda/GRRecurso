package br.com.grrecurso.producer;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.grrecurso.dominio.DominioAtivoInativo;
import br.com.grrecurso.entities.usuario.UserBean;
import br.com.grrecurso.producer.qualifiers.UsuarioLogado;

@RequestScoped
public class GRRecursoProducer {	
	@Produces
	public DominioAtivoInativo[] listaAtivoInativo(){
		return DominioAtivoInativo.values();
	}
	
	@Produces @UsuarioLogado
	public UserBean obtemUserBean() {
		UserBean userBean = null;
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if(session != null) {
			userBean = (UserBean)session.getAttribute(UserBean.USER_LOGGED);
			return userBean;
		} 
		return userBean;
	}
}
