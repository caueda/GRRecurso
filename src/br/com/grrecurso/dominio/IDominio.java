package br.com.grrecurso.dominio;

@SuppressWarnings("rawtypes")
public interface IDominio<T extends Enum> {
	public T getDominio(String value);
}
