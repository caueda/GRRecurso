package br.com.grrecurso.entities.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import br.com.grrecurso.core.persistence.BaseEntity;
import br.com.grrecurso.core.search.FieldTextOperations;
import br.com.grrecurso.core.search.FieldTextPresentation;
import br.com.grrecurso.core.search.annotations.ConfiguracaoPesquisa;
import br.com.grrecurso.core.search.annotations.FieldTextFilter;
import br.com.grrecurso.core.search.annotations.ResultGrid;
import br.com.grrecurso.core.search.annotations.TituloPesquisa;


@Entity
@Audited
@Table(name="role")
@NamedQueries({@NamedQuery(name="Role.listAll", query="select r from Role r"),
			   @NamedQuery(name="Role.loadById", query="select r from Role r where r.id = :idRole")
	})
@TituloPesquisa
@ConfiguracaoPesquisa(rowsPerPageTemplate="5,10")
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
	@FieldTextFilter(label="Nome", apresentacao=FieldTextPresentation.TEXT, operacao=FieldTextOperations.CONTAINS)
	@Column(name="nome", length=200, nullable=false)
	private String nome;
	@ResultGrid(label="Descrição", ordem=3, align="left")
	@FieldTextFilter(label="Descrição", apresentacao=FieldTextPresentation.TEXT, operacao=FieldTextOperations.CONTAINS)
	@Column(name="descricao", length=400)	
	private String descricao;	
	
	public Role() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Role other = (Role) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
