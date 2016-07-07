package br.com.grrecurso.dominio;

public enum DominioSexo {
	FEMININO('F', 1, "Feminino"),
	MASCULINO('M', 2, "Masculino");
	
	int codigo;
	String desc;
	Character charCodigo;
	
	public static final String NOME = "DominioSexo";
	public static final String METHOD = "getCharCodigo";
	
	DominioSexo(Character charCodigo, int codigo, String desc){
		this.codigo = codigo;
		this.charCodigo = charCodigo;
		this.desc = desc;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDesc() {
		return desc;
	}
	
	public Character getCharCodigo() {
		return charCodigo;
	}

	public static DominioSexo valueOf(Character codigo){
		for(DominioSexo value : DominioSexo.values()){
			if(value.getCharCodigo().equals(codigo)){
				return value;
			}
		}
		return null;
	}
}
