@TypeDefs(
			{
			    @TypeDef(
			    		name = DominioAtivoInativo.NOME, 
			    		typeClass = GRRecursoGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = GRRecursoGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioAtivoInativo.NOME)}),
			    @TypeDef(
			    		name = DominioSexo.NOME, 
			    		typeClass = GRRecursoGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = GRRecursoGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioSexo.NOME)}),
			    @TypeDef(
			    		name = DominioSituacaoSolicitacao.NOME, 
			    		typeClass = GRRecursoGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = GRRecursoGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioSituacaoSolicitacao.NOME)}),
			    @TypeDef(
			    		name = DominioTipoEndereco.NOME, 
			    		typeClass = GRRecursoGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = GRRecursoGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioTipoEndereco.NOME)}),
			    @TypeDef(
			    		name = DominioTipoPermissao.NOME, 
			    		typeClass = GRRecursoGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = GRRecursoGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioTipoPermissao.NOME)}),			    
			    
			}
    			
		)
package br.com.grrecurso.dominio;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
