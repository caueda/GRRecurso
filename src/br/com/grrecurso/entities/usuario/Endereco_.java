package br.com.grrecurso.entities.usuario;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-20T14:19:27.783-0400")
@StaticMetamodel(Endereco.class)
public class Endereco_ {
	public static volatile SingularAttribute<Endereco, Long> idEndereco;
	public static volatile SingularAttribute<Endereco, String> rua;
	public static volatile SingularAttribute<Endereco, String> cidade;
	public static volatile SingularAttribute<Endereco, String> estado;
	public static volatile SingularAttribute<Endereco, String> numero;
	public static volatile SingularAttribute<Endereco, String> bairro;
	public static volatile SingularAttribute<Endereco, String> cep;
	public static volatile SingularAttribute<Endereco, Usuario> usuario;
}
