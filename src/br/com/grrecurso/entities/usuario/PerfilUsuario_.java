package br.com.grrecurso.entities.usuario;

import br.com.grrecurso.dominio.DominioAtivoInativo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-06-29T21:37:02.089-0400")
@StaticMetamodel(PerfilUsuario.class)
public class PerfilUsuario_ {
	public static volatile SingularAttribute<PerfilUsuario, Long> idPerfilUsuario;
	public static volatile SingularAttribute<PerfilUsuario, String> nome;
	public static volatile SingularAttribute<PerfilUsuario, Date> dataCadastro;
	public static volatile SingularAttribute<PerfilUsuario, DominioAtivoInativo> status;
}
