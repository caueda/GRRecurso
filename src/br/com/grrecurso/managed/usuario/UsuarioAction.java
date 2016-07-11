package br.com.grrecurso.managed.usuario;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

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
		@URLMapping(id="newUsuario", pattern="/app/usuario/#{tipoOperacao : usuarioAction.tipoOperacao}", viewId="/application/user/usuario.jsf"),
		@URLMapping(id="editUsuario", pattern="/app/usuario/#{tipoOperacao : usuarioAction.tipoOperacao}/id/#{idUsuario : usuarioAction.idUsuario}", 
					onPostback=false, viewId="/application/user/usuario.jsf"),
		@URLMapping(id="userpassword", pattern="/app/cpasswd", viewId="/application/user/usuarioPassword.jsf"),
		@URLMapping(id="usermessage", pattern="/app/message/usuario", viewId="/application/msg/mensagem.jsf")
})
public class UsuarioAction extends AbstractManagedBean {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7537013458287313484L;
	private Usuario usuario;
	private String senhaAtual;
	private String novaSenha1;
	private String novaSenha2;
	private Long idUsuario;
	
	@EJB
	private UsuarioSvcLocal usuarioSvcLocal;
	
	@PostConstruct
	public void init() {
		logger.info("[UsuarioAction.init] " + this.toString());
	}
	
	@PreDestroy
	public void destroy() {
		logger.info("[UsuarioAction.destroy] " + this.toString());
	}
	
	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha1() {
		return novaSenha1;
	}

	public void setNovaSenha1(String novaSenha1) {
		this.novaSenha1 = novaSenha1;
	}

	public String getNovaSenha2() {
		return novaSenha2;
	}

	public void setNovaSenha2(String novaSenha2) {
		this.novaSenha2 = novaSenha2;
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public DominioAtivoInativo[] getListaStatus() {
		return DominioAtivoInativo.values();
	}	
	
	public DominioSexo[] getListaSexo() {
		return DominioSexo.values();
	}
	
	public void exibirEdicao(){
		if(!isIncluir() && getIdUsuario() != null) {
			setTipoOperacao(ALTERAR);
			setUsuario(usuarioSvcLocal.loadById(getIdUsuario()));
		}
	}
	
	public void exibirAlterarSenha() {
		if(userBean != null && userBean.getIdUsuario() != null) {			
			setUsuario(usuarioSvcLocal.loadById(userBean.getIdUsuario()));		
		}
	}

	public String alterarSenha(){
		try {
			usuario = usuarioSvcLocal.loadById(userBean.getIdUsuario());
			if(!getSenhaAtual().equals(getUsuario().getSenha())){
				incluirError("A senha atual não confere.");
				return "pretty:";
			}
			if(!getNovaSenha1().equals(getNovaSenha2())){
				incluirError("Confirmação da nova senha inválida.");
				return "pretty:";
			}			
			usuarioSvcLocal.alterarSenha(userBean.getIdUsuario(), getNovaSenha1());
			incluirInfo("Senha alterada com sucesso");
			setUsuario(new Usuario());
			return "pretty:usermessage";
		} catch(Exception e){
			e.printStackTrace();
			incluirError(e.getMessage());
		}
		return "pretty:";
	}
	
	public String persistir() {
//		printScopedReferences(beanManager);
		if(isIncluir()) {
			try {			
				usuarioSvcLocal.saveOrUpdate(this.usuario);			
				incluirInfo("Usuário incluído com sucesso.");
				setUsuario(new Usuario());
			} catch(Exception e) {
				e.printStackTrace();
				incluirError("Erro ao incluir usuário.", e.getMessage());
			}
			return "pretty:";
		} else if(isAlterar()) {
			try {			
				usuarioSvcLocal.saveOrUpdate(this.usuario);			
				incluirInfo("Usuário alterado com sucesso.");
				setUsuario(new Usuario());
				return "pretty:userPesquisa";
			} catch(Exception e) {
				e.printStackTrace();
				incluirError("Erro ao alterar usuário.", e.getMessage());
			}
		}
		return "pretty:";
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
