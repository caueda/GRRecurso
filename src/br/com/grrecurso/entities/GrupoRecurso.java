package br.com.grrecurso.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="grupo_recurso")
public class GrupoRecurso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3566053615692971084L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_grupo_recurso")
	private Long idGrupoRecurso;
	@Column(name="nome", length=200, nullable=false, unique=true)
	private String nome;
	@Column(name="descricao", length=400, nullable=false)
	private String descricao;
	@Column(name="data_cadastro", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="grupoRecurso")
	private List<Recurso> recursos;
}
