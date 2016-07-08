package br.com.grrecurso.managed.usuario;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.com.grrecurso.dominio.DominioAtivoInativo;
import br.com.grrecurso.dominio.DominioSexo;
import br.com.grrecurso.entities.usuario.Usuario;
import br.com.grrecurso.managed.AbstractManagedBean;
import br.com.grrecurso.service.login.UsuarioSvcLocal;

@Named
@ViewScoped
@URLMappings( mappings= {
		@URLMapping(id="userPesquisa", pattern="/app/usuario/pesquisa", viewId="/application/user/usuarioPesquisa.jsf"),
})
public class UsuarioPesquisaAction extends AbstractManagedBean {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7537013458287313484L;
	private Usuario usuario;
	private List<Usuario> listaUsuarios;
	
	@EJB
	private UsuarioSvcLocal usuarioSvcLocal;
	@Inject
	protected BeanManager beanManager;
	
	@PostConstruct
	public void init() {
		System.out.println("[UsuarioPesquisaAction.init] " + this.toString());
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("[UsuarioPesquisaAction.destroy] " + this.toString());
	}
	
	public String persist() {
		usuarioSvcLocal.saveOrUpdate(usuario);
		return "";
	}

	public void consultar() {
		setListaUsuarios(usuarioSvcLocal.list(getUsuario()));
//		printScopedReferences(beanManager);
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
	
	public DominioSexo[] getListaSexo() {
		return DominioSexo.values();
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
