package br.com.grrecurso.entities.usuario;

import br.com.grrecurso.dominio.DominioAtivoInativo;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-24T11:25:44.557-0400")
@StaticMetamodel(Modulo.class)
public class Modulo_ {
	public static volatile SingularAttribute<Modulo, Long> idModulo;
	public static volatile SingularAttribute<Modulo, String> nome;
	public static volatile SingularAttribute<Modulo, DominioAtivoInativo> status;
}
