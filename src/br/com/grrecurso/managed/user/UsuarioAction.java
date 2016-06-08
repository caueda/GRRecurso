package br.com.grrecurso.managed.user;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.com.grrecurso.ejb.login.UsuarioBean;
import br.com.grrecurso.entities.Usuario;
import br.com.grrecurso.enumerator.DominioAtivoInativo;
import br.com.grrecurso.managed.AbstractManagedBean;

@Named
@RequestScoped

@URLMappings( mappings= {
		@URLMapping(id="user", pattern="/app/usuario", viewId="/application/user/usuario.jsf"),
		@URLMapping(id="userpassword", pattern="/app/usuario/password/#{userBean.idUsuario}", viewId="/application/user/usuarioPassword.jsf"),
		@URLMapping(id="usermessage", pattern="/app/message/usuario", viewId="/application/msg/mensagem.jsf")
		
})
public class UsuarioAction extends AbstractManagedBean implements Serializable {	
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
	private UsuarioBean usuarioBean;
	
	public String persist() {
		usuarioBean.saveOrUpdate(usuario);
		return "";
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
	
	public void exibirEdicao(){
		if(this.idUsuario != null){
			setUsuario(usuarioBean.loadById(idUsuario));
		}
	}
	
	public String alterarSenha(){
		try {
			usuario = usuarioBean.loadById(getIdUsuario());
			if(!getSenhaAtual().equals(getUsuario().getSenha())){
				incluirError("A senha atual não confere.");
				return "";
			}
			if(!getNovaSenha1().equals(getNovaSenha2())){
				incluirError("Confirmação da nova senha inválida.");
				return "";
			}			
			usuarioBean.alterarSenha(getIdUsuario(), getNovaSenha1());
			incluirInfo("Senha alterada com sucesso");
			setUsuario(new Usuario());
			return "pretty:usermessage";
		} catch(Exception e){
			e.printStackTrace();
			incluirError(e.getMessage());
		}
		return "";
	}
	
	public void incluir() {
		try {		
			usuarioBean.saveOrUpdate(this.usuario);			
			incluirInfo("Usuário incluído com sucesso.");
			setUsuario(new Usuario());
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
