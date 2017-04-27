package br.com.grrecurso.entities.usuario;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import br.com.grrecurso.dominio.DominioAtivoInativo;

@Entity
@Table(name="perfil_usuario")
@Audited
@NamedQueries({
	@NamedQuery(name="PerfilUsuario.listAll", query="select vo from PerfilUsuario vo order by vo.nome "),
	@NamedQuery(name="PerfilUsuario.loadById",query="select vo from PerfilUsuario vo where vo.idPerfilUsuario = :idPerfilUsuario")
})
public class PerfilUsuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3813746187898018816L;
	
	private Long idPerfilUsuario;
	private String nome;
	private DominioAtivoInativo status;
	private Date dataCadastro;
	private Set<Role> roles;
	
	public PerfilUsuario() {
		super();
	}

	@Id
	@Column(name="id_perfil_usuario")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getIdPerfilUsuario() {
		return idPerfilUsuario;
	}

	public void setIdPerfilUsuario(Long idPerfilUsuario) {
		this.idPerfilUsuario = idPerfilUsuario;
	}

	@Column(name="nome", length=200, nullable=false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name="status")
	@Enumerated(EnumType.ORDINAL)
	public DominioAtivoInativo getStatus() {
		return status;
	}

	public void setStatus(DominioAtivoInativo status) {
		this.status = status;
	}

	@Column(name="data_cadastro")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="perfil_usuario_role", joinColumns={@JoinColumn(name="id_perfil_usuario")},
	inverseJoinColumns={@JoinColumn(name="id_role")})
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((idPerfilUsuario == null) ? 0 : idPerfilUsuario.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		PerfilUsuario other = (PerfilUsuario) obj;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (idPerfilUsuario == null) {
			if (other.idPerfilUsuario != null)
				return false;
		} else if (!idPerfilUsuario.equals(other.idPerfilUsuario))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
}
