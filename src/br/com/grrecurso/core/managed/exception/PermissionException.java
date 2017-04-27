package br.com.grrecurso.core.managed.exception;

public class PermissionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -55009828851390152L;

	public PermissionException() {
	}

	public PermissionException(String message) {
		super(message);
	}

	public PermissionException(Throwable cause) {
		super(cause);
	}

	public PermissionException(String message, Throwable cause) {
		super(message, cause);
	}

	public PermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
