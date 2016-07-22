package br.com.grrecurso.seguranca.spring.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import br.com.grrecurso.entities.usuario.UserBean;
import br.com.grrecurso.entities.usuario.Usuario;
import br.com.grrecurso.service.login.UsuarioSvcLocal;

@Component
public class AuthenticationSuccessImpl implements AuthenticationSuccessHandler {
    protected Log logger = LogFactory.getLog(this.getClass());
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();    
    
    @Autowired UsuarioSvcLocal usuarioSvcLocal;
    
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.Authentication auth) throws IOException, ServletException {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		HttpSession session = request.getSession(false);
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
			Usuario usuario = usuarioSvcLocal.findByEmail(userName);
			if(usuario != null) {
				logger.debug("Usuário: " + usuario.getNome());
				UserBean userBean = new UserBean(usuario);
				if(session != null) {
					session.setAttribute(UserBean.USER_LOGGED, userBean);
				}
			}
		} else {
			userName = principal.toString();
		}
		
		System.out.println(userName);
		redirectStrategy.sendRedirect(request, response, "/");
	}	
}
