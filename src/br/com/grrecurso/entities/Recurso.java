package br.com.grrecurso.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

@Entity
@FilterDef (name = "porSistema", parameters = @ParamDef (name = "sistema", type="string"))
@Filters ({
    @Filter (name = "porSistema", condition = "sistema = :sistema")
})
@Table(name="recurso")
public class Recurso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6068246493928239998L;
	private Long idRecurso;
	private String nomeInterno;
	private String descricao;
	private Date dataCadastro;
	private GrupoRecurso grupoRecurso;
	
	public Recurso(){
		super();
	}
	
	@Id
	@Column(name="id_recurso")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(Long idRecurso) {
		this.idRecurso = idRecurso;
	}

	@Column(name="nome_interno", length=200, nullable=false, unique=true)
	public String getNomeInterno() {
		return nomeInterno;
	}

	public void setNomeInterno(String nomeInterno) {
		this.nomeInterno = nomeInterno;
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

	
	@ManyToOne
	@JoinColumn(name="id_grupo_recurso")
	public GrupoRecurso getGrupoRecurso() {
		return grupoRecurso;
	}

	public void setGrupoRecurso(GrupoRecurso grupoRecurso) {
		this.grupoRecurso = grupoRecurso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((idRecurso == null) ? 0 : idRecurso.hashCode());
		result = prime * result + ((nomeInterno == null) ? 0 : nomeInterno.hashCode());
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
		Recurso other = (Recurso) obj;
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
		if (idRecurso == null) {
			if (other.idRecurso != null)
				return false;
		} else if (!idRecurso.equals(other.idRecurso))
			return false;
		if (nomeInterno == null) {
			if (other.nomeInterno != null)
				return false;
		} else if (!nomeInterno.equals(other.nomeInterno))
			return false;
		return true;
	}
}
