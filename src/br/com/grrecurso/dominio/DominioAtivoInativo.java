package br.com.grrecurso.dominio;

import br.com.grrecurso.core.search.annotations.DominioId;

public enum DominioAtivoInativo {
	ATIVO(1, "Ativo"), INATIVO(0, "Inativo");
	
	Integer codigo;
	String desc;
	
	public static final String NOME = "DominioAtivoInativo";
	
	DominioAtivoInativo(Integer codigo, String desc){
		this.codigo = codigo;
		this.desc = desc;
	}	
	
	public static DominioAtivoInativo valueOf(Integer codigo){
		for(DominioAtivoInativo value : DominioAtivoInativo.values()){
			if(value.getCodigo().equals(codigo))
				return value;
		}
		return null;
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
}
