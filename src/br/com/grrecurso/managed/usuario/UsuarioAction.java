package br.com.grrecurso.managed.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DualListModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.com.grrecurso.core.managed.AbstractManagedBean;
import br.com.grrecurso.core.util.LocalUtil;
import br.com.grrecurso.dominio.DominioAtivoInativo;
import br.com.grrecurso.dominio.DominioSexo;
import br.com.grrecurso.entities.usuario.Modulo;
import br.com.grrecurso.entities.usuario.Usuario;
import br.com.grrecurso.service.login.UsuarioSvcLocal;
import br.com.grrecurso.service.modulo.ModuloService;

@Named
@URLBeanName(value="usuarioAction")
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
	
	//Caso em que o Adm irá alterar a senha de outro usuário
	private String senhaUpdateAdm;
	
	private Long idUsuario;
	
	private DualListModel<Modulo> modulos;
	
	@EJB
	private UsuarioSvcLocal usuarioSvcLocal;
	
	@EJB
	private ModuloService moduloService;
	
	private boolean isUsuarioLogadoAdm = false;
	
	@PostConstruct
	public void init() {
		logger.info("[UsuarioAction.init] " + this.toString());
		List<Modulo> modulosSource = moduloService.listaModulos();
		List<Modulo> modulosTarget = new ArrayList<Modulo>();
		
		setUsuarioLogadoAdm(hasRole("Admin"));
		
		if(this.usuario != null && this.usuario.getModulos() != null){
			modulosTarget.addAll(usuario.getModulos());
		}
		modulos = new DualListModel<Modulo>(modulosSource, modulosTarget);
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
	
	public DualListModel<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(DualListModel<Modulo> modulos) {
		this.modulos = modulos;
	}

	@URLAction(mappingId="editUsuario")
	public void exibirEdicao(){
		
		if(!isIncluir() && getIdUsuario() != null) {
			setTipoOperacao(ALTERAR);
			setUsuario(usuarioSvcLocal.loadById(getIdUsuario()));
			
			List<Modulo> modulosSource = moduloService.listaModulosExcept(getIdUsuario());
			List<Modulo> modulosTarget = new ArrayList<Modulo>();
			
			if(this.usuario != null && this.usuario.getModulos() != null){
				modulosTarget.addAll(usuario.getModulos());
			}
			
			modulos = new DualListModel<Modulo>(modulosSource, modulosTarget);
		}
	}
	
	public void exibirAlterarSenha() {
		if(userBean != null && userBean.getIdUsuario() != null) {			
			setUsuario(usuarioSvcLocal.loadById(userBean.getIdUsuario()));		
		}
	}

	public String encrypt(String senha) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashed = passwordEncoder.encode(senha);
		return hashed;
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
			usuarioSvcLocal.alterarSenha(userBean.getIdUsuario(), encrypt(getNovaSenha1()));
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
				String hashed = encrypt(this.usuario.getSenha());
				this.usuario.setSenha(hashed);
				
				List<Modulo> targetList = getModulos().getTarget();
				usuario.getModulos().clear();
				usuario.getModulos().addAll(targetList);
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
				List<Modulo> targetList = getModulos().getTarget();
				usuario.getModulos().clear();
				usuario.getModulos().addAll(targetList);
				if(isUsuarioLogadoAdm && StringUtils.isNotBlank(getSenhaUpdateAdm())){
					usuario.setSenha(encrypt(getSenhaUpdateAdm()));
				}
				usuarioSvcLocal.saveOrUpdate(this.usuario);			
				
				if(getIdUsuario().equals(LocalUtil.getPrincipal().getIdUsuario())) {
					if(LocalUtil.getPrincipal().getModuleIds() != null){
						LocalUtil.getPrincipal().getModuleIds().clear();
					} else {
						LocalUtil.getPrincipal().setModuleIds(new ArrayList<Long>());
					}
					for(Modulo m : usuario.getModulos()) {
						LocalUtil.getPrincipal().getModuleIds().add(m.getId());
					}
				}
				
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

	public boolean isUsuarioLogadoAdm() {
		return isUsuarioLogadoAdm;
	}

	public void setUsuarioLogadoAdm(boolean isUsuarioLogadoAdm) {
		this.isUsuarioLogadoAdm = isUsuarioLogadoAdm;
	}

	public String getSenhaUpdateAdm() {
		return senhaUpdateAdm;
	}

	public void setSenhaUpdateAdm(String senhaUpdateAdm) {
		this.senhaUpdateAdm = senhaUpdateAdm;
	}
}
