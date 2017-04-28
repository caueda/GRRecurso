package br.com.grrecurso.entities.usuario;

import br.com.grrecurso.dominio.DominioAtivoInativo;
import br.com.grrecurso.dominio.DominioSexo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-04-28T15:19:10.982-0400")
@StaticMetamodel(Usuario.class)
public class Usuario_ {
	public static volatile SingularAttribute<Usuario, Long> idUsuario;
	public static volatile SingularAttribute<Usuario, String> nome;
	public static volatile SingularAttribute<Usuario, String> email;
	public static volatile SingularAttribute<Usuario, DominioSexo> sexo;
	public static volatile SingularAttribute<Usuario, String> senha;
	public static volatile SingularAttribute<Usuario, Date> dataLogin;
	public static volatile SingularAttribute<Usuario, DominioAtivoInativo> status;
	public static volatile SetAttribute<Usuario, PerfilUsuario> perfis;
	public static volatile SetAttribute<Usuario, Modulo> modulos;
	public static volatile SetAttribute<Usuario, Endereco> enderecos;
	public static volatile SingularAttribute<Usuario, Boolean> edicao;
	public static volatile SingularAttribute<Usuario, Integer> isDesenvolvedor;
}
