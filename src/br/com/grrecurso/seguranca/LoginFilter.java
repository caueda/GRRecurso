package br.com.grrecurso.seguranca;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.grrecurso.entities.UserBean;

/**
 * Servlet Filter implementation class LoginFilter
 * @deprecated - Foi a primeira solu��o para aut�ntica��o antes de configurar o Spring Security.
 * Ficar� apenas para conhecimento de como implementar um Filter para aut�ntica��o.
 */
//@WebFilter(dispatcherTypes = {
//				DispatcherType.REQUEST, 
//				DispatcherType.FORWARD
//		}
//					, urlPatterns = { "/*" })
public class LoginFilter implements Filter {
	private static final Logger logger = Logger.getLogger(LoginFilter.class.getName()); 
    /**
     * Default constructor. 
     */
    public LoginFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	public String getURL(final HttpServletRequest req) {
		return "http://" + req.getLocalAddr() + ":" + req.getLocalPort() + req.getContextPath() + "/";
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {		
	    HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;
	    HttpSession session = req.getSession(false);
	    UserBean login = null;
	    if(session != null){
	    	login = (UserBean) session.getAttribute(UserBean.USER_LOGGED);
	    }
	    String path = req.getRequestURI().substring(req.getContextPath().length());	  
	    
	    @SuppressWarnings("unused")
		String context = request.getServletContext().getContextPath();
	    
	    res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        res.setDateHeader("Expires", 0); // Proxies.
        
        logger.log(Level.INFO, "url: {0}", path);
	    
        //Liberar recursos: js e css.
	    if(Pattern.matches(".*resources.*|.*javax\\.faces\\.resource.*|.*\\.js[;jsessionid=]*.*|.*\\.css[;jsessionid=]*.*|.*\\.css\\.curso[;jsessionid=]*.*|.*\\.js\\.curso[;jsessionid=]*.*", path)){
	    	chain.doFilter(request, response);	    	
	    } else if(login != null && path.contains("home")){
	    	chain.doFilter(request, response);
	    } else if(login == null && !path.contains("autenticar")){
	    	res.sendRedirect(req.getContextPath() + "/index.jsp");
	    } else if(path.contains("autenticar") || path.contains("index.jsp")){
	    	if(login != null){
	    		res.sendRedirect(req.getContextPath() + "/home.jsf");
	    	} else
	    		chain.doFilter(request, response);
	    } else {
	    	if(login != null && path.contains("deslogar"))
	    		chain.doFilter(request, response);
	    	else if(login != null && path.equals("/")){
	    		res.sendRedirect(req.getContextPath() + "/home.jsf");
	    	} else {
	    		chain.doFilter(request, response);
	    	}
	    }
	}

	
	public static void main(String[] args) {
		String url = "/javax.faces.resource/jquery-1.12.1.min.js.curso;jsessionid=e5mOtJ8gBrv-tJtlygs5fkB5Q_w6Xz41xnZBhHP8.matrix";
		System.out.println(Pattern.matches(".*javax\\.faces\\.resource.*|.*\\.js[;jsessionid=]*.*", url));
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
