@TypeDefs(
			{
			    @TypeDef(
			    		name = DominioAtivoInativo.NOME, 
			    		typeClass = GRRecursoGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = GRRecursoGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioAtivoInativo.NOME),
			    				      @Parameter(name = GRRecursoGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioAtivoInativo.METHOD)}),
			    @TypeDef(
			    		name = DominioSexo.NOME, 
			    		typeClass = GRRecursoGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = GRRecursoGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioSexo.NOME),
			    				      @Parameter(name = GRRecursoGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioSexo.METHOD)}),
			    @TypeDef(
			    		name = DominioSituacaoSolicitacao.NOME, 
			    		typeClass = GRRecursoGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = GRRecursoGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioSituacaoSolicitacao.NOME),
			    				      @Parameter(name = GRRecursoGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioSituacaoSolicitacao.METHOD)}),
			    @TypeDef(
			    		name = DominioTipoEndereco.NOME, 
			    		typeClass = GRRecursoGenericEnumUserType.class, 
			    		parameters = {@Parameter(name = GRRecursoGenericEnumUserType.ENUM_CLASS_NAME_PARAM, value = DominioTipoEndereco.NOME),
			    				      @Parameter(name = GRRecursoGenericEnumUserType.ENUM_CLASS_VALUE_METHOD, value = DominioTipoEndereco.METHOD)}),
			}
    			
		)
package br.com.grrecurso.dominio;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
