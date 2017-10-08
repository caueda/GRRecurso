package br.com.grrecurso.seguranca.spring.configuration;

import java.util.Arrays;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jndi.JndiTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.grrecurso.seguranca.spring.CustomAuthenticationEntryPointHandler;
import br.com.grrecurso.seguranca.spring.handlers.AccessDeniedHandlerImpl;
import br.com.grrecurso.seguranca.spring.handlers.AutenticationFailureImpl;
import br.com.grrecurso.seguranca.spring.handlers.AuthenticationSuccessImpl;
import br.com.grrecurso.seguranca.spring.user.UserDetailService;
import br.com.grrecurso.service.login.UsuarioSvcLocal;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true, securedEnabled=true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	AuthenticationSuccessImpl authenticationSuccessHandler;
	@Autowired
	AutenticationFailureImpl authenticationFailureHandler;
	@Autowired
	AccessDeniedHandlerImpl accessDeniedHandler;
	
//	@Autowired
//	DataSource dataSource;
	
	@Autowired UserDetailService userDetailService;
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
	
	@Bean(name="usuarioSvcLocal")
	public UsuarioSvcLocal usuarioSvcLocal() {
		UsuarioSvcLocal usuarioSvcLocal = null;
		JndiTemplate jndi = new JndiTemplate();
        try {
        	usuarioSvcLocal = (UsuarioSvcLocal) jndi.lookup("java:app/grrecurso-ejb/UsuarioService!br.com.grrecurso.service.login.UsuarioSvcLocal");
        } catch (NamingException e) {
            logger.error("NamingException for java:app/grrecurso-ejb/UsuarioService!br.com.grrecurso.service.login.UsuarioSvcLocal", e);
        }
        return usuarioSvcLocal;
	}
	
	
	@Bean(name = "dataSource")
    public DataSource dataSourceJndi() {
        DataSource dataSource = null;
        JndiTemplate jndi = new JndiTemplate();
        try {
            dataSource = (DataSource) jndi.lookup("java:/jboss/GRRecursoPool");
        } catch (NamingException e) {
            logger.error("NamingException for java:comp/env/jdbc/yourname", e);
        }
        return dataSource;
    }
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {		
		auth.userDetailsService(userDetailService)
		    .passwordEncoder(new BCryptPasswordEncoder());
//		auth.jdbcAuthentication().dataSource(dataSource)
//			.usersByUsernameQuery("select email, senha, status from usuario where email = ?")
//			.authoritiesByUsernameQuery("select u.email, r.nome as role from usuario u join usuario_role ur on ur.id_usuario = u.id_usuario "
//					+ " join role r on r.id_role = ur.id_role where u.email = ? ");
	}
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("USER");		
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
				.authorizeRequests()				
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/javax.faces.resource/**").permitAll()				
				.antMatchers("/loginFailed").permitAll()
				.antMatchers("/permissionDenied.jsf").authenticated()
				.antMatchers("/permissionDeniedPopup.jsf").authenticated()
				.antMatchers(HttpMethod.OPTIONS, "/public/api").permitAll()				
				.antMatchers("/public/api/**").permitAll()
				.antMatchers("/maxSession").permitAll()
				.antMatchers("/home.jsf").authenticated();
		
				AcessoWebConfig acesso = new AcessoWebConfig(http);
				acesso.aplicar();
				
				/* Nega o acesso via /application/../pagina.jsf */
//				.antMatchers("/application/**").denyAll()
			http.authorizeRequests()			
			    .anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login").permitAll()
				.usernameParameter("email").passwordParameter("senha")				
				.successHandler(authenticationSuccessHandler)
				.failureHandler(authenticationFailureHandler)
				.and().csrf().ignoringAntMatchers("/public/api/**")
				.and()
			.sessionManagement()
				.maximumSessions(1).maxSessionsPreventsLogin(false)
				;
		http.csrf().disable();
		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
		http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPointHandler());
		http.logout().deleteCookies("JSESSIONID");
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();		
		configuration.addAllowedOrigin("*");		
		configuration.setAllowedMethods(Arrays.asList("HEAD","GET","POST","PUT","DELETE","PATCH"));
		configuration.setAllowedHeaders(Arrays.asList("Content-Type"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**")
		.antMatchers(HttpMethod.OPTIONS, "/**")
		.antMatchers("/javax.faces.resource/**");
		super.configure(web);
	}
}
