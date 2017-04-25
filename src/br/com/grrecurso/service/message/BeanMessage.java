package br.com.grrecurso.service.message;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BeanMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3619922701419476515L;
	
	@XmlElement
	private Integer codigo;
	@XmlElement
	private String titulo;
	@XmlElement
	private String mensagem;
	
	public BeanMessage(Integer codigo, String titulo, String mensagem) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.mensagem = mensagem;
	}

	public BeanMessage() {
		super();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
