package br.com.grrecurso.seguranca.spring.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.grrecurso.dominio.DominioAtivoInativo;
import br.com.grrecurso.entities.usuario.Role;
import br.com.grrecurso.entities.usuario.Usuario;
import br.com.grrecurso.service.login.UsuarioSvcLocal;

@Component
public class UserDetailService implements UserDetailsService {
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired UsuarioSvcLocal usuarioSvcLocal;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = null;
		try {
			usuario = usuarioSvcLocal.findByEmail(email);
			if(usuario == null) {
				throw new UsernameNotFoundException("E-mail não encontrado.");
			}
		} catch(Exception e) {
			throw new UsernameNotFoundException("Erro no banco de dados. Contacte o administrador do sistema.");
		}
		return obterUsuario(usuario);
	}
	
	public User obterUsuario(Usuario usuario) {
		String email = usuario.getEmail();
		String password = usuario.getSenha();
		boolean enabled = usuario.getStatus().equals(DominioAtivoInativo.ATIVO);
		boolean accountNonExpired = true;
		boolean credentialNonExpired = true;
		boolean accountNonLocked = true;
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(Role role : usuario.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getNome()));
		}
		
		User springUser = new User(email, password, enabled, accountNonExpired, credentialNonExpired,
				accountNonLocked, authorities);
		
		return springUser;
	}
}
