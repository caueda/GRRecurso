package br.com.grrecurso.seguranca;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityLoginConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	AuthenticationSuccessImpl authenticationSuccessHandler;
	@Autowired
	AutenticationFailureImpl authenticationFailureHandler;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("select email, senha, case status when 0 then 1 else 0 end as status from usuario where email = ?")
			.authoritiesByUsernameQuery("select u.email, r.nome as role from usuario u join usuario_role ur on ur.id_usuario = u.id_usuario "
					+ " join role r on r.id_role = ur.id_role where u.email = ? ");
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
				.anyRequest().authenticated()
				.and()						
			.formLogin()
				.loginPage("/login").permitAll()
				.usernameParameter("email").passwordParameter("senha")				
				.successHandler(authenticationSuccessHandler)
				.failureHandler(authenticationFailureHandler)
				.and().csrf()
				.and()
			.sessionManagement()
				.maximumSessions(1).maxSessionsPreventsLogin(true)
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
