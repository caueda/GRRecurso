package br.com.grrecurso.core.search;

public class EvalExpression {
	public static final String SUFIXO_OPERACAO = "_OPERACAO";
	
	public static String getSufixoOperacao(String value){
		return value + SUFIXO_OPERACAO;
	}
}
