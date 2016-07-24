package br.com.grrecurso.entities.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import br.com.grrecurso.dominio.DominioAtivoInativo;
import br.com.grrecurso.entities.BaseEntity;

@Entity
@Audited
@Table(name="modulo")
public class Modulo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4598739921437441010L;
	
	@Id
	@Column(name="id_modulo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idModulo;
	@Column(name="nome", length=200, nullable=false)
	private String nome;
	@Column(name="status")
	@Type(type=DominioAtivoInativo.NOME)
	private DominioAtivoInativo status;
	
	public Modulo(){
		super();
	}

	public Long getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(Long idModulo) {
		this.idModulo = idModulo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public DominioAtivoInativo getStatus() {
		return status;
	}

	public void setStatus(DominioAtivoInativo status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idModulo == null) ? 0 : idModulo.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Modulo other = (Modulo) obj;
		if (idModulo == null) {
			if (other.idModulo != null)
				return false;
		} else if (!idModulo.equals(other.idModulo))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public Long getId() {
		return getIdModulo();
	}
	
	public void setId(Long id){
		setIdModulo(id);
	}
}
