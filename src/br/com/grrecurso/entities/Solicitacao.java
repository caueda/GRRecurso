package br.com.grrecurso.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

import br.com.grrecurso.dominio.DominioSituacaoSolicitacao;
import br.com.grrecurso.entities.usuario.Modulo;
import br.com.grrecurso.entities.usuario.Usuario;

@Entity
@FilterDef (name = "porModulo", parameters = @ParamDef (name = "idModulos", type="long"))
@Filters ({
    @Filter (name = "porModulo", condition = "id_modulo in (:idModulos)")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_modulo")
	private Modulo modulo;
}
