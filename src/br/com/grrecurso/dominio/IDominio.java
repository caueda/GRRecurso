package br.com.grrecurso.dominio;

public interface IDominio<T extends Enum> {
	public T getDominio(String value);
}
