package br.com.grrecurso.entities;

import br.com.grrecurso.enumerator.DominioSituacaoSolicitacao;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-06-26T19:02:27.526-0400")
@StaticMetamodel(Solicitacao.class)
public class Solicitacao_ {
	public static volatile SingularAttribute<Solicitacao, Long> idSolicitacao;
	public static volatile SingularAttribute<Solicitacao, Usuario> usuario;
	public static volatile SingularAttribute<Solicitacao, String> chamado;
	public static volatile SingularAttribute<Solicitacao, String> descricao;
	public static volatile SingularAttribute<Solicitacao, GrupoRecurso> grupoRecurso;
	public static volatile SingularAttribute<Solicitacao, DominioSituacaoSolicitacao> situacao;
}
