package br.com.grrecurso.dominio;

import java.util.Properties;

import org.hibernate.HibernateException;

public class GRRecursoGenericEnumUserType  extends GenericEnumUserType {

    public static final String PACOTE_DOMINIOS = "br.com.grrecurso.dominio";
    
    public static final String ENUM_CLASS_NAME_PARAM = "enumClass";
    

    protected void getEnumClassName(Properties properties) {

        StringBuilder sbPacote = new StringBuilder(PACOTE_DOMINIOS);
        
        String enumClassName = properties.getProperty(ENUM_CLASS_NAME_PARAM);
        
        try {
            enumClass = Class.forName(sbPacote.append(".").append(enumClassName).toString()).asSubclass(Enum.class);
        } catch (ClassNotFoundException cnfe) {
            throw new HibernateException("Domínio não encontrado", cnfe);
        }
    }

}