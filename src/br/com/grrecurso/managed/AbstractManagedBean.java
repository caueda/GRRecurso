package br.com.grrecurso.managed;

import java.io.Serializable;
import java.util.Map;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ocpsoft.logging.Logger;

import br.com.grrecurso.entities.usuario.UserBean;
import br.com.grrecurso.producer.qualifiers.UsuarioLogado;

@SuppressWarnings("unchecked")
public abstract class AbstractManagedBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject @UsuarioLogado
	protected UserBean userBean;
	
	protected Logger logger = Logger.getLogger(this.getClass());
	/*
	 * Id do componente messages do aplicacaoTemplate.xhtml
	 */
	public static final String MESSAGE = "messages";

	public String redirect(String url) {
		return url + "?faces-redirect=true";
	}
	
	public String redirectWithViewParameters(String url){
		return redirect(url) + "&includeViewParams=true";
	}
	
	public Object getAttribute(ActionEvent evt, String attributeName){
		return evt.getComponent().getAttributes().get(attributeName);
	}
	
	public void addAttributeToFlash(String name, Object value){
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put(name, value);
	}
	
	public Object getAttributeFromFlash(String name){
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		return flash.get(name);
	}
	
	
	public String getURL(final HttpServletRequest req) {
		return "http://" + req.getLocalAddr() + ":" + req.getLocalPort() + req.getContextPath() + "/";
	}
	
	protected HttpSession getHttpSession(boolean value) {
		return (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(value);
	}
	
	protected Map<String, Object> getApplicationMap() {
		return (Map<String, Object>)FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
	}
	
	protected HttpServletRequest getRequest() {
		return (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
	protected void incluirInfo(String summary) {
		incluirInfo(summary, "");
	}
	
	protected void incluirInfo(String summary, String detail) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		message.setSummary(summary);
		message.setDetail(detail);
		FacesContext.getCurrentInstance().addMessage(MESSAGE, message);
	}
	
	protected void incluirError(String summary) {
		incluirError(summary, "");
	}
	
	protected void incluirError(String summary, String detail) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		message.setSummary(summary);
		message.setDetail(detail);
		FacesContext.getCurrentInstance().addMessage(MESSAGE, message);
	}
	
	protected void incluirWarning(String summary) {
		incluirWarning(summary, "");
	}
	
	protected void incluirWarning(String summary, String detail) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_WARN);
		message.setSummary(summary);
		message.setDetail(detail);
		FacesContext.getCurrentInstance().addMessage(MESSAGE, message);
	}
	
	protected <T> T getBean(Class<T> clazz, String beanName) {
		FacesContext context = FacesContext.getCurrentInstance();
		ELContext elContext = context.getELContext();
		T bean = (T)context.getApplication().getExpressionFactory().createValueExpression(elContext, "#{" + beanName + "}", clazz);
		return bean;
	}
}
