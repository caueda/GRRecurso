package br.com.grrecurso.seguranca.spring;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

public class CustomAuthenticationEntryPointHandler extends Http403ForbiddenEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		boolean ajaxRedirect = request.getHeader("faces-request") != null
	            && request.getHeader("faces-request").toLowerCase().indexOf("ajax") > -1;
	    if (ajaxRedirect) {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        if (authentication == null) {
	        	response.sendError(HttpServletResponse.SC_FORBIDDEN);
	        }
	    } else {
	    	RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	        redirectStrategy.sendRedirect(request, response, "/login");
//	        super.commence(request, response, authException);
	    }
	}

}
