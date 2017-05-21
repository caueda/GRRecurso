package br.com.grrecurso.seguranca.spring.handlers;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import br.com.grrecurso.entities.usuario.Usuario;
import br.com.grrecurso.seguranca.spring.user.GRRecursoUser;
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
		if (principal instanceof UserDetails) {
			userName = ((GRRecursoUser) principal).getUsername();
			Usuario usuario = usuarioSvcLocal.findByEmail(userName);
			usuario.setDataLogin(new Date());
			usuarioSvcLocal.updateDataLogin(usuario);			
		} else {
			userName = principal.toString();
		}
		
		redirectStrategy.sendRedirect(request, response, "/home.jsf");
	}	
}
