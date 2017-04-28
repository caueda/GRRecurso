package br.com.grrecurso.seguranca.spring.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import br.com.grrecurso.dominio.DominioTipoPermissao;
import br.com.grrecurso.entities.usuario.Modulo;
import br.com.grrecurso.entities.usuario.PerfilUsuario;
import br.com.grrecurso.entities.usuario.Permissao;
import br.com.grrecurso.entities.usuario.Role;
import br.com.grrecurso.entities.usuario.Usuario;
import br.com.grrecurso.service.login.UsuarioSvcLocal;

@Component
public class UserDetailService implements UserDetailsService {
	protected Log logger = LogFactory.getLog(this.getClass());
	
	private static final int NAO = 0;
	
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
		
		Map<String, String> permissaoItemMenu = new HashMap<String, String>();
		Map<String, String> roles = new HashMap<String, String>();
		
		if(usuario.getIsDesenvolvedor().intValue() == NAO) {
			for(PerfilUsuario perfil : usuario.getPerfis()) {
				for(Role role : perfil.getRoles()) {				
					authorities.add(new SimpleGrantedAuthority(role.getNome()));
					roles.put(role.getNome(), role.getNome());
					for(Permissao permissao : role.getPermissoes()) {
						if(permissao.getTipoPermissao().equals(DominioTipoPermissao.ITEM_MENU)) {
							permissaoItemMenu.put(permissao.getNome(), permissao.getNome());
						}
					}
				}
			}
		}
		
		GRRecursoUser grrecursoUser = new GRRecursoUser(email, password, enabled, accountNonExpired, credentialNonExpired,
				accountNonLocked, authorities);
		grrecursoUser.setModuleIds(new ArrayList<Long>());
		for(Modulo modulo : usuario.getModulos()){
			grrecursoUser.getModuleIds().add(modulo.getIdModulo());
		}
		grrecursoUser.setIsDesenvolvedor(usuario.getIsDesenvolvedor());
		grrecursoUser.setIdUsuario(usuario.getIdUsuario());
		
		if(grrecursoUser.getIsDesenvolvedor().intValue() == NAO) {
			grrecursoUser.setPermissaoItemMenu(permissaoItemMenu);
		} else {
			grrecursoUser.setPermissaoItemMenu(new FakeMap<String, String>());
		}
		
		grrecursoUser.setRoles(roles);
		
		grrecursoUser.setNome(usuario.getNome());
		grrecursoUser.setEmail(usuario.getEmail());
		
		return grrecursoUser;
	}
}
