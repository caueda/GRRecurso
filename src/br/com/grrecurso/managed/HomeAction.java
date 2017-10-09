package br.com.grrecurso.managed;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.com.grrecurso.core.managed.AbstractManagedBean;
import br.com.grrecurso.service.login.UsuarioService;

@Named
@SessionScoped
@URLBeanName("homeAction")
@URLMappings( mappings= {
		@URLMapping(id="home", pattern="/home", viewId="/home.jsf")
})
public class HomeAction extends AbstractManagedBean {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1338935076679440203L;
	
	@EJB
	private UsuarioService usuarioService;
	
	@PostConstruct
	public void init() {
		logger.info("[HomeAction.init] " + this.toString());
	}
	
	@PreDestroy
	public void destroy() {
		usuarioService.updateAllDataLoginNULL();
		logger.info("[HomeAction.destroy] " + this.toString());
	}
	
	public String getDataHora(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		return sdf.format(new Date());
	}
}
