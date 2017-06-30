package br.com.grrecurso.seguranca.spring.configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter;
import org.keycloak.adapters.springsecurity.filter.KeycloakPreAuthActionsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.preauth.x509.X509AuthenticationFilter;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
public class WebSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
	
	protected Log logger = LogFactory.getLog(this.getClass());
	
//	@Autowired
//	DataSource dataSource;
	
	/*
	public static void main(String[] args) {
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		System.out.println(b.encode("welcome1"));
	}
	*/
	
//	@Bean(name = "dataSource")
//	public DriverManagerDataSource dataSource() {		
//		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/grrecurso");
//		driverManagerDataSource.setUsername("grrecurso");
//		driverManagerDataSource.setPassword("welcome1");
//		return driverManagerDataSource;
//	}
	
//	@Bean(name="usuarioSvcLocal")
//	public UsuarioSvcLocal usuarioSvcLocal() {
//		UsuarioSvcLocal usuarioSvcLocal = null;
//		JndiTemplate jndi = new JndiTemplate();
//        try {
//        	usuarioSvcLocal = (UsuarioSvcLocal) jndi.lookup("java:app/grrecurso-ejb/UsuarioService!br.com.grrecurso.service.login.UsuarioSvcLocal");
//        } catch (NamingException e) {
//            logger.error("NamingException for java:app/grrecurso-ejb/UsuarioService!br.com.grrecurso.service.login.UsuarioSvcLocal", e);
//        }
//        return usuarioSvcLocal;
//	}
	
	
//	@Bean(name = "dataSource")
//    public DataSource dataSourceJndi() {
//        DataSource dataSource = null;
//        JndiTemplate jndi = new JndiTemplate();
//        try {
//            dataSource = (DataSource) jndi.lookup("java:/jboss/GRRecursoPool");
//        } catch (NamingException e) {
//            logger.error("NamingException for java:comp/env/jdbc/yourname", e);
//        }
//        return dataSource;
//    }
	
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("USER");		
//	}
	
    /**
     * Registers the KeycloakAuthenticationProvider with the authentication manager.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
	    	SimpleAuthorityMapper simpleAuthorityMapper = new SimpleAuthorityMapper();
	    	simpleAuthorityMapper.setPrefix("ROLE_");
	    	simpleAuthorityMapper.setConvertToUpperCase(true);
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(simpleAuthorityMapper);
        
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }
    
//    @Bean
//    public KeycloakConfigResolver KeycloakConfigResolver() {
//       return new KeycloakSpringBootConfigResolver();
//    }
    
	@Bean
	public FilterRegistrationBean keycloakAuthenticationProcessingFilterRegistrationBean(
			KeycloakAuthenticationProcessingFilter filter) {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
		registrationBean.setEnabled(false);
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean keycloakPreAuthActionsFilterRegistrationBean(KeycloakPreAuthActionsFilter filter) {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
		registrationBean.setEnabled(false);
		return registrationBean;
}
    
    /**
     * Defines the session authentication strategy.
     */
    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
		.sessionAuthenticationStrategy(sessionAuthenticationStrategy()).and()
		.addFilterBefore(keycloakPreAuthActionsFilter(), LogoutFilter.class)
		.addFilterBefore(keycloakAuthenticationProcessingFilter(), X509AuthenticationFilter.class)
		.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint()).and().authorizeRequests()
		.antMatchers("/**").hasRole("ADMIN")
		.anyRequest().permitAll();		
		
//		super.configure(http);
//		http.authorizeRequests()				
//			.antMatchers("/home*").hasRole("admin")
//			.antMatchers("/home.jsf*").hasRole("admin")
//			.antMatchers("/home.xhtml*").hasRole("admin")
//			.antMatchers("/**").authenticated();
		
//				AcessoWebConfig acesso = new AcessoWebConfig(http);
//				acesso.aplicar();
				
				/* Nega o acesso via /application/../pagina.jsf */
//				.antMatchers("/application/**").denyAll()
//			http.authorizeRequests()			
//			    .anyRequest().denyAll()
//				.and()
//			.formLogin()
//				.loginPage("/login").permitAll()
//				.usernameParameter("email").passwordParameter("senha")				
//				.successHandler(authenticationSuccessHandler)
//				.failureHandler(authenticationFailureHandler)
//				.and().csrf().ignoringAntMatchers("/public/api/**")
//				.and()
//			.sessionManagement()
//				.maximumSessions(1).maxSessionsPreventsLogin(false)
//				;
//		http.csrf().disable();
//		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
//		http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPointHandler());
//		http.logout().deleteCookies("JSESSIONID");
	}
}
