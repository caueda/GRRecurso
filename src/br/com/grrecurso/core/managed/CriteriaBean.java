package br.com.grrecurso.core.managed;

import java.io.Serializable;

public class CriteriaBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4601795631454515983L;
	private String nomeCampo;
	private Object singleValue;
	private Object rangeValueInicio;
	private Object rangeValueFim;
	private String operacao;
	
	public CriteriaBean(){
		super();
	}

	public CriteriaBean(String nomeCampo, Object rangeValueInicio, Object rangeValueFim, String operacao) {
		super();
		this.nomeCampo = nomeCampo;
		this.rangeValueInicio = rangeValueInicio;
		this.rangeValueFim = rangeValueFim;
		this.operacao = operacao;
	}

	public CriteriaBean(String nomeCampo, Object singleValue, String operacao) {
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

	public Object getSingleValue() {
		return singleValue;
	}

	public void setSingleValue(Object singleValue) {
		this.singleValue = singleValue;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public Object getRangeValueInicio() {
		return rangeValueInicio;
	}

	public void setRangeValueInicio(Object rangeValueInicio) {
		this.rangeValueInicio = rangeValueInicio;
	}

	public Object getRangeValueFim() {
		return rangeValueFim;
	}

	public void setRangeValueFim(Object rangeValueFim) {
		this.rangeValueFim = rangeValueFim;
	}
}
