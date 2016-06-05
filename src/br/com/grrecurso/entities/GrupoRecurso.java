package br.com.grrecurso.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="grupo_recurso")
public class GrupoRecurso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3566053615692971084L;
	private Long idGrupoRecurso;
	private String nome;
	private String descricao;
	private Date dataCadastro;
	private List<Recurso> recursos;
	
	public GrupoRecurso(){
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_grupo_recurso")
	public Long getIdGrupoRecurso() {
		return idGrupoRecurso;
	}

	public void setIdGrupoRecurso(Long idGrupoRecurso) {
		this.idGrupoRecurso = idGrupoRecurso;
	}

	@Column(name="nome", length=200, nullable=false, unique=true)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name="descricao", length=400, nullable=false)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name="data_cadastro", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@OneToMany(cascade=CascadeType.ALL, mappedBy="grupoRecurso")
	public List<Recurso> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((idGrupoRecurso == null) ? 0 : idGrupoRecurso.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((recursos == null) ? 0 : recursos.hashCode());
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
		GrupoRecurso other = (GrupoRecurso) obj;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (idGrupoRecurso == null) {
			if (other.idGrupoRecurso != null)
				return false;
		} else if (!idGrupoRecurso.equals(other.idGrupoRecurso))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (recursos == null) {
			if (other.recursos != null)
				return false;
		} else if (!recursos.equals(other.recursos))
			return false;
		return true;
	}
}
