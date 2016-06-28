package br.com.grrecurso.seguranca.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@org.springframework.context.annotation.Configuration
@EnableWebMvc
@ComponentScan(basePackages = "br.com.grrecurso.seguranca")
public class Configuration extends WebMvcConfigurerAdapter{
    
   @Bean
   public ViewResolver viewResolver() {
       InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
       viewResolver.setViewClass(JstlView.class);
       viewResolver.setPrefix("/");
       viewResolver.setSuffix(".jsp");

       return viewResolver;
   }

    /*
    * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
    */
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
   }
}