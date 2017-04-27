package br.com.grrecurso.service.login;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import org.primefaces.model.SortOrder;

import br.com.grrecurso.core.security.annotation.IgnorarPermissoes;
import br.com.grrecurso.entities.usuario.Usuario;

@Local
public interface UsuarioSvcLocal extends UsuarioSvcRemote {	
	public List<Usuario> list(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);
	public int count(String sortField, SortOrder sortOrder, Map<String, Object> filters);
	public Usuario saveOrUpdate(Usuario usuario);
	public void alterarSenha(Long idUsuario, String novaSenha);
	public List<Usuario> list(Usuario usuarioPesquisa);
	@IgnorarPermissoes
	public Usuario findByEmail(String email);
	/**
	 * Não gera log no Envers.
	 * @param entity
	 */
	@IgnorarPermissoes
	public void updateDataLogin(Usuario entity);
	/**
	 * Não gera log no Envers.
	 * @param entity
	 */
	@IgnorarPermissoes
	public void updateDataLoginNULL(Usuario entity);
}
