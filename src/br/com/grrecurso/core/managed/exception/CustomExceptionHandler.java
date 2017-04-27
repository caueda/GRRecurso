package br.com.grrecurso.core.managed.exception;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import com.ocpsoft.pretty.PrettyContext;

@SuppressWarnings("all")
public class CustomExceptionHandler extends ExceptionHandlerWrapper {
 
    private ExceptionHandler wrapped;
 
    //Obtém uma instância do FacesContext
    final FacesContext facesContext = FacesContext.getCurrentInstance();
 
    //Obtém um mapa do FacesContext    
	final Map requestMap = facesContext.getExternalContext().getRequestMap();
 
    //Obtém o estado atual da navegação entre páginas do JSF
    final NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
 
    //Declara o construtor que recebe uma exceptio do tipo ExceptionHandler como parâmetro
    CustomExceptionHandler(ExceptionHandler exception) {
        this.wrapped = exception;
    }
 
    //Sobrescreve o método ExceptionHandler que retorna a "pilha" de exceções
    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }
 
    //Sobrescreve o método handle que é responsável por manipular as exceções do JSF    
	@Override
    public void handle() throws FacesException {
 
        final Iterator<ExceptionQueuedEvent> iterator = getUnhandledExceptionQueuedEvents().iterator();
        
        FacesContext faces = FacesContext.getCurrentInstance();
        
        String message = null;
        if(faces != null && faces.getExternalContext().getRequestMap().get(PermissionException.class.getSimpleName()) != null) {
        	message = faces.getExternalContext().getRequestMap().get(PermissionException.class.getSimpleName()).toString();
        }
        
        while (iterator.hasNext()) {
            ExceptionQueuedEvent event = iterator.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();            
 
            Throwable exception = context.getException();
            
            String phaseId = PrettyContext.getCurrentInstance().getCurrentViewId();
            
            try { 
            	
            	String messageDetail = null;
            	if(message == null) {
            		messageDetail = exception.getMessage();
            	} else {
            		messageDetail = message;
            	}
            	
            	if(messageDetail.contains(PermissionException.class.getName())) {
            		messageDetail = getPermissionException(exception).getMessage();
            	}
 
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
                    (FacesMessage.SEVERITY_ERROR, messageDetail, ""));               
 
                navigationHandler.handleNavigation(facesContext, null, "/permissionDenied.jsf");
 
                // Renderiza a pagina de erro e exibe as mensagens
                facesContext.renderResponse();
            } finally {
                // Remove a exeção da fila
                iterator.remove();
            }
        }
        // Manipula o erro
        getWrapped().handle();
    }
	
	private PermissionException getPermissionException(Throwable exception) {
		if(exception instanceof PermissionException) {
			return (PermissionException) exception;
		} else {
			return getPermissionException(exception.getCause());
		}
	}
}
