package br.com.grrecurso.entities;

import br.com.grrecurso.dominio.DominioSituacaoSolicitacao;
import br.com.grrecurso.entities.usuario.Modulo;
import br.com.grrecurso.entities.usuario.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-24T11:29:41.290-0400")
@StaticMetamodel(Solicitacao.class)
public class Solicitacao_ {
	public static volatile SingularAttribute<Solicitacao, Long> idSolicitacao;
	public static volatile SingularAttribute<Solicitacao, Usuario> usuario;
	public static volatile SingularAttribute<Solicitacao, String> chamado;
	public static volatile SingularAttribute<Solicitacao, String> descricao;
	public static volatile SingularAttribute<Solicitacao, GrupoRecurso> grupoRecurso;
	public static volatile SingularAttribute<Solicitacao, DominioSituacaoSolicitacao> situacao;
	public static volatile SingularAttribute<Solicitacao, Modulo> modulo;
}
