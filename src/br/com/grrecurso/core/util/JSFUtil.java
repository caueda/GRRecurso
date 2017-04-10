package br.com.grrecurso.core.util;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.grrecurso.seguranca.spring.user.GRRecursoUser;

public class JSFUtil {
	public static String evalAsString(String p_expression)
	{
	    FacesContext context = FacesContext.getCurrentInstance();
	    ExpressionFactory expressionFactory = context.getApplication().getExpressionFactory();
	    ELContext elContext = context.getELContext();
	    ValueExpression vex = expressionFactory.createValueExpression(elContext, p_expression, String.class);
	    String result = (String) vex.getValue(elContext);
	    return result;
	}
	
	public static GRRecursoUser getPrincipal(){
		GRRecursoUser user = (GRRecursoUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
	
	public static boolean hasRole(String role){
		if(getPrincipal() != null){
			for(GrantedAuthority grantedAuthority : getPrincipal().getAuthorities()){
				if(grantedAuthority.getAuthority().equals(role)){
					return true;
				}
			}
		}
		return false;
	}
}
