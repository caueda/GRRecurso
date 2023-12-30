package br.com.grrecurso.managed.publico;

import br.com.grrecurso.core.managed.AbstractManagedBean;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.cdi.ViewScoped;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
@Getter @Setter
@URLMappings( mappings= {
		@URLMapping(id="publico", pattern="/app/publico", viewId="/application/publico/index.jsf"),
})
public class PublicoAction extends AbstractManagedBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7537013458287313484L;
	@Inject
	private Greeting greeting;
	
	@PostConstruct
	public void init() {
		logger.info("[PublicoAction.init] " + this.toString());
	}
	
	@PreDestroy
	public void destroy() {
		logger.info("[PublicoAction.destroy] " + this.toString());
	}

	public String greeting() {
		return greeting.greet("World");
	}
}
