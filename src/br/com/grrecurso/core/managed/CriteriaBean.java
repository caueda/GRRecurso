package br.com.grrecurso.core.managed;

import java.io.Serializable;

public class CriteriaBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4601795631454515983L;
	private String nomeCampo;
	private String singleValue;
	private String rangeValueInicio;
	private String rangeValueFim;
	private String operacao;
	
	public CriteriaBean(){
		super();
	}

	public CriteriaBean(String nomeCampo, String rangeValueInicio, String rangeValueFim, String operacao) {
		super();
		this.nomeCampo = nomeCampo;
		this.rangeValueInicio = rangeValueInicio;
		this.rangeValueFim = rangeValueFim;
		this.operacao = operacao;
	}

	public CriteriaBean(String nomeCampo, String singleValue, String operacao) {
		super();
		this.nomeCampo = nomeCampo;
		this.singleValue = singleValue;
		this.operacao = operacao;
	}


	public String getNomeCampo() {
		return nomeCampo;
	}

	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}

	public String getSingleValue() {
		return singleValue;
	}

	public void setSingleValue(String singleValue) {
		this.singleValue = singleValue;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getRangeValueInicio() {
		return rangeValueInicio;
	}

	public void setRangeValueInicio(String rangeValueInicio) {
		this.rangeValueInicio = rangeValueInicio;
	}

	public String getRangeValueFim() {
		return rangeValueFim;
	}

	public void setRangeValueFim(String rangeValueFim) {
		this.rangeValueFim = rangeValueFim;
	}
}
