package br.com.grrecurso.dominio;

public enum DominioAtivoInativo {
	ATIVO(1, "Ativo"), INATIVO(2, "Inativo");
	
	Integer codigo;
	String desc;
	
	DominioAtivoInativo(Integer codigo, String desc){
		this.codigo = codigo;
		this.desc = desc;
	}	
	
	public DominioAtivoInativo valueOf(Integer codigo){
		for(DominioAtivoInativo value : DominioAtivoInativo.values()){
			if(value.getCodigo().equals(codigo))
				return value;
		}
		return null;
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
