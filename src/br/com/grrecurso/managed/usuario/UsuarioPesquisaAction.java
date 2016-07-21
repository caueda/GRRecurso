package br.com.grrecurso.managed.usuario;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.com.grrecurso.dominio.DominioAtivoInativo;
import br.com.grrecurso.dominio.DominioSexo;
import br.com.grrecurso.entities.usuario.Usuario;
import br.com.grrecurso.managed.AbstractManagedBean;
import br.com.grrecurso.service.login.UsuarioSvcLocal;

@Named
@RequestScoped
@URLMappings( mappings= {
		@URLMapping(id="userPesquisa", pattern="/app/usuario/pesquisa", viewId="/application/user/usuarioPesquisa.jsf"),
})
public class UsuarioPesquisaAction extends AbstractManagedBean {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7537013458287313484L;
	private Usuario usuario;
	
	@EJB
	private UsuarioSvcLocal usuarioSvcLocal;
	@Inject
	protected BeanManager beanManager;
	
	private LazyDataModel<Usuario> listaUsuarios;
	
	@PostConstruct
	public void init() {
		logger.info("[UsuarioPesquisaAction.init] " + this.toString());
		this.listaUsuarios = new LazyDataModel<Usuario>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
            public List<Usuario> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List<Usuario> result = usuarioSvcLocal.list(first, pageSize, sortField, sortOrder, filters);
                listaUsuarios.setRowCount(usuarioSvcLocal.count(sortField, sortOrder, filters));
                return result;
            }
        };
	}	
	
	@PreDestroy
	public void destroy() {
		logger.info("[UsuarioPesquisaAction.destroy] " + this.toString());
	}
	
	public boolean filterByName(Object value, Object filter, Locale locale) {
//        String filterText = (filter == null) ? null : filter.toString().trim();
        return true;
    }
	
	public void consultar() {
		List<Usuario> usuarios = usuarioSvcLocal.list(getUsuario());
		listaUsuarios.setWrappedData(usuarios);		
		listaUsuarios.setRowCount(usuarios.size());
	}
	
	public String persist() {
		usuarioSvcLocal.saveOrUpdate(usuario);
		return "";
	}

	public LazyDataModel<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}
	
	public void setListaUsuarios(LazyDataModel<Usuario> listaUsuarios) {
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
