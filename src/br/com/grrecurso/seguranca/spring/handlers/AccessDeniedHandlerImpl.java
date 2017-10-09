package br.com.grrecurso.seguranca.spring.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;

@Component
public class AccessDeniedHandlerImpl implements org.springframework.security.web.access.AccessDeniedHandler{
		
		protected Log logger = LogFactory.getLog(this.getClass());
		private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

		@Override
		public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exc) throws IOException, ServletException {
			logger.info(exc.getMessage());
			logger.info(request.getRequestURI());
			String popupParameter = request.getParameter("popup");
			if(popupParameter != null && "true".equals(popupParameter))
				redirectStrategy.sendRedirect(request, response, "/permissionDeniedPopup.jsf");
			else 
				redirectStrategy.sendRedirect(request, response, "/permissionDenied.jsf");
			
		}
}