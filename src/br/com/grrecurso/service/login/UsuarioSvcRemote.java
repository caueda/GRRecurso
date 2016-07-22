package br.com.grrecurso.service.login;

import javax.ejb.Remote;

import br.com.grrecurso.entities.usuario.Usuario;

@Remote
public interface UsuarioSvcRemote {
	public Usuario loadById(Long idUsuario);
	public Usuario findByEmail(String email);
}
