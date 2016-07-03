package br.com.grrecurso.managed;


import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.grrecurso.entities.usuario.UserBean;
import br.com.grrecurso.entities.usuario.Usuario;
import br.com.grrecurso.service.login.LoginService;

@Named("loginAction")
@ViewScoped
public class LoginAction extends AbstractManagedBean {

	private static final long serialVersionUID = -5263706623967677173L;	
	
	@EJB
	private LoginService loginBean;
	
	private String login;
	private String senha;
	
	public LoginAction(){
		logger.info("Creating LoginBean..." + new java.util.Date());
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
    public void onIdle() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                                        "Nenhuma atividade.", "O que você está fazendo por aí ?"));
    }
 
    public void onActive() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                        "Bem vindo", "Esta foi uma longa parada para o café!"));
    }
	
    public String hello(){
    	logger.info("Hello World!");
    	return "home";
    }
    
	public String logar(){
		try {
			Usuario usuario = loginBean.autenticar(getLogin(), getSenha());
			if(usuario != null){				
				 HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				 UserBean userBean = new UserBean(usuario);				 
				 cacheSession(userBean, session, true);
				return redirect("home");
			} else {				
				incluirWarning("E-mail ou senha incorretos.");
				return "";
			}
		} catch (Exception e) {
			incluirError(e.getMessage());
			return "";
		}		
	}
	
	public String logout() throws Exception {
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		UserBean userBean = (UserBean)session.getAttribute(UserBean.USER_LOGGED);
		if(userBean != null) {
			return super.logout(userBean, session, true);
		}
		return redirect("login");
	}
}
