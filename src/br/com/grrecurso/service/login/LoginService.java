package br.com.grrecurso.service.login;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.grrecurso.entities.usuario.Usuario;
import br.com.grrecurso.service.AbstractService;

@Named
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class LoginService extends AbstractService {	
	
	private static final long serialVersionUID = -1466922676838989315L;

	@Inject private EntityManager em;
	
	@PostConstruct
	private void init(){
		logger.info("Method init [LoginBean] invoked at " + new java.util.Date());
	}
	
	@PreDestroy
	private void destroy(){
		/*
		 * 
		 */
		System.out.println("Finalizing LoginBean at " + new java.util.Date());
	}
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario autenticar(String email, String senha) throws Exception {		
		Query query = em.createNamedQuery("Usuario.findByEmailSenha");
		query.setParameter("email", email);
		query.setParameter("senha", senha);
		List<Usuario> usuarios = query.getResultList();
		if(usuarios != null && !usuarios.isEmpty()){
			Usuario user = usuarios.get(0);
			if(!user.equals(new Usuario())){
				user.setDataLogin(new Date());
				return user;
			}
		}
		return null;
	}

	public Usuario remoteLogin(String email, String senha) throws Exception {		
		return autenticar(email, senha);
	}
	
}
