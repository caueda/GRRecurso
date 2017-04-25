package br.com.grrecurso.core.service;

import javax.ejb.ApplicationException;

@ApplicationException(inherited=true, rollback=true)
public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8927107110427571L;

	public BusinessException() {
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
