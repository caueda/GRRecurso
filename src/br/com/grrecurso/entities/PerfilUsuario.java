package br.com.grrecurso.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.grrecurso.enumerator.DominioAtivoInativo;

@Entity
@Table(name="perfil_usuario")
public class PerfilUsuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3813746187898018816L;
	
	private Long idPerfilUsuario;
	private String nome;
	private DominioAtivoInativo status;
	private Date dataCadastro;
	
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
}
