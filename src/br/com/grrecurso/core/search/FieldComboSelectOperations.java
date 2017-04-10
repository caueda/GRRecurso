package br.com.grrecurso.core.search;

public enum FieldComboSelectOperations {
	SELECT_IGUAL(1, "Igual");
	
	String desc;
	Integer id;
	
	FieldComboSelectOperations(Integer id, String desc){
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
