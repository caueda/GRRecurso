package br.com.grrecurso.core.search;

public enum FieldTextOperations {
	CONTAINS(1, "Contém"), START_WITH (2, "Começa com"), ENDS_WITH(3, "Termina com");
	
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
