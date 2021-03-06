package br.com.grrecurso.entities.usuario;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.Email;

import br.com.grrecurso.dominio.DominioAtivoInativo;
import br.com.grrecurso.dominio.DominioSexo;

@Entity
@Audited
@Table(name="usuario")
@NamedQueries({
	@NamedQuery(name="Usuario.findByEmailSenha", query="select u from Usuario u where u.email = :email and u.senha = :senha"),
	@NamedQuery(name="Usuario.loadById", query="select u from Usuario u where u.idUsuario = :idUsuario"),
	@NamedQuery(name="Usuario.listAll", query="select u from Usuario u"),
	@NamedQuery(name="Usuario.loadByEmail", query="select u from Usuario u where u.email = :email")
})
public class Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 340681521727137527L;
	
	private Long idUsuario;
	private String nome;
	private String email;
	private String senha;
	private Date dataLogin;
	private DominioAtivoInativo status;
	private Set<PerfilUsuario> perfis;
	private Set<Role> roles;
	private DominioSexo sexo;
	private boolean edicao;
	private Set<Endereco> enderecos;
	private Set<Modulo> modulos;
	
	public Usuario(){
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	@Column(name="nome", length=100, nullable=false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name="email", length=120, nullable=false, unique=true)
	@Email(message="E-mail inválido.")	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="sexo")
	@Type(type = DominioSexo.NOME)
	public DominioSexo getSexo() {
		return sexo;
	}

	public void setSexo(DominioSexo sexo) {
		this.sexo = sexo;
	}

	@NotAudited
	@Column(name="senha", length=20)
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Column(name="data_login")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDataLogin() {
		return dataLogin;
	}

	public void setDataLogin(Date dataLogin) {
		this.dataLogin = dataLogin;
	}
	@Column(name="status")
	@Type(type = DominioAtivoInativo.NOME)
	public DominioAtivoInativo getStatus() {
		return status;
	}

	public void setStatus(DominioAtivoInativo status) {
		this.status = status;
	}

	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="usuario_perfil_usuario", 
		joinColumns= {@JoinColumn(name="id_usuario")},
		inverseJoinColumns= {@JoinColumn(name="id_perfil_usuario")}
	)
	public Set<PerfilUsuario> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<PerfilUsuario> perfis) {
		this.perfis = perfis;
	}
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="usuario_modulos", joinColumns={
			@JoinColumn(name="id_usuario")},
			inverseJoinColumns={@JoinColumn(name="id_modulo")}
	)
	public Set<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(Set<Modulo> modulos) {
		this.modulos = modulos;
	}

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="usuario_role", joinColumns= {@JoinColumn(name="id_usuario")},
			   inverseJoinColumns= {@JoinColumn(name="id_role")}
	)
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean isEdicao() {
		return edicao;
	}

	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
	}
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="usuario")	
	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		Usuario other = (Usuario) obj;
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
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}
}
