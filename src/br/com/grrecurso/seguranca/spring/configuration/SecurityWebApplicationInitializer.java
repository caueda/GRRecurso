package br.com.grrecurso.seguranca.spring.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * SecurityConfig
 * <ul>
 * 	<li>Registra automaticamente o filtro springSecurityFilterChain para cada URL da aplicação</li>
 *  <li>Adiciona o ContextLoaderListener que carrega o SecurityConfig.
 * </ul>
 * @author 
 *
 */
public class SecurityWebApplicationInitializer /*  extends AbstractAnnotationConfigDispatcherServletInitializer */ {
 
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[] { WebSecurityConfig.class};
//    }
//  
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return null;
//    }
//  
//    @Override
//    protected String[] getServletMappings() {
//        return new String[] { "/" };
//    }
 
}
