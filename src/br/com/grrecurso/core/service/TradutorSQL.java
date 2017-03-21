package br.com.grrecurso.core.service;

import java.io.Serializable;

import br.com.grrecurso.core.search.FieldTextOperations;

public class TradutorSQL implements Serializable, Traduzivel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9119927655034376710L;
	
	public String traduzir(String operacao){
		String traducao=null;
		if(operacao.contains("TEXT")){
			FieldTextOperations e = Enum.valueOf(FieldTextOperations.class, operacao);
			switch(e){
				case TEXT_CONTAINS:
					traducao = " LIKE LOWER('%:value%')";
					break;
				case TEXT_ENDS_WITH:
					traducao = " LIKE LOWER('%:value')";
					break;
				case TEXT_START_WITH:
					traducao = " LIKE LOWER(':value%')";
					break;
				case TEXT_IGUAL:
					traducao = " = LOWER(':value')";
					break;
			}
		}
		return traducao;
	}
}
