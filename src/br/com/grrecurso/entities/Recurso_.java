package br.com.grrecurso.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-06-26T19:02:27.524-0400")
@StaticMetamodel(Recurso.class)
public class Recurso_ {
	public static volatile SingularAttribute<Recurso, Long> idRecurso;
	public static volatile SingularAttribute<Recurso, String> nomeInterno;
	public static volatile SingularAttribute<Recurso, String> descricao;
	public static volatile SingularAttribute<Recurso, Date> dataCadastro;
	public static volatile SingularAttribute<Recurso, GrupoRecurso> grupoRecurso;
}
