package br.com.grrecurso.managed;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.com.grrecurso.ejb.login.UsuarioBean;
import br.com.grrecurso.entities.Usuario;
import br.com.grrecurso.enumerator.DominioAtivoInativo;

@Named
@javax.enterprise.context.RequestScoped
@URLMapping(id="user", pattern="/app/usuario", viewId="/application/user/usuario.jsf")
public class UsuarioAction extends AbstractManagedBean {	
	private Usuario usuario;
	private List<Usuario> listaUsuarios;
	private List<Usuario> listaUsuariosFiltrados;
	
	@EJB
	private UsuarioBean usuarioBean;
	
	public String persist() {
		usuarioBean.saveOrUpdate(usuario);
		return "";
	}
	
	public void consultar() {
		setListaUsuarios(usuarioBean.list(this.usuario));
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	
	public List<Usuario> getListaUsuariosFiltrados() {
		return listaUsuariosFiltrados;
	}

	public void setListaUsuariosFiltrados(List<Usuario> listaUsuariosFiltrados) {
		this.listaUsuariosFiltrados = listaUsuariosFiltrados;
	}	
	
	public DominioAtivoInativo[] getListaStatus() {
		return DominioAtivoInativo.values();
	}
	
	public void incluir() {
		try {			
			usuarioBean.saveOrUpdate(this.usuario);			
			incluirInfo("Usuário incluído com sucesso.");
			setUsuario(new Usuario());
			consultar();
		} catch(Exception e) {
			e.printStackTrace();
			incluirError("Erro ao incluir usuário.", e.getMessage());
		}
	}

	public Usuario getUsuario() {
		if(this.usuario == null) {
			setUsuario(new Usuario());
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
