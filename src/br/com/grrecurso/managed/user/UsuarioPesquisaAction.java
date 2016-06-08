package br.com.grrecurso.managed.user;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.com.grrecurso.ejb.login.UsuarioBean;
import br.com.grrecurso.entities.Usuario;
import br.com.grrecurso.enumerator.DominioAtivoInativo;
import br.com.grrecurso.managed.AbstractManagedBean;

@Named
@ViewScoped

@URLMappings( mappings= {
		@URLMapping(id="userPesquisa", pattern="/app/usuario/pesquisa", viewId="/application/user/usuarioPesquisa.jsf"),
		
})
public class UsuarioPesquisaAction extends AbstractManagedBean implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7537013458287313484L;
	private Usuario usuario;
	private List<Usuario> listaUsuarios;
	
	@EJB
	private UsuarioBean usuarioBean;
	
	public String persist() {
		usuarioBean.saveOrUpdate(usuario);
		return "";
	}
	
	public void consultar() {
		setListaUsuarios(usuarioBean.list(getUsuario()));
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public DominioAtivoInativo[] getListaStatus() {
		return DominioAtivoInativo.values();
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
