package br.com.grrecurso.core.interceptor;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.com.grrecurso.core.interceptor.annotation.WebAccess;

@WebAccess
@Interceptor
public class WebInterceptor implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6920609609332217993L;

	@PostConstruct
	public void checkAccess(InvocationContext context) throws Exception{
		System.out.println("Method: " + context.getMethod());
		context.proceed();
	}

}
