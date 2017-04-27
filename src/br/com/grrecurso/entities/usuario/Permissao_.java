package br.com.grrecurso.entities.usuario;

import br.com.grrecurso.dominio.DominioTipoPermissao;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-04-27T13:53:54.182-0400")
@StaticMetamodel(Permissao.class)
public class Permissao_ {
	public static volatile SingularAttribute<Permissao, Long> id;
	public static volatile SingularAttribute<Permissao, String> nome;
	public static volatile SingularAttribute<Permissao, String> descricao;
	public static volatile SingularAttribute<Permissao, String> action;
	public static volatile SingularAttribute<Permissao, DominioTipoPermissao> tipoPermissao;
}
