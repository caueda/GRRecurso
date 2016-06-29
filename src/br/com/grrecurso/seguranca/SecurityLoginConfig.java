package br.com.grrecurso.seguranca;

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
	AuthenticationHandler authenticationHander;
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("USER");		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/resources/**").permitAll()
				.anyRequest().authenticated()
				.and()						
			.formLogin()
				.loginPage("/login").permitAll()	
				.failureUrl("/loginFailed")
				.successHandler(authenticationHander)
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
