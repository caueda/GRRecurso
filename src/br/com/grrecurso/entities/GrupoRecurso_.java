package br.com.grrecurso.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-06-26T19:02:27.518-0400")
@StaticMetamodel(GrupoRecurso.class)
public class GrupoRecurso_ {
	public static volatile SingularAttribute<GrupoRecurso, Long> idGrupoRecurso;
	public static volatile SingularAttribute<GrupoRecurso, String> nome;
	public static volatile SingularAttribute<GrupoRecurso, String> descricao;
	public static volatile SingularAttribute<GrupoRecurso, Date> dataCadastro;
	public static volatile ListAttribute<GrupoRecurso, Recurso> recursos;
}
