package br.com.grrecurso.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import br.com.grrecurso.entities.UserBean;
import br.com.grrecurso.util.WebUtil;

@WebServlet(urlPatterns="/deslogar")
public class ServletDeslogar extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1997026148543236463L;

	@Inject private UserBean userBean;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = (HttpSession) req.getSession(false);		
		if(userBean != null && StringUtils.isNotBlank(userBean.getEmail())) {
			WebUtil.logout(userBean, session, true);
		}
		resp.sendRedirect(req.getContextPath() + "/index.jsp");
	}
	
}
