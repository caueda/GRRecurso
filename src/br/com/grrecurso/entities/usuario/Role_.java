package br.com.grrecurso.entities.usuario;

import br.com.grrecurso.dominio.DominioAtivoInativo;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-04-10T05:19:29.173-0400")
@StaticMetamodel(Role.class)
public class Role_ {
	public static volatile SingularAttribute<Role, Long> id;
	public static volatile SingularAttribute<Role, String> nome;
	public static volatile SingularAttribute<Role, String> descricao;
	public static volatile SetAttribute<Role, Permissao> permissoes;
	public static volatile SingularAttribute<Role, DominioAtivoInativo> status;
}
