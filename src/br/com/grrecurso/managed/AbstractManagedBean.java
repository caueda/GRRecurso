package br.com.grrecurso.managed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.el.ELContext;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.context.spi.Context;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.ocpsoft.logging.Logger;

import br.com.grrecurso.entities.usuario.UserBean;
import br.com.grrecurso.producer.qualifiers.UsuarioLogado;

@SuppressWarnings("unchecked")
public abstract class AbstractManagedBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	protected Logger logger = Logger.getLogger(this.getClass());
	/*
	 * Id do componente messages do aplicacaoTemplate.xhtml
	 */
	public static final String MESSAGE = "messages";
	
	public static final String INCLUIR = "incluir";
	public static final String ALTERAR = "alterar";
	public static final String PESQUISAR = "pesquisar";
	public static final String EXCLUIR = "excluir";
	
	@Inject @UsuarioLogado
	protected UserBean userBean;
	
	private String tipoOperacao;
	
	public AbstractManagedBean() {		
		super();
	}
	
	public boolean isIncluir() {
		return getTipoOperacao().equals(INCLUIR);
	}
	
	public boolean isAlterar() {
		return getTipoOperacao().equals(ALTERAR);
	}
	
	public boolean isPesquisar() {
		return getTipoOperacao().equals(PESQUISAR);
	}
	
	public boolean isExcluir() {
		return getTipoOperacao().equals(EXCLUIR);
	}
	
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
		incluirInfo(summary, "", true);
	}
	
	protected void incluirInfo(String summary, String detail, boolean keepMessage) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		message.setSummary(summary);
		message.setDetail(detail);
		FacesContext.getCurrentInstance().addMessage(MESSAGE, message);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(keepMessage);
	}
	
	protected void incluirError(String summary) {
		incluirError(summary, "", true);
	}
	
	protected void incluirError(String summary, String detail){
		incluirError(summary, detail, true);
	}
	
	protected void incluirError(String summary, String detail, boolean keepMessage) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		message.setSummary(summary);
		message.setDetail(detail);
		FacesContext.getCurrentInstance().addMessage(MESSAGE, message);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(keepMessage);
	}
	
	protected void incluirWarning(String summary) {
		incluirWarning(summary, "", true);
	}
	
	protected void incluirWarning(String summary, String detail, boolean keepMessage) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_WARN);
		message.setSummary(summary);
		message.setDetail(detail);
		FacesContext.getCurrentInstance().addMessage(MESSAGE, message);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(keepMessage);
	}
	
	protected <T> T getBean(Class<T> clazz, String beanName) {
		FacesContext context = FacesContext.getCurrentInstance();
		ELContext elContext = context.getELContext();
		T bean = (T)context.getApplication().getExpressionFactory().createValueExpression(elContext, "#{" + beanName + "}", clazz);
		return bean;
	}

	public String getTipoOperacao() {
		if(this.tipoOperacao == null) {
			setTipoOperacao(StringUtils.EMPTY);
		}
		return this.tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
	
    public Map<String, List<String>> getActiveReferences(BeanManager beanManager) {
        Map<String, List<String>> activeReferences = new HashMap<String, List<String>>();
        Set<Bean<?>> beans = beanManager.getBeans(Object.class);
        Context viewScoped = beanManager.getContext(ViewScoped.class);
        Context sessionScope = beanManager.getContext(SessionScoped.class);
        Context requestScoped = beanManager.getContext(RequestScoped.class);
        Context applicationScoped = beanManager.getContext(ApplicationScoped.class);
        
        for (Bean<?> bean : beans) {
        	if(bean.getClass().getName().contains("com.sun.faces.util.cdi11.CDIUtilImpl")) continue;
            Object referenceViewScoped = viewScoped.get(bean);
            Object referenceAppScoped = applicationScoped.get(bean);
            Object referenceSession = sessionScope.get(bean);
            Object referenceRequest = requestScoped.get(bean);

            if (referenceViewScoped != null) {
            	if(activeReferences.get("ViewScoped") == null) activeReferences.put("ViewScoped", new ArrayList<String>()); 
                activeReferences.get("ViewScoped").add(bean.getBeanClass().getName());
            }
            if(referenceAppScoped != null){
            	if(activeReferences.get("ApplicationScoped") == null) activeReferences.put("ApplicationScoped", new ArrayList<String>());
            	activeReferences.get("ApplicationScoped").add(bean.getBeanClass().getName());
            }
            if(referenceSession != null){
            	if(activeReferences.get("SessionScoped") == null) activeReferences.put("SessionScoped", new ArrayList<String>());
            	activeReferences.get("SessionScoped").add(bean.getBeanClass().getName());
            }
            if(referenceRequest != null){
            	if(activeReferences.get("RequestScoped") == null) activeReferences.put("RequestScoped", new ArrayList<String>());
            	activeReferences.get("RequestScoped").add(bean.getBeanClass().getName());
            }
        }

        return activeReferences;
    }
    
    /**
     * Método utilizado para avaliar quais objetos estão sendo inseridos nos escopos.
     * @param beanManager
     */
    @SuppressWarnings("rawtypes")
    public void printScopedReferences(BeanManager beanManager) {
		Map objects = getActiveReferences(beanManager);
		Set keyValues = objects.entrySet();
		logger.info("[Begin] Scoped attributes");
		for(Object obj : keyValues){
			Entry entry = (Entry) obj;
			logger.info(entry.getKey() + ":" + entry.getValue());
		}
		logger.info("[End] Scoped attributes");
    }

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
}
