package br.com.grrecurso.core.managed;

import java.io.Serializable;
import java.lang.reflect.Method;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.com.grrecurso.core.managed.exception.PermissionException;
import br.com.grrecurso.core.security.annotation.IgnorarPermissoes;
import br.com.grrecurso.core.service.GenericService;
import br.com.grrecurso.producer.qualifiers.UsuarioLogado;
import br.com.grrecurso.seguranca.spring.user.GRRecursoUser;


@SuppressWarnings("cdi-missing-interceptor-binding")
@Interceptor
public class BeanInterceptor implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3275837191207429205L;	
	
	private final static int NAO = 0;

	@Inject
	private GenericService genericService;
	
	@Inject @UsuarioLogado
	protected GRRecursoUser principal;
	
	@AroundInvoke
    public Object checkPermission(InvocationContext ctx) throws Exception {		
		
		if(principal != null && principal.getIsDesenvolvedor().intValue() == NAO) {
			Method m = ctx.getMethod();
			
			String methodName = m.getDeclaringClass().getName() + "." + m.getName();		
			
			boolean contemPermissao = false;
			
			if(!m.isAnnotationPresent(IgnorarPermissoes.class)) {		
				if(genericService.isPermitido(principal.getIdUsuario(), methodName)) {
					contemPermissao = true;
				}			 
				if(!contemPermissao) {
					PermissionException e = new PermissionException("Usuário sem a Permissão necessária para : [" + methodName + "].");
					FacesContext faces = FacesContext.getCurrentInstance();
					if(faces != null) {
						faces.getExternalContext().getRequestMap().put(e.getClass().getSimpleName(), e.getMessage());
						faces.addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
					} 
					throw e;
				}
			}
			
			/*
			if(m.isAnnotationPresent(ValidarPermissoes.class)) {
				boolean contemPermissao = false;
				ValidarPermissoes valP = m.getAnnotation(ValidarPermissoes.class);
				if(valP.permissoes().length == 0) {				
					if(genericService.isPermitido(principal.getIdUsuario(), methodName)) {
						contemPermissao = true;
					}
				} else {
					for(String permissao : valP.permissoes()) {
						if(genericService.isPermitido(principal.getIdUsuario(), permissao)) {
							contemPermissao = true;
							break;
						}
					}
				}
				if(!contemPermissao) {
					throw new PermissionException("Usuário sem a Permissão necessária para : [" + methodName + "].");
				}
			}
			
			if(m.isAnnotationPresent(ValidarRoles.class)) {
				boolean contemRole = false;
				ValidarRoles valR = m.getAnnotation(ValidarRoles.class);
				for(String role : valR.roles()) {
					if(principal.getRoles().containsKey(role)) {
						contemRole = true;
						break;
					}
				}
				if(!contemRole) {
					throw new PermissionException("Usuário sem a Role necessária para : [" + methodName + "].");
				}
			}
			*/
		}
		
		Object methodReturn = ctx.proceed();
        
        return methodReturn;
    }   

}
