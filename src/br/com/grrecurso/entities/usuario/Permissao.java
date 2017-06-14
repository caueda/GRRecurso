package br.com.grrecurso.entities.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import br.com.grrecurso.core.persistence.BaseEntity;
import br.com.grrecurso.core.search.FieldTextOperations;
import br.com.grrecurso.core.search.FieldTextPresentation;
import br.com.grrecurso.core.search.annotations.ConfiguracaoPesquisa;
import br.com.grrecurso.core.search.annotations.FieldTextFilter;
import br.com.grrecurso.core.search.annotations.ResultGrid;
import br.com.grrecurso.core.search.annotations.TituloPesquisa;
import br.com.grrecurso.dominio.DominioTipoPermissao;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name="permissao")
@NamedQueries({@NamedQuery(name="Permissao.listAll", query="select r from Permissao r"),
			   @NamedQuery(name="Permissao.loadById", query="select r from Permissao r where r.id = :idPermissao")
	})
@Data
@EqualsAndHashCode(callSuper=false)
@TituloPesquisa
@ConfiguracaoPesquisa(rowsPerPageTemplate="5,10")
public class Permissao extends BaseEntity {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8669464839547735734L;
	
	public static final String ADMIN = "admin";
	
	@Id
	@Column(name="id_permissao")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ResultGrid(label="ID", ordem=1, align="center")
	private Long id;
	@ResultGrid(label="Nome", ordem=2, align="left")
	@FieldTextFilter(label="Nome", apresentacao=FieldTextPresentation.TEXT, operacao= {FieldTextOperations.TEXT_CONTAINS, FieldTextOperations.TEXT_ENDS_WITH, FieldTextOperations.TEXT_IGUAL})
	@Column(name="nome", length=200, nullable=false, unique=true)
	private String nome;
	@ResultGrid(label="Descrição", ordem=3, align="left")
	@FieldTextFilter(label="Descrição", apresentacao=FieldTextPresentation.TEXT, operacao=FieldTextOperations.TEXT_CONTAINS)
	@Column(name="descricao", length=400)	
	private String descricao;
	
	@ResultGrid(label="Ação", ordem=4, align="left")
	@FieldTextFilter(label="Ação", apresentacao=FieldTextPresentation.TEXT, operacao=FieldTextOperations.TEXT_CONTAINS)
	@Column(name="action", length=4000, unique=true)
	private String action;
	
	@Column(name="tipo_permissao", nullable=false)
	@Type(type=DominioTipoPermissao.NOME)
	private DominioTipoPermissao tipoPermissao;
}
