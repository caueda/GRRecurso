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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

@Entity
@FilterDef (name = "porSistema", parameters = @ParamDef (name = "sistema", type="string"))
@Filters ({
    @Filter (name = "porSistema", condition = "sistema = :sistema")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="recurso")
public class Recurso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6068246493928239998L;
	@Id
	@Column(name="id_recurso")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idRecurso;
	@Column(name="nome_interno", length=200, nullable=false, unique=true)
	private String nomeInterno;
	@Column(name="descricao", length=400, nullable=false)
	private String descricao;
	@Column(name="data_cadastro", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	@ManyToOne
	@JoinColumn(name="id_grupo_recurso")
	private GrupoRecurso grupoRecurso;
}
