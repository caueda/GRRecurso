package br.com.grrecurso.dominio;

public enum DominioSexo implements IDominio<DominioSexo> {
	FEMININO('F', 1, "Feminino"),
	MASCULINO('M', 2, "Masculino");
	
	int codigo;
	String desc;
	Character charCodigo;
	
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

	public DominioSexo getDominio(String codigo){
		Character c = codigo.charAt(0);
		for(DominioSexo value : DominioSexo.values()){
			if(value.getCharCodigo().equals(c)){
				return value;
			}
		}
		return null;
	}
}
