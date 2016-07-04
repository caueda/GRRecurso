package br.com.grrecurso.service.login;

import java.util.List;

import javax.ejb.Local;

import br.com.grrecurso.entities.usuario.Usuario;

@Local
public interface UsuarioSvcLocal extends UsuarioSvcRemote {
	
	public List<Usuario> listAll();
	public Usuario saveOrUpdate(Usuario usuario);
	public void alterarSenha(Long idUsuario, String novaSenha);
	public List<Usuario> list(Usuario usuarioPesquisa);
}
