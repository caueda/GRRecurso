package br.com.grrecurso.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.grrecurso.ejb.login.LoginBean;
import br.com.grrecurso.entities.UserBean;
import br.com.grrecurso.entities.Usuario;
import br.com.grrecurso.util.WebUtil;

/**
 * @deprecated - Função delegada para o Spring Security.
 *
 */
/*
@WebServlet(urlPatterns="/autenticar")
*/
public class ServletAutenticacao extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1997026148543236463L;

	@Inject private LoginBean loginBean;
	@Inject private UserBean userBean;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = (String)req.getParameter("login");
		String senha = (String)req.getParameter("senha");
		try {
			Usuario usuario = loginBean.autenticar(login, senha);
			if(usuario != null){				
				 HttpSession session = req.getSession(true);
				 userBean.setUsuario(usuario);				 
				 WebUtil.cacheSession(userBean, session, true);
				 resp.sendRedirect(req.getContextPath() + "/home.jsf");
			} else {				
				resp.sendRedirect(req.getContextPath() + "/index.jsp?mensagem=" + URLEncoder.encode("E-mail ou senha invÃ¡lida!", "UTF-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
}
