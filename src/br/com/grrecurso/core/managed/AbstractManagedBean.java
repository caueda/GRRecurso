package br.com.grrecurso.core.managed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Conversation;
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

import br.com.grrecurso.core.managed.exception.PermissionException;
import br.com.grrecurso.core.security.annotation.IgnorarPermissoes;
import br.com.grrecurso.core.service.GenericService;
import br.com.grrecurso.producer.qualifiers.UsuarioLogado;
import br.com.grrecurso.seguranca.spring.user.GRRecursoUser;

@SuppressWarnings("unchecked")
public abstract class AbstractManagedBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected static final String SEARCH_OBJECT = "br.com.grrecurso.core.managed.SearchEngine";
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Inject
	private Conversation conversation;
	
	@Inject GenericService genericService;
	
	/*
	 * Id do componente messages do aplicacaoTemplate.xhtml
	 */
	public static final String MESSAGE = "messages";
	
	public static final String INCLUIR = "incluir";
	public static final String ALTERAR = "alterar";
	public static final String PESQUISAR = "pesquisar";
	public static final String EXCLUIR = "excluir";
	
	@Inject @UsuarioLogado
	protected GRRecursoUser principal;	
	
	private String tipoOperacao;
	
	public AbstractManagedBean() {		
		super();
	}
	
	@IgnorarPermissoes
	public boolean isIncluir() {
		return getTipoOperacao().equals(INCLUIR);
	}
	@IgnorarPermissoes
	public boolean isAlterar() {
		return getTipoOperacao().equals(ALTERAR);
	}
	@IgnorarPermissoes
	public boolean isPesquisar() {
		return getTipoOperacao().equals(PESQUISAR);
	}
	@IgnorarPermissoes
	public boolean isExcluir() {
		return getTipoOperacao().equals(EXCLUIR);
	}
	@IgnorarPermissoes
	public String redirect(String url) {
		return url + "?faces-redirect=true";
	}
	@IgnorarPermissoes
	public String redirectWithViewParameters(String url){
		return redirect(url) + "&includeViewParams=true";
	}
	@IgnorarPermissoes
	public Object getAttribute(ActionEvent evt, String attributeName){
		return evt.getComponent().getAttributes().get(attributeName);
	}
	@IgnorarPermissoes
	protected String pesquisar(Class<?> clazz) throws ClassNotFoundException{
		addAttributeToFlash(SEARCH_OBJECT,clazz.getName());
		return "/application/search/searchPrototype.jsf";
	}
	@IgnorarPermissoes
	protected String pesquisar(Class<?> clazz, String page) throws ClassNotFoundException{
		addAttributeToFlash(SEARCH_OBJECT, clazz.getName());
		return page;
	}
	@IgnorarPermissoes
	public <T> void addAttributeToFlash(String name, T value){
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put(name, value);
	}
	@IgnorarPermissoes
	public <T> T getAttributeFromFlash(String name){
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		return (T)flash.get(name);
	}
	
	@IgnorarPermissoes
	public String getURL(final HttpServletRequest req) {
		return "http://" + req.getLocalAddr() + ":" + req.getLocalPort() + req.getContextPath() + "/";
	}
	@IgnorarPermissoes
	protected HttpSession getHttpSession(boolean value) {
		return (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(value);
	}
	@IgnorarPermissoes
	protected Map<String, Object> getApplicationMap() {
		return (Map<String, Object>)FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
	}
	@IgnorarPermissoes
	protected HttpServletRequest getRequest() {
		return (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	@IgnorarPermissoes
	protected void incluirInfo(String summary) {
		incluirInfo(summary, "", true);
	}
	@IgnorarPermissoes
	protected void incluirInfo(String summary, String detail, boolean keepMessage) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		message.setSummary(summary);
		message.setDetail(detail);
		FacesContext.getCurrentInstance().addMessage(MESSAGE, message);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(keepMessage);
//		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}
	@IgnorarPermissoes
	protected void incluirError(String summary) {
		incluirError(summary, "", true);
	}
	@IgnorarPermissoes
	protected void incluirError(String summary, String detail){
		incluirError(summary, detail, true);
	}
	@IgnorarPermissoes
	protected void incluirError(String summary, String detail, boolean keepMessage) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		message.setSummary(summary);
		message.setDetail(detail);
		FacesContext.getCurrentInstance().addMessage(MESSAGE, message);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(keepMessage);
//		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}
	@IgnorarPermissoes
	protected void incluirWarning(String summary) {
		incluirWarning(summary, "", true);
	}
	@IgnorarPermissoes
	protected void incluirWarning(String summary, String detail, boolean keepMessage) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_WARN);
		message.setSummary(summary);
		message.setDetail(detail);
		FacesContext.getCurrentInstance().addMessage(MESSAGE, message);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(keepMessage);
//		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}
	@IgnorarPermissoes
	@SuppressWarnings("el-syntax")
	protected <T> T getBean(Class<T> clazz, String beanName) {
		FacesContext context = FacesContext.getCurrentInstance();
		ELContext elContext = context.getELContext();
		T bean = (T)context.getApplication().getExpressionFactory().createValueExpression(elContext, "#{" + beanName + "}", clazz);
		return bean;
	}
	@IgnorarPermissoes
	public String getTipoOperacao() {
		if(this.tipoOperacao == null) {
			setTipoOperacao(StringUtils.EMPTY);
		}
		return this.tipoOperacao;
	}
	@IgnorarPermissoes
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
    @IgnorarPermissoes
	public Conversation getConversation() {
		return conversation;
	}
    @IgnorarPermissoes
	public String evalAsString(String p_expression)
	{
	    FacesContext context = FacesContext.getCurrentInstance();
	    ExpressionFactory expressionFactory = context.getApplication().getExpressionFactory();
	    ELContext elContext = context.getELContext();
	    ValueExpression vex = expressionFactory.createValueExpression(elContext, p_expression, String.class);
	    String result = (String) vex.getValue(elContext);
	    return result;
	}
    @IgnorarPermissoes
	public GRRecursoUser getPrincipal(){		
		return this.principal;
	}
    @IgnorarPermissoes
	public boolean hasAtLeastOnePermissao(String ... permissoes) {
		for(String permissao : permissoes) {
			if(hasPermissao(permissao)) {
				return true;
			}
		}
		return false;
	}
    @IgnorarPermissoes
	public void validarHasPermissao(String ... permissoes) throws PermissionException {
		if(permissoes == null || permissoes.length == 0) {
			throw new IllegalArgumentException(this.getClass().getName() + ".validarHasPermissao [" + "O parâmetro permissões não deve ser vazio].");
		}
		if(!hasAtLeastOnePermissao(permissoes)) {			
			throw new PermissionException("Usuário não possui a devida Permissão para esta funcionalidade.");
		}
	}
    @IgnorarPermissoes
	public void validarHasRole(String ... roles) throws PermissionException {
		if(roles == null || roles.length == 0) {
			throw new IllegalArgumentException(this.getClass().getName() + ".validarHasRole [" +  "O parâmetro roles não deve ser vazio].");
		}
		if(!hasAtLeastOneRole(roles)) {			
			throw new PermissionException("O usuário não possui a devida Role para esta funcionalidade.");
		}
	}
    @IgnorarPermissoes
	public boolean hasPermissao(String permissao){
		if(getPrincipal() != null && getPrincipal() instanceof GRRecursoUser){
			GRRecursoUser user = (GRRecursoUser) getPrincipal();
			return genericService.isPermitido(user.getIdUsuario(), permissao);						
		}
		return false;
	}
    @IgnorarPermissoes
	public boolean hasRole(String role) {
		if(getPrincipal() != null && getPrincipal() instanceof GRRecursoUser){
			GRRecursoUser user = (GRRecursoUser) getPrincipal();
			return (user.getRoles().containsKey(role));			
		}
		return false;
	}
    @IgnorarPermissoes
	public boolean hasAtLeastOneRole(String ... roles) {
		for(String role : roles) {
			if(hasRole(role)) {
				return true;
			}
		}
		return false;
	}
}
