package br.com.grrecurso.core.search;

public enum FieldTextOperations {
	TEXT_CONTAINS(1, "Contém"), TEXT_START_WITH (2, "Começa com"), TEXT_ENDS_WITH(3, "Termina com"), TEXT_IGUAL(4, "Igual a");
	
	String desc;
	Integer id;
	
	FieldTextOperations(Integer id, String desc){
		this.id = id;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public Integer getId() {
		return id;
	}
}
