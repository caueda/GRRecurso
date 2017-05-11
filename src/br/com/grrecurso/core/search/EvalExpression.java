package br.com.grrecurso.core.search;

public class EvalExpression {
	public static final String SUFIXO_OPERACAO = "_OPERACAO";
	
	public static String getSufixoOperacao(String value){
		return getIdCampo(value) + SUFIXO_OPERACAO;
	}
	
	/**
	 * Substitui o . (ponto) pelo _ (underscore) nos casos aonde o campo informado para a pesquisa é do tipo:
	 * entity1.entity2.nome
	 * @param value
	 * @return
	 */
	public static String getIdCampo(String value){
		return value.replace(".","_");
	}
}
