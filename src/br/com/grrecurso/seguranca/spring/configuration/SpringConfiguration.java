package br.com.grrecurso.seguranca.spring.configuration;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jndi.JndiTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import br.com.grrecurso.service.login.UsuarioSvcLocal;

@org.springframework.context.annotation.Configuration
@EnableWebMvc
@ComponentScan(basePackages = "br.com.grrecurso.seguranca.spring")
public class SpringConfiguration extends WebMvcConfigurerAdapter {
	protected Log logger = LogFactory.getLog(this.getClass());
	
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
	
//	@Bean(name = "dataSource")
//	public DriverManagerDataSource dataSource() {		
//		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/grrecurso");
//		driverManagerDataSource.setUsername("grrecurso");
//		driverManagerDataSource.setPassword("welcome1");
//		return driverManagerDataSource;
//	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	/*
	 * Configure ResourceHandlers to serve static resources like CSS/ Javascript
	 * etc...
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}