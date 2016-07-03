package br.com.grrecurso.service;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7398964389307608328L;
	
	protected Log logger = LogFactory.getLog(this.getClass());
}
