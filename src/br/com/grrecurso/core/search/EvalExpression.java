package br.com.grrecurso.core.search;

public class EvalExpression {
	public static final String SUFIXO_OPERACAO = "_OPERACAO";
	
	public static String getSufixoOperacao(String value){
		return value.replace(".", "_") + SUFIXO_OPERACAO;
	}
	
	public static String getIdCampo(String value){
		return value.replace(".","_");
	}
}
