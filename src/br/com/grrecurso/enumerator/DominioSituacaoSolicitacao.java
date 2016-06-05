package br.com.grrecurso.enumerator;

public enum DominioSituacaoSolicitacao {
	PENDENTE(1, "Pendente"), PERSISTIDO(2, "Persistido");
	
	Integer codigo;
	String desc;
	
	DominioSituacaoSolicitacao(Integer codigo, String desc){
		this.codigo = codigo;
		this.desc = desc;
	}

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
}
