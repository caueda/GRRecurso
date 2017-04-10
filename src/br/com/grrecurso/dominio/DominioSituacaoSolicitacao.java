package br.com.grrecurso.dominio;

import br.com.grrecurso.core.search.annotations.DominioId;

public enum DominioSituacaoSolicitacao {
	PENDENTE(1, "Pendente"), PERSISTIDO(2, "Persistido");
	
	Integer codigo;
	String desc;
	
	public static final String NOME = "DominioSituacaoSolicitacao";
	
	DominioSituacaoSolicitacao(Integer codigo, String desc){
		this.codigo = codigo;
		this.desc = desc;
	}

	@DominioId
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}	
	
	public static DominioSituacaoSolicitacao valueOf(Integer codigo){
		for(DominioSituacaoSolicitacao situacao : DominioSituacaoSolicitacao.values()){
			if(situacao.getCodigo().equals(codigo))
				return situacao;
		}
		return null;
	}
}
