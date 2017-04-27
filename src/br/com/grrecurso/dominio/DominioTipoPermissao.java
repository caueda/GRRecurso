package br.com.grrecurso.dominio;

import br.com.grrecurso.core.search.annotations.DominioId;

public enum DominioTipoPermissao {
	ITEM_MENU(1, "Item de Menu"),
	ACAO(2, "Ação");
	
	int codigo;
	String desc;
	
	public static final String NOME = "DominioTipoPermissao";
	
	DominioTipoPermissao(int codigo, String desc){
		this.codigo = codigo;		
		this.desc = desc;
	}

	@DominioId
	public int getCodigo() {
		return codigo;
	}

	public String getDesc() {
		return desc;
	}

	public static DominioTipoPermissao valueOf(int codigo){
		for(DominioTipoPermissao value : DominioTipoPermissao.values()){
			if(value.getCodigo()==codigo){
				return value;
			}
		}
		return null;
	}
}
