package br.com.grrecurso.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

@Named
@SessionScoped
public class UserBean implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -112805847748847144L;
	public static final String USER_LOGGED = "userLogged";
	private Long idUsuario;
	private String nome;
	private String email;
	private Date dataLogin;
	
	public UserBean() {
		super();
	}
	
	public UserBean(Usuario usuario) {
		this.idUsuario = usuario.getIdUsuario();
		this.email = usuario.getEmail();
		this.dataLogin = usuario.getDataLogin();
	}
	
	public void setUsuario(Usuario usuario) throws IllegalArgumentException {
		if(usuario == null) {
			throw new IllegalArgumentException("O Parâmetro usuário não pode ser null.");
		}
		this.idUsuario = usuario.getIdUsuario();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.dataLogin = usuario.getDataLogin();
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataLogin() {
		return dataLogin;
	}

	public void setDataLogin(Date dataLogin) {
		this.dataLogin = dataLogin;
	}
	
	public String getKey() {
		return StringUtils.leftPad(getIdUsuario().toString(),10,'0') + "-" + getEmail();
	}

	public String getUserBeanInfo() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		return getNome() + " - logado " + sdf.format(getDataLogin());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBean other = (UserBean) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}
}
