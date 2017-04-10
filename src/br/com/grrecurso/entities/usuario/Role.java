package br.com.grrecurso.entities.usuario;

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
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import br.com.grrecurso.core.persistence.BaseEntity;
import br.com.grrecurso.core.search.FieldTextOperations;
import br.com.grrecurso.core.search.FieldTextPresentation;
import br.com.grrecurso.core.search.annotations.ConfiguracaoPesquisa;
import br.com.grrecurso.core.search.annotations.FieldComboSelectFilter;
import br.com.grrecurso.core.search.annotations.FieldTextFilter;
import br.com.grrecurso.core.search.annotations.ResultGrid;
import br.com.grrecurso.core.search.annotations.TituloPesquisa;
import br.com.grrecurso.dominio.DominioAtivoInativo;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Audited
@Table(name="role")
@NamedQueries({@NamedQuery(name="Role.listAll", query="select r from Role r"),
			   @NamedQuery(name="Role.loadById", query="select r from Role r where r.id = :idRole")
	})
@TituloPesquisa
@ConfiguracaoPesquisa(rowsPerPageTemplate="5,10")
@Data
@EqualsAndHashCode(callSuper=false)
public class Role extends BaseEntity {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8669464839547735734L;
	
	public static final String ADMIN = "admin";
	
	@Id
	@Column(name="id_role")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ResultGrid(label="ID", ordem=1, align="center")
	private Long id;
	@ResultGrid(label="Nome", ordem=2, align="left")
	@FieldTextFilter(label="Nome", apresentacao=FieldTextPresentation.TEXT, operacao= {FieldTextOperations.TEXT_CONTAINS, FieldTextOperations.TEXT_ENDS_WITH, FieldTextOperations.TEXT_IGUAL})
	@Column(name="nome", length=200, nullable=false)
	private String nome;
	@ResultGrid(label="Descrição", ordem=3, align="left")
	@FieldTextFilter(label="Descrição", apresentacao=FieldTextPresentation.TEXT, operacao=FieldTextOperations.TEXT_CONTAINS)
	@Column(name="descricao", length=400)	
	private String descricao;	
	
	@ResultGrid(label="Status", ordem=4, align="center")
	@FieldComboSelectFilter(label="Status", campo="status", classe=DominioAtivoInativo.class)
	@Column(name="status")
	@Type(type = DominioAtivoInativo.NOME)
	private DominioAtivoInativo status;
	
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="role_permissao", 
		joinColumns= {@JoinColumn(name="id_role")},
		inverseJoinColumns= {@JoinColumn(name="id_permissao")}
	)
	private Set<Permissao> permissoes;

}
