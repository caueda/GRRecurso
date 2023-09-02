package br.com.grrecurso.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="categoria")
public class Categoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9170820345325014563L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_categoria")
	private Long idCategoria;
	@Column(name="cd_categoria", nullable=false)
	private Integer cdCategoria;
	@Column(name="nome", length=50, nullable=false)
	private String nome;
}
