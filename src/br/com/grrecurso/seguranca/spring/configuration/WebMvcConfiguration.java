package br.com.grrecurso.seguranca.spring.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * The configuration creates a Servlet Filter known as the springSecurityFilterChain 
 * which is responsible for all the security 
 * (protecting the application URLs, validating submitted username and passwords, 
 * redirecting to the log in form, etc) within your application
 * 
 * @author 
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "br.com.grrecurso.seguranca.spring")
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

	/*
	 * Configure ResourceHandlers to serve static resources like CSS/ Javascript
	 * etc...
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}