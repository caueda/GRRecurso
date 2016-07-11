package br.com.grrecurso.dominio;

public enum DominioTipoEndereco {
	CASA(1, "Casa"), 
	CORRESPONDENCIA(2, "Correspondência"),
	TRABALHO(2, "Trabalho");
	
	Integer codigo;
	String desc;
	
	public static final String NOME = "DominioTipoEndereco";
	public static final String METHOD = "getCodigo";
	
	DominioTipoEndereco(Integer codigo, String desc){
		this.codigo = codigo;
		this.desc = desc;
	}	
	
	public static DominioTipoEndereco valueOf(Integer codigo){
		for(DominioTipoEndereco value : DominioTipoEndereco.values()){
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
