package br.com.grrecurso.entities;

import br.com.grrecurso.enumerator.DominioAtivoInativo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-06-25T18:23:20.033-0400")
@StaticMetamodel(Usuario.class)
public class Usuario_ {
	public static volatile SingularAttribute<Usuario, Long> idUsuario;
	public static volatile SingularAttribute<Usuario, String> nome;
	public static volatile SingularAttribute<Usuario, String> email;
	public static volatile SingularAttribute<Usuario, String> senha;
	public static volatile SingularAttribute<Usuario, Date> dataLogin;
	public static volatile SingularAttribute<Usuario, DominioAtivoInativo> status;
	public static volatile ListAttribute<Usuario, PerfilUsuario> perfis;
}
