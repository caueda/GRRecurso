package br.com.grrecurso.seguranca.spring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.grrecurso.seguranca.spring.handlers.AutenticationFailureImpl;
import br.com.grrecurso.seguranca.spring.handlers.AuthenticationSuccessImpl;
import br.com.grrecurso.seguranca.spring.user.UserDetailService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebSecurity
public class SpringLoginConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	AuthenticationSuccessImpl authenticationSuccessHandler;
	@Autowired
	AutenticationFailureImpl authenticationFailureHandler;
	
//	@Autowired
//	DataSource dataSource;
	
	@Autowired UserDetailService userDetailService;
	/*
	public static void main(String[] args) {
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		System.out.println(b.encode("welcome1"));
	}
	*/
	
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
		http.authorizeRequests()
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/loginFailed").permitAll()
				.antMatchers("/maxSession").permitAll()
				.antMatchers("/public/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login").permitAll()
				.usernameParameter("email").passwordParameter("senha")				
				.successHandler(authenticationSuccessHandler)
				.failureHandler(authenticationFailureHandler)
				.and().csrf().ignoringAntMatchers("/public/api/**")
				             .ignoringAntMatchers("/login")
				//.disable().exceptionHandling()
				.and()
			.sessionManagement()
				.maximumSessions(1).maxSessionsPreventsLogin(false)
				;
//		http
//		.authorizeRequests()
//			.anyRequest().authenticated()
//			.and()
//		.formLogin()
//			.and()
//		.httpBasic();
	}
}
