package br.com.grrecurso.core.service;

import java.io.Serializable;

import br.com.grrecurso.core.search.FieldComboSelectOperations;
import br.com.grrecurso.core.search.FieldTextOperations;

public class TradutorSQL implements Serializable, Traduzivel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9119927655034376710L;
	
	public String traduzir(String operacao){
		String traducao=null;
		if(operacao.startsWith("TEXT")){
			FieldTextOperations e = Enum.valueOf(FieldTextOperations.class, operacao);
			traducao = e.toString();
		} else if(operacao.startsWith("SELECT")){
			FieldComboSelectOperations e = Enum.valueOf(FieldComboSelectOperations.class, operacao);
			traducao = e.toString();
		}
		return traducao;
	}
}
