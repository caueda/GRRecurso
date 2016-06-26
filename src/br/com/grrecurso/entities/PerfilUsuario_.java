package br.com.grrecurso.entities;

import br.com.grrecurso.enumerator.DominioAtivoInativo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-06-25T18:23:20.031-0400")
@StaticMetamodel(PerfilUsuario.class)
public class PerfilUsuario_ {
	public static volatile SingularAttribute<PerfilUsuario, Long> idPerfilUsuario;
	public static volatile SingularAttribute<PerfilUsuario, String> nome;
	public static volatile SingularAttribute<PerfilUsuario, DominioAtivoInativo> status;
	public static volatile SingularAttribute<PerfilUsuario, Date> dataCadastro;
}
