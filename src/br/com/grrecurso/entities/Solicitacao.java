package br.com.grrecurso.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.grrecurso.dominio.DominioSituacaoSolicitacao;
import br.com.grrecurso.entities.usuario.Usuario;

@Entity
/*
@FilterDef (name = "porRoleId", parameters = @ParamDef (name = "roleId", type="integer"))
@Filters ({
    @Filter (name = "porRoleId", condition = "nome = :nome")
})
*/
@Table(name="solicitacao")
public class Solicitacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -950653597962165418L;
	
	@Id
	@Column(name="id_solicitacao")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idSolicitacao;
	@ManyToOne
	@JoinColumn(name="id_usuario", nullable=false)
	private Usuario usuario;
	@Column(name="chamado", length=50, nullable=false)
	private String chamado;
	@Column(name="descricao", length=400, nullable=false)
	private String descricao;
	@ManyToOne
	@JoinColumn(name="id_grupo_recurso")
	private GrupoRecurso grupoRecurso;
	@Column(name="situacao_solicitacao", nullable=false)
	@Enumerated(EnumType.ORDINAL)
	private DominioSituacaoSolicitacao situacao;
	
	public Solicitacao(){
		super();
	}

	public Long getIdSolicitacao() {
		return idSolicitacao;
	}

	public void setIdSolicitacao(Long idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getChamado() {
		return chamado;
	}

	public void setChamado(String chamado) {
		this.chamado = chamado;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public GrupoRecurso getGrupoRecurso() {
		return grupoRecurso;
	}

	public void setGrupoRecurso(GrupoRecurso grupoRecurso) {
		this.grupoRecurso = grupoRecurso;
	}

	public DominioSituacaoSolicitacao getSituacao() {
		return situacao;
	}

	public void setSituacao(DominioSituacaoSolicitacao situacao) {
		this.situacao = situacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chamado == null) ? 0 : chamado.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((grupoRecurso == null) ? 0 : grupoRecurso.hashCode());
		result = prime * result + ((idSolicitacao == null) ? 0 : idSolicitacao.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Solicitacao other = (Solicitacao) obj;
		if (chamado == null) {
			if (other.chamado != null)
				return false;
		} else if (!chamado.equals(other.chamado))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (grupoRecurso == null) {
			if (other.grupoRecurso != null)
				return false;
		} else if (!grupoRecurso.equals(other.grupoRecurso))
			return false;
		if (idSolicitacao == null) {
			if (other.idSolicitacao != null)
				return false;
		} else if (!idSolicitacao.equals(other.idSolicitacao))
			return false;
		if (situacao != other.situacao)
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
}
