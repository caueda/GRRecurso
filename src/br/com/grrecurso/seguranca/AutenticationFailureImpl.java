package br.com.grrecurso.seguranca;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class AutenticationFailureImpl implements AuthenticationFailureHandler{
	
	protected Log logger = LogFactory.getLog(this.getClass());
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exc) throws IOException, ServletException {
		logger.info(exc.getMessage());
		if(exc.getMessage().contains("Maximum sessions"))
			redirectStrategy.sendRedirect(request, response, "/maxSession?email=" + request.getParameter("email"));
		else
			redirectStrategy.sendRedirect(request, response, "/loginFailed");
	}

}
