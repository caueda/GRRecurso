package br.com.grrecurso.managed;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.grrecurso.core.managed.AbstractManagedBean;

@Named
public class IdleMonitorView extends AbstractManagedBean {
	public static final String MESSAGE_MONITOR = "messageMonitor";
    /**
	 * 
	 */
	protected Log logger = LogFactory.getLog(this.getClass());
	
	private static final long serialVersionUID = 1610661144086364381L;

	public void onIdle() {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		logger.info(request.getRequestURI());
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,  "No activity.", "What are you doing over there?");		
        FacesContext.getCurrentInstance().addMessage(MESSAGE_MONITOR, message);
    }
 
    public void onActive() {
    	HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    	logger.info(request.getRequestURI());
    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Welcome Back", "Well, that's a long coffee break!");
        FacesContext.getCurrentInstance().addMessage(MESSAGE_MONITOR, message);
    }
}