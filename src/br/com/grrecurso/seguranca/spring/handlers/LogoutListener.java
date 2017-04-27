package br.com.grrecurso.seguranca.spring.handlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.stereotype.Component;

import br.com.grrecurso.entities.usuario.Usuario;
import br.com.grrecurso.seguranca.spring.user.GRRecursoUser;
import br.com.grrecurso.service.login.UsuarioSvcLocal;

@Component
public class LogoutListener implements ApplicationListener<SessionDestroyedEvent> {

	@Autowired UsuarioSvcLocal usuarioSvcLocal;
	
    @Override
    public void onApplicationEvent(SessionDestroyedEvent event)
    {
        List<SecurityContext> lstSecurityContext = event.getSecurityContexts();
        GRRecursoUser ud;
        for (SecurityContext securityContext : lstSecurityContext)
        {
            ud = (GRRecursoUser) securityContext.getAuthentication().getPrincipal();           
            Usuario update = new Usuario();
            update.setIdUsuario(ud.getIdUsuario());
            usuarioSvcLocal.updateDataLoginNULL(update);
        }
    }

}