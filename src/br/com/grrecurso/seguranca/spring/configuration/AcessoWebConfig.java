package br.com.grrecurso.seguranca.spring.configuration;

import java.io.Serializable;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public class AcessoWebConfig implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9082695826792282087L;
	
	private HttpSecurity http;
	public AcessoWebConfig(HttpSecurity http){
		this.http = http;
	}
	
	public void aplicar() throws Exception{
		http.authorizeRequests().antMatchers("/app/usuario/role/**").hasAuthority("ROLE_ADMIN");
//		http.authorizeRequests().antMatchers("/app/permissao/criteria/**").hasAuthority("ROLE_ADMIN");
	}
}
