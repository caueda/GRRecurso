package br.com.grrecurso.managed;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.grrecurso.core.managed.AbstractManagedBean;

@Named
public class IdleMonitorView extends AbstractManagedBean {
	public static final String MESSAGE_MONITOR = "messageMonitor";
    /**
	 * 
	 */
	private static final long serialVersionUID = 1610661144086364381L;

	public Date getData() {
		return new Date();
	}
	
	public void onIdle() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,  "No activity.", "What are you doing over there?");		
        FacesContext.getCurrentInstance().addMessage(MESSAGE_MONITOR, message);
    }
 
    public void onActive() {
    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Welcome Back", "Well, that's a long coffee break!");
        FacesContext.getCurrentInstance().addMessage(MESSAGE_MONITOR, message);
    }
}